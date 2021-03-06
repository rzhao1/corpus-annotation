package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ctakes.core.util.ListFactory;
import org.apache.ctakes.dependency.parser.ae.ClearParserUtil;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyFSList;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyStringList;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceInitializationException;
import org.oaqa.model.kb.EventMention;
import org.oaqa.model.kb.RelationMention;
import org.oaqa.model.nlp.ConllDependencyNode;
import org.oaqa.model.nlp.SemanticRole;
import org.oaqa.model.nlp.Sentence;
import org.oaqa.model.nlp.Token;
import org.uimafit.component.JCasAnnotator_ImplBase;
import org.uimafit.descriptor.ConfigurationParameter;
import org.uimafit.descriptor.TypeCapability;
import org.uimafit.util.JCasUtil;

import clear.dep.DepNode;
import clear.dep.DepTree;
import clear.dep.srl.SRLHead;
import clear.morph.MorphEnAnalyzer;
import clear.parse.AbstractSRLParser;
import clear.reader.AbstractReader;

/**
 * This class provides a UIMA wrapper for the ClearParser Semantic Role Labeler, which is available
 * here.
 * <p>
 * http://code.google.com/p/clearparser/
 * <p>
 * Before using this AnalysisEngine, you should run a Tokenizer, POS-tagger, Lemmatizer, and the
 * CLEAR parser dependency parser.
 * <p>
 * Please see /clearparser-wrapper/resources/dependency/clear/README for important information
 * pertaining to the models provided for this parser.
 * <p>
 * 
 * @author Ran Zhao
 * 
 *         Phase: ClearParserSemanticRoleLabelerAE Annotator
 * 
 */
@TypeCapability(inputs = { "org.oaqa.model.nlp.Token:partOfSpeech",
    "org.oaqa.model.nlp.Token:tokenNumber", "org.oaqa.model.nlp.Token:end",
    "org.oaqa.model.nlp.Token:begin", "org.oaqa.model.nlp.ConllDependencyNode" })
public class ClearParserSemanticRoleLabelerAE extends JCasAnnotator_ImplBase {

  public Logger logger = Logger.getLogger(getClass().getName());

  public static final String DEFAULT_MODEL_FILE_NAME = "org/apache/ctakes/dependency/parser/models/srl/en_srl_ontonotes.jar";

  public static final String ENG_LEMMATIZER_DATA_FILE = "org/apache/ctakes/dependency/parser/models/lemmatizer/wordnet-3.0-lemma-data.jar";

  public static final String PARAM_PARSER_MODEL_FILE_NAME = "ParserModelFileName";

  @ConfigurationParameter(name = PARAM_PARSER_MODEL_FILE_NAME, description = "This parameter provides the file name of the semantic role labeler model required by the factory method provided by ClearParserUtil.")
  private String parserModelFileName;

  public static final String PARAM_LEMMATIZER_DATA_FILE = "LemmatizerDataFile";

  @ConfigurationParameter(name = PARAM_LEMMATIZER_DATA_FILE, description = "This parameter provides the data file required for the MorphEnAnalyzer. If not "
          + "specified, this analysis engine will use a default model from the resources directory")
  protected File lemmatizerDataFile;

  public static final String PARAM_USE_LEMMATIZER = "UseLemmatizer";

  @ConfigurationParameter(name = PARAM_USE_LEMMATIZER, defaultValue = "true", description = "If true, use the default ClearParser lemmatizer, otherwise use lemmas from the BaseToken normalizedToken field")
  protected boolean useLemmatizer;

  protected AbstractSRLParser parser;

  protected MorphEnAnalyzer lemmatizer;

