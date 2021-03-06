package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//import opennlp.tools.lang.english.PosTagger;
import opennlp.model.AbstractModel;
import opennlp.tools.postag.POSDictionary;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.TagDictionary;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.cas.NonEmptyStringList;
import org.apache.uima.resource.ResourceInitializationException;

import org.oaqa.model.nlp.Sentence;
import org.oaqa.model.nlp.Token;

/**
 * 
 * @author Ran Zhao
 * 
 *         Phase: POS-Tagger
 * 
 */

public class POSTagger extends JCasAnnotator_ImplBase {

  // LOG4J logger based on class name
  private Logger logger = Logger.getLogger(getClass().getName());

  /**
   * "PosModelFile" is a required, single, string parameter that contains the file name of the part
   * of speech tagger model. The model file name should end with ".bin.gz" or ".txt". If this is not
   * the case, then please see resources/models/README.
   */
  public static final String POS_MODEL_FILE_PARAM = "PosModelFile";

  /**
   * "TagDictionary" is an optional, single, string parameter that contains the file name of the
   * part-of-speech tag dictionary. For relevant discussion of the difference between a
   * "tag dictionary" and a "dictionary" see:
   * <p>
   * <ul>
   * <li>
   * <a href="https://sourceforge.net/forum/forum.php?thread_id=1720863&forum_id=9943">PosTagger -
   * with/without dictionaries? and ..</a>
   * <li>
   * </li>
   * <a href="https://sourceforge.net/forum/forum.php?thread_id=1894043&forum_id=9943">Create a new
   * dict file</a> </li>
   * </ul>
   * For information about how to create a TagDictionary, please see the README in top-level
   * directory of this project.
   * 
   * @see TagDictionary
   * @see POSDictionary
   */
  public static final String TAG_DICTIONARY_PARAM = "TagDictionary";

  /**
   * "CaseSensitive" is a required, single, boolean parameter that specifies how to access entries
   * in the tag dictionary. If you give the value "false", then you should really have a tag
   * dictionary that is also case insensitive. Please see the README in top-level directory of this
   * project for details on how to create a case insensitive tag dictionary.
   * 
   * <br>
   * This parameter has no effect if no tag dictionary is provided but is required if a tag
   * dictionary is provided.
   * 
   * @see POSDictionary#POSDictionary(String, boolean)
   * 
   */
  public static final String CASE_SENSITIVE_PARAM = "CaseSensitive";

  private opennlp.tools.postag.POSTaggerME tagger;

  public void initialize(UimaContext uimaContext) throws ResourceInitializationException {
    super.initialize(uimaContext);

    String posModelPath = null;

    try {

      posModelPath = (String) uimaContext.getConfigParameterValue(POS_MODEL_FILE_PARAM);
      File posModelFile = FileLocator.locateFile(posModelPath);
      String modelFileAbsPath = posModelFile.getAbsolutePath();
      logger.info("POS tagger model file: " + modelFileAbsPath);

      boolean caseSensitive = (Boolean) uimaContext.getConfigParameterValue(CASE_SENSITIVE_PARAM);
      String tagDictionaryPath = (String) uimaContext.getConfigParameterValue(TAG_DICTIONARY_PARAM);

      TagDictionary tagDictionary = null;
      if (tagDictionaryPath != null && !tagDictionaryPath.trim().equals("")) {
        File tagDictFile = FileLocator.locateFile(tagDictionaryPath);
        String tagDictFileAbsPath = tagDictFile.getAbsolutePath();
        logger.info("POS tagger tag-dictionary: " + tagDictFileAbsPath);

        tagDictionary = new POSDictionary(tagDictFileAbsPath, caseSensitive);
      } else {
        logger.info("No POS tagger tag-dictionary.");
      }

      FileInputStream fis = new FileInputStream(posModelFile);
      POSModel modelFile = new POSModel(fis); // skip using the tag dictionary for now since OpenNLP
                                              // (1.5) changed
      tagger = new opennlp.tools.postag.POSTaggerME(modelFile); // , tagDictionary);

    } catch (Exception e) {
      logger.info("POS tagger model: " + posModelPath);
      throw new ResourceInitializationException(e);
    }
  }

  public void process(JCas jCas) throws AnalysisEngineProcessException {

    logger.info("process(JCas)");

    List<Token> tokens = new ArrayList<Token>();
    List<String> words = new ArrayList<String>();

    AnnotationIndex baseTokenIndex = jCas.getAnnotationIndex(Token.type);

    FSIterator sentences = jCas.getAnnotationIndex(Sentence.type).iterator();

    while (sentences.hasNext()) {
      Sentence sentence = (Sentence) sentences.next();

      tokens.clear();
      words.clear();

      FSIterator tokenIterator = baseTokenIndex.subiterator(sentence);
      while (tokenIterator.hasNext()) {
        Token token = (Token) tokenIterator.next();
        tokens.add(token);
        words.add(token.getCoveredText());
      }

      List<?> wordTagList = null; // List of BaseToken's
      if (words.size() > 0) {
        wordTagList = tagger.tag(words);
      }
      // else {
      // logger.info("sentence has no words = '" + sentence.getCoveredText()
      // + "' at (" +sentence.getBegin() + "," + sentence.getEnd() + ")");
      // }

      try {
        for (int i = 0; i < tokens.size(); i++) {
          Token token = (Token) tokens.get(i);
          String posTag = (String) wordTagList.get(i);
          token.setPartOfSpeech(posTag);
          if (token.getGenerators() == null) {
            NonEmptyStringList generator = new NonEmptyStringList(jCas);
            token.setGenerators(generator);
            generator.setHead("POS:OpenNLP");

          } else {
            NonEmptyStringList generator = (NonEmptyStringList) token.getGenerators();
            generator.setTail(new NonEmptyStringList(jCas));
            generator = (NonEmptyStringList) generator.getTail();
            generator.setHead("POS:OpenNLP");

          }
        }
      } catch (IndexOutOfBoundsException e) {
        throw new AnalysisEngineProcessException("sentence being tagged is: '"
                + sentence.getCoveredText() + "'", null, e);
      }
    }
  }
}
