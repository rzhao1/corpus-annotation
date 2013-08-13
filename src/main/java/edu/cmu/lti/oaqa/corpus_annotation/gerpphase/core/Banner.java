package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.chunker.ChunkerModel;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.NonEmptyStringList;
import org.apache.uima.resource.ResourceInitializationException;
import org.oaqa.model.kb.ConceptMention;
import org.oaqa.model.kb.EntityMention;
import org.oaqa.model.nlp.Sentence;
import org.oaqa.model.nlp.Token;

import banner.BannerProperties;
import banner.processing.PostProcessor;
import banner.tagging.CRFTagger;
import banner.tagging.Mention;
import banner.tagging.TaggedToken;
import banner.tokenization.Tokenizer;

public class Banner extends JCasAnnotator_ImplBase {

  // LOG4J logger based on class name
  private Logger logger = Logger.getLogger(getClass().getName());


  public static final String PROPERTIESFILE = "PropertiesFile";

  public static final String MODELFILE = "Gene_Model_file";

  private String tagFile = null;

  private String propertiesFile = null;

  private String modelFile = null;

  public void initialize(UimaContext uimaContext) throws ResourceInitializationException {
    super.initialize(uimaContext);

    try {


 //     propertiesFile = (String) uimaContext.getConfigParameterValue(PROPERTIESFILE);

   //   modelFile = (String) uimaContext.getConfigParameterValue(MODELFILE);

      ClassLoader cl = FileLocator.class.getClassLoader();
      propertiesFile = cl.getResource((String) uimaContext.getConfigParameterValue(PROPERTIESFILE)).getPath();
      modelFile=cl.getResource((String) uimaContext.getConfigParameterValue(MODELFILE)).getPath();
      System.out.println("propertiesFile"+propertiesFile);
      System.out.println("modelFile"+modelFile);
     //  File chunkerModelFile = FileLocator.locateFile(tagFile);
      // InputStream fis = new FileInputStream(chunkerModelFile);
      // ChunkerModel model = new ChunkerModel(fis);
      // String chunkerModelAbsPath = chunkerModelFile.getAbsolutePath();
      // logger.info("Chunker model file: " + chunkerModelAbsPath);
      //
      // chunker = new opennlp.tools.chunker.ChunkerME(model);
      //
      // String chunkerCreatorClassName = (String) uimaContext
      // .getConfigParameterValue(CHUNKER_CREATOR_CLASS_PARAM);
      //
      // chunkerCreator = (ChunkCreator) Class.forName(chunkerCreatorClassName).newInstance();
      // chunkerCreator.initialize(uimaContext);

    } catch (Exception e) {

      throw new ResourceInitializationException(e);
    }
  }

  public void process(JCas jCas) throws AnalysisEngineProcessException {

    logger.info(" process(JCas)");

    List<Sentence> sentenceList = new ArrayList<Sentence>();

    // AnnotationIndex sentenceIndex = jCas.getAnnotationIndex(Sentence.type);
    FSIterator sentences = jCas.getAnnotationIndex(Sentence.type).iterator();
    BannerProperties properties = BannerProperties.load(propertiesFile);
    Tokenizer tokenizer = properties.getTokenizer();
    CRFTagger tagger = null;
    try {
      tagger = CRFTagger.load(new File(modelFile), properties.getLemmatiser(),
              properties.getPosTagger());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    PostProcessor postProcessor = properties.getPostProcessor();

    while (sentences.hasNext()) {
      Sentence sentence = (Sentence) sentences.next();
      banner.Sentence tagSentence = new banner.Sentence(null, sentence.getCoveredText());
      tokenizer.tokenize(tagSentence);
      tagger.tag(tagSentence);
      if (postProcessor != null)
        postProcessor.postProcess(tagSentence);
//      System.out.println("%%%%%%% The sentence is "+tagSentence.getSGML());
      List<Mention> taggedMentionSet = tagSentence.getMentions();
      for (int i = 0; i < taggedMentionSet.size(); i++) {
        ConceptMention neAnnot;
//        System.out.println("########begin"+taggedMentionSet.get(i).getStartChar()+" $$$$$End"+taggedMentionSet.get(i).getEndChar());
//        System.out.println("Mention is "+taggedMentionSet.get(i).getText());
        neAnnot = new EntityMention(jCas,  taggedMentionSet.get(i).getStartChar()+sentence.getBegin(), taggedMentionSet.get(i).getEndChar()
                + sentence.getBegin());
        // System.out.println("The current EntityMention is " + neAnnot.getCoveredText());
        if (neAnnot.getGenerators() == null) {
          NonEmptyStringList generator = new NonEmptyStringList(jCas);
          neAnnot.setGenerators(generator);
          generator.setHead("BannerNER");

        } else {
          NonEmptyStringList generator = (NonEmptyStringList) neAnnot.getGenerators();
          generator.setTail(new NonEmptyStringList(jCas));
          generator = (NonEmptyStringList) generator.getTail();
          generator.setHead("BannerNER");

        }
        neAnnot.addToIndexes();
      }

    }
  }
}