  @Override
  public void initialize(UimaContext context) throws ResourceInitializationException {
    super.initialize(context);

    logger.info("using Morphy analysis? " + useLemmatizer);

    try {
      if (useLemmatizer) {
        // Note: If lemmatizer data file is not specified, then use lemmas from the BaseToken
        // normalizedToken field.
        // Initialize lemmatizer
        URL lemmatizerDataFileURL = this.lemmatizerDataFile == null ? this.getClass()
                .getClassLoader().getResource(ENG_LEMMATIZER_DATA_FILE) : this.lemmatizerDataFile
                .toURI().toURL();
        lemmatizer = new MorphEnAnalyzer(new URL(lemmatizerDataFileURL.toString()));
      }

      // Initialize role labeler
      URL parserModelURL = this.parserModelFileName == null ? this.getClass().getClassLoader()
              .getResource(DEFAULT_MODEL_FILE_NAME) : new File(this.parserModelFileName).toURI()
              .toURL();
      parser = ClearParserUtil.createSRLParser(parserModelURL.openStream());

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

      // Build map between CAS dependency node and id for later creation of
      // ClearParser dependency node/tree
      Map<ConllDependencyNode, Integer> depNodeToID = new HashMap<ConllDependencyNode, Integer>();
      int nodeId = 1;
      for (ConllDependencyNode depNode : JCasUtil.selectCovered(jCas, ConllDependencyNode.class,
              sentence)) {
        // if (depNode instanceof TopDependencyNode) {
        if (depNode.getHead() == null) {
          // A node without the head is the head of the sentence
          depNodeToID.put(depNode, 0);
        } else {
          depNodeToID.put(depNode, nodeId);
          nodeId++;
        }
      }

      // Initialize Token / Sentence info for the ClearParser Semantic Role Labeler
      for (int i = 0; i < tokens.size(); i++) {
        Token token = tokens.get(i);

        // Determine HeadId
        DepNode node = new DepNode();
        ConllDependencyNode casDepNode = JCasUtil.selectCovered(jCas, ConllDependencyNode.class,
                token).get(0);
        casDepNode.getDeprel();
        String headRelation = casDepNode.getDeprel();
        ConllDependencyNode head = casDepNode.getHead();

        // If there is no head, this is the head node, set node to 0
        int headId = (head == null) ? 0 : depNodeToID.get(head);

        // Populate Dependency Node / Tree information
        node.id = i + 1;
        node.form = token.getCoveredText();
        node.pos = token.getPartOfSpeech();
        node.lemma = useLemmatizer ? "" : token.getLemmaForm();
        node.setHead(headId, headRelation, 0);
        tree.add(node);
      }
      tree.setPredicates(AbstractReader.LANG_EN);

      // Run the SRL
      parser.parse(tree);

      // Convert ClearParser SRL output to CAS types
      extractSRLInfo(jCas, tokens, tree);

    }

    /*
     * FIXME DELETEME for (Sentence sentence: JCasUtil.select(jCas, Sentence.class)) {
     * System.out.println("Sentence: " + sentence.getCoveredText()); for (Predicate predicate :
     * JCasUtil.selectCovered(jCas, Predicate.class, sentence)) { System.out.println("\t" +
     * predicate.getCoveredText() + "/" + "pred");
     * 
     * 
     * for (SemanticRoleRelation relations : JCasUtil.select(predicate.getRelations(),
     * SemanticRoleRelation.class)) { SemanticArgument argument = relations.getArgument();
     * System.out.println("\t" + argument.getCoveredText() + "/" + argument.getLabel()); }
     * 
     * } }
     */

  }

