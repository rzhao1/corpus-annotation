package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.ctakes.dependency.parser.ae.ClearParserUtil;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.oaqa.model.nlp.ConllDependencyNode;
import org.oaqa.model.nlp.Sentence;
import org.oaqa.model.nlp.Token;
import org.uimafit.descriptor.ConfigurationParameter;
import org.uimafit.descriptor.TypeCapability;
import org.uimafit.util.JCasUtil;

import clear.dep.DepNode;
import clear.dep.DepTree;
import clear.morph.MorphEnAnalyzer;
import clear.parse.AbstractDepParser;
import org.uimafit.component.JCasAnnotator_ImplBase;

/**
 * <br>
 * This class provides a UIMA wrapper for the CLEAR dependency parser. This parser is available
 * here:
 * <p>
 * http://code.google.com/p/clearparser/
 * <p>
 * Please see /clearparser-wrapper/resources/dependency/README for important information pertaining
 * to the models provided for this parser. In particular, note that the output of the CLEAR parser
 * is different than that of the Malt parser and so these two parsers may not be interchangeable
 * (without some effort) for most use cases.
 * <p>
 * 
 * @author Ran Zhao
 * 
 *         Phase: ClearParserDependencyParserAE Annotator
 */
@TypeCapability(inputs = { "org.oaqa.model.nlp.Token:partOfSpeech",
    "org.oaqa.model.nlp.Token:lemmaForm", "org.oaqa.model.nlp.Token:tokenNumber",
    "org.oaqa.model.nlp.Token:end", "org.oaqa.model.nlp.Token:begin" })
public class ClearParserDependencyParserAE extends JCasAnnotator_ImplBase {

  public Logger logger = Logger.getLogger(getClass().getName());

  // Default model values
  public static final String DEFAULT_MODEL_FILE_NAME = "org/apache/ctakes/dependency/parser/models/dependency/conll-2009-dev-shift-pop.jar";

  public static final String ENG_LEMMATIZER_DATA_FILE = "org/apache/ctakes/dependency/parser/models/lemmatizer/wordnet-3.0-lemma-data.jar";

  public static final String DEFAULT_PARSER_ALGORITHM_NAME = AbstractDepParser.ALG_SHIFT_POP;

  // Configuration Parameters
  public static final String PARAM_PARSER_MODEL_FILE_NAME = "ParserModelFileName";

  @ConfigurationParameter(name = PARAM_PARSER_MODEL_FILE_NAME, description = "This parameter provides the file name of the dependency parser model required "
          + "by the factory method provided by ClearParserUtil.  If not specified, this "
          + "analysis engine will use a default model from the resources directory")
  protected String parserModelFileName;

  public static final String PARAM_LEMMATIZER_DATA_FILE = "LemmatizerDataFile";

  @ConfigurationParameter(name = PARAM_LEMMATIZER_DATA_FILE, description = "This parameter provides the data file required for the MorphEnAnalyzer. If not "
          + "specified, this analysis engine will use a default model from the resources directory")
  protected File lemmatizerDataFile;

  public static final String PARAM_PARSER_ALGORITHM_NAME = "ParserAlgorithmName";

  @ConfigurationParameter(name = PARAM_PARSER_ALGORITHM_NAME, defaultValue = DEFAULT_PARSER_ALGORITHM_NAME, mandatory = true, description = "This parameter provides the algorithm name used by the dependency parser that "
          + "is required by the factory method provided by ClearParserUtil.  "
          + "If in doubt, do not change from the default value.")
  protected String parserAlgorithmName;

  public static final String PARAM_USE_LEMMATIZER = "UseLemmatizer";

  @ConfigurationParameter(name = PARAM_USE_LEMMATIZER, defaultValue = "true", description = "If true, use the default ClearParser lemmatizer, otherwise use lemmas from the BaseToken normalizedToken field")
  protected boolean useLemmatizer;

  protected AbstractDepParser parser;

  protected MorphEnAnalyzer lemmatizer;

  // protected boolean useLemmatizer = false;

  @Override
  public void initialize(UimaContext context) throws ResourceInitializationException {
    super.initialize(context);
    logger.info("using Morphy analysis? " + useLemmatizer);

    try {
      if (useLemmatizer) {

        URL lemmatizerDataFileURL = getClass().getClassLoader().getResource(
                ENG_LEMMATIZER_DATA_FILE);
        lemmatizer = new MorphEnAnalyzer(lemmatizerDataFileURL);
      }

      // Initialize parser
      URL parserModelURL = getClass().getClassLoader().getResource(DEFAULT_MODEL_FILE_NAME);
      parser = ClearParserUtil.createParser(parserModelURL.openStream(), parserAlgorithmName);
    } catch (MalformedURLException e) {
      throw new ResourceInitializationException(e);
    } catch (IOException e) {
      throw new ResourceInitializationException(e);
    }

  }

  @Override
  public void process(JCas jCas) throws AnalysisEngineProcessException {
    for (Sentence sentence : JCasUtil.select(jCas, Sentence.class)) {
      List<Token> tokens = JCasUtil.selectCovered(jCas, Token.class, sentence);
      DepTree tree = new DepTree();

      // Convert CAS data into structures usable by ClearParser
      for (int i = 0; i < tokens.size(); i++) {
        Token token = tokens.get(i);
        DepNode node = new DepNode();
        node.id = i + 1;
        node.form = token.getCoveredText();
        node.pos = token.getPartOfSpeech();
        node.lemma = useLemmatizer ? lemmatizer.getLemma(node.form, node.pos) : token
                .getLemmaForm();
        tree.add(node);
      }

      // Run parser and convert output back to CAS friendly data types
      parser.parse(tree);
      ArrayList<ConllDependencyNode> nodes = ClearDependencyUtility.convert(jCas, tree, sentence,
              tokens);
      ClearDependencyUtility.addToIndexes(jCas, nodes);
    }

  }
}