  /**
   * Converts the output from the ClearParser Semantic Role Labeler to the ClearTK Predicate and
   * SemanticArgument Types.
   * 
   * @param jCas
   * @param tokens
   *          - In order list of tokens
   * @param tree
   *          - DepdendencyTree output by ClearParser SRLPredict
   */
  private void extractSRLInfo(JCas jCas, List<Token> tokens, DepTree tree) {
    Map<Integer, EventMention> headIdToPredicate = new HashMap<Integer, EventMention>();
    Map<EventMention, List<SemanticRole>> predicateArguments = new HashMap<EventMention, List<SemanticRole>>();

    // Start at node 1, since node 0 is considered the head of the sentence
    for (int i = 1; i < tree.size(); i++) {
      // Every ClearParser parserNode will contain an srlInfo field.
      DepNode parserNode = tree.get(i);
      Token token = tokens.get(i - 1);
      if (parserNode.srlInfo == null) {
        continue;
      }

      if (parserNode.srlInfo.isPredicate()) {
        int headId = i;
        if (!headIdToPredicate.containsKey(headId)) {
          // We have not encountered this predicate yet, so create it
          EventMention pred = this.createPredicate(jCas, parserNode.srlInfo.rolesetId, token);
          headIdToPredicate.put(headId, pred);
          pred.setRelations(new EmptyFSList(jCas));
        }
      } else {
        for (SRLHead head : parserNode.srlInfo.heads) {
          EventMention predicate;

          // Determine which predicate this argument belongs to
          if (!headIdToPredicate.containsKey(head.headId)) {
            // The predicate hasn't been encountered, so create it
            Token headToken = tokens.get(head.headId - 1);
            predicate = this.createPredicate(jCas, parserNode.srlInfo.rolesetId, headToken);
            headIdToPredicate.put(head.headId, predicate);
          } else {
            predicate = headIdToPredicate.get(head.headId);
          }

          // Append this argument to the predicate's list of arguments
          if (!predicateArguments.containsKey(predicate)) {
            predicateArguments.put(predicate, new ArrayList<SemanticRole>());
          }
          List<SemanticRole> argumentList = predicateArguments.get(predicate);

          // Create the semantic argument and store for later link creation
          SemanticRole argument = createArgument(jCas, head, token);
          argumentList.add(argument);
        }
      }
    }

    // Create relations between predicates and arguments
    for (Map.Entry<EventMention, List<SemanticRole>> entry : predicateArguments.entrySet()) {
      EventMention predicate = entry.getKey();

      List<RelationMention> relations = new ArrayList<RelationMention>();
      for (SemanticRole argument : entry.getValue()) {
        RelationMention relation = new RelationMention(jCas);
        relation.setSemanticArgument(argument);
        relation.setEventMention(predicate);
        relation.setCategory(argument.getLabel());
        if (predicate.getBegin() < argument.getBegin()) {
          relation.setBegin(predicate.getBegin());
          relation.setEnd(argument.getEnd());
        } else {
          relation.setBegin(argument.getBegin());
          relation.setEnd(predicate.getEnd());
        }
        if (relation.getGenerators() == null) {
          NonEmptyStringList generator = new NonEmptyStringList(jCas);
          relation.setGenerators(generator);
          generator.setHead("Clear SRL");

        } else {
          NonEmptyStringList generator = (NonEmptyStringList) relation.getGenerators();
          generator.setTail(new NonEmptyStringList(jCas));
          generator = (NonEmptyStringList) generator.getTail();
          generator.setHead("Clear SRL");

        }
        relation.addToIndexes();
        relations.add(relation);
        argument.setRelation(relation);
      }

      FSList relationsList = ListFactory.buildList(jCas,
              relations.toArray(new TOP[relations.size()]));
      predicate.setRelations(relationsList);
    }
  }

  private EventMention createPredicate(JCas jCas, String rolesetId, Token token) {
    EventMention pred = new EventMention(jCas, token.getBegin(), token.getEnd());
    pred.setFrameSet(rolesetId);
    if (pred.getGenerators() == null) {
      NonEmptyStringList generator = new NonEmptyStringList(jCas);
      pred.setGenerators(generator);
      generator.setHead("Clear SRL");

    } else {
      NonEmptyStringList generator = (NonEmptyStringList) pred.getGenerators();
      generator.setTail(new NonEmptyStringList(jCas));
      generator = (NonEmptyStringList) generator.getTail();
      generator.setHead("Clear SRL");

    }
    pred.addToIndexes();
    return pred;
  }

  private SemanticRole createArgument(JCas jCas, SRLHead head, Token token) {
    SemanticRole argument = new SemanticRole(jCas, token.getBegin(), token.getEnd());
    argument.setLabel(head.label);
    if (argument.getGenerators() == null) {
      NonEmptyStringList generator = new NonEmptyStringList(jCas);
      argument.setGenerators(generator);
      generator.setHead("Clear SRL");

    } else {
      NonEmptyStringList generator = (NonEmptyStringList) argument.getGenerators();
      generator.setTail(new NonEmptyStringList(jCas));
      generator = (NonEmptyStringList) generator.getTail();
      generator.setHead("Clear SRL");

    }
    argument.addToIndexes();
    return argument;
  }

}
