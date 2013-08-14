package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.NonEmptyStringList;
import org.apache.uima.resource.ResourceInitializationException;
import org.oaqa.model.kb.FanseDependencyRelation;
import org.oaqa.model.kb.FanseSemanticRelation;
import org.oaqa.model.nlp.FanseToken;
import org.oaqa.model.nlp.Sentence;
import org.oaqa.model.nlp.Token;
import org.uimafit.component.JCasAnnotator_ImplBase;
import org.uimafit.util.FSCollectionFactory;
import org.uimafit.util.JCasUtil;

import com.google.common.collect.ArrayListMultimap;

import tratz.parse.FullSystemWrapper;
import tratz.parse.FullSystemWrapper.FullSystemResult;
import tratz.parse.types.Arc;
import tratz.parse.types.Parse;

/**
 * Runs FANSE parser, and annotate associated types.
 * 
 * Required annotation: sentence, token
 */
public class FanseAnnotator extends JCasAnnotator_ImplBase {

  private String pos_model;

  private String parse_model;

  private String possessiveModel;

  private String nnModel;

  private String srlArgsWrapper;

  private String srlPredWrapper;

  private String psdModels;

  private String wordnet3;

  // these file are assume existence in the base directory
  private static String POS_MODEL = "posTaggingModel", PARSE_MODEL = "parseModel",
          POSSESSIVES_MODEL = "possessivesModel", NOUN_COMPOUND_MODEL = "nnModel",
          SRL_ARGS_MODELS = "srlArgsWrapper", SRL_PREDICATE_MODELS = "srlPredWrapper",
          PREPOSITION_MODELS = "psdModels", WORDNET = "wordnet";

  public final static Boolean DEFAULT_VCH_CONVERT = Boolean.FALSE;

  public final static String DEFAULT_SENTENCE_READER_CLASS = tratz.parse.io.ConllxSentenceReader.class
          .getName();

  FullSystemWrapper fullSystemWrapper = null;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    try {

      ClassLoader cl = FileLocator.class.getClassLoader();
      psdModels = cl.getResource((String) aContext.getConfigParameterValue(PREPOSITION_MODELS))
              .getPath();
      nnModel = cl.getResource((String) aContext.getConfigParameterValue(NOUN_COMPOUND_MODEL))
              .getPath();
      possessiveModel = cl
              .getResource((String) aContext.getConfigParameterValue(POSSESSIVES_MODEL)).getPath();
      srlArgsWrapper = cl.getResource((String) aContext.getConfigParameterValue(SRL_ARGS_MODELS))
              .getPath();
      srlPredWrapper = cl.getResource(
              (String) aContext.getConfigParameterValue(SRL_PREDICATE_MODELS)).getPath();
      pos_model = cl.getResource((String) aContext.getConfigParameterValue(POS_MODEL)).getPath();
      parse_model = cl.getResource((String) aContext.getConfigParameterValue(PARSE_MODEL))
              .getPath();
      wordnet3 = cl.getResource((String) aContext.getConfigParameterValue(WORDNET)).getPath();

      fullSystemWrapper = new FullSystemWrapper(psdModels, nnModel, possessiveModel,
              srlArgsWrapper, srlPredWrapper, pos_model, parse_model, wordnet3);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ResourceInitializationException();
    }
  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // List<Sentence> sentList = UimaConvenience.getAnnotationList(aJCas, Sentence.class);
    Collection<Sentence> sentList = JCasUtil.select(aJCas, Sentence.class);

    for (Sentence sent : sentList) {
      // List<Word> words = JCasUtil.selectCovered(Word.class, sent);
      List<Token> tokenList = JCasUtil.selectCovered(Token.class, sent);

      // Parse par = wordListToParse(words);
      Parse par = tokenListToParse(tokenList);
      tratz.parse.types.Sentence fSent = par.getSentence();
      List<tratz.parse.types.Token> tokens = fSent.getTokens();

      FullSystemResult result = fullSystemWrapper.process(fSent, tokens.size() > 0
              && tokens.get(0).getPos() == null, true, true, true, true, true);

      Parse dependencyParse = result.getParse();
      Parse semanticParse = result.getSrlParse();

      tratz.parse.types.Sentence resultSent = dependencyParse.getSentence();
      List<tratz.parse.types.Token> resultTokens = resultSent.getTokens();

      // get Token annotation and convert them to UIMA
      Map<tratz.parse.types.Token, FanseToken> Fanse2UimaMap = new HashMap<tratz.parse.types.Token, FanseToken>();
      for (tratz.parse.types.Token token : resultTokens) {
        // Word word = words.get(token.getIndex() - 1);
        Token stanfordToken = tokenList.get(token.getIndex() - 1);

        FanseToken fToken = new FanseToken(aJCas);
        // fToken.setBegin(word.getBegin());
        // fToken.setEnd(word.getEnd());
        fToken.setBegin(stanfordToken.getBegin());
        fToken.setEnd(stanfordToken.getEnd());

        fToken.setCoarsePos(token.getCoarsePos());
        fToken.setPos(token.getPos());
        fToken.setLexicalSense(token.getLexSense());

        Fanse2UimaMap.put(token, fToken);
      }

      // now create depedency edges of these nodes
      // Map<FanseToken, List<FanseDepedencyRelation>> dependencyHeadRelationMap = new
      // HashMap<FanseToken, List<FanseDepedencyRelation>>();
      // Map<FanseToken, List<FanseDepedencyRelation>> dependencyChildRelationMap = new
      // HashMap<FanseToken, List<FanseDepedencyRelation>>();

      ArrayListMultimap<FanseToken, FanseDependencyRelation> dependencyHeadRelationMap = ArrayListMultimap
              .create();
      ArrayListMultimap<FanseToken, FanseDependencyRelation> dependencyChildRelationMap = ArrayListMultimap
              .create();

      for (Arc arc : dependencyParse.getArcs()) {
        if (arc != null) {
          FanseToken childToken = Fanse2UimaMap.get(arc.getChild());
          FanseToken headToken = Fanse2UimaMap.get(arc.getHead());

          if (childToken != null || headToken != null) {
            FanseDependencyRelation fArc = new FanseDependencyRelation(aJCas);
            fArc.setHead(headToken);
            fArc.setChild(childToken);
            fArc.setDependency(arc.getDependency());

            dependencyHeadRelationMap.put(childToken, fArc);
            dependencyChildRelationMap.put(headToken, fArc);

            if (fArc.getGenerators() == null) {
              NonEmptyStringList generator = new NonEmptyStringList(aJCas);
              fArc.setGenerators(generator);
              generator.setHead("Fanse");

            } else {
              NonEmptyStringList generator = (NonEmptyStringList) fArc.getGenerators();
              generator.setTail(new NonEmptyStringList(aJCas));
              generator = (NonEmptyStringList) generator.getTail();
              generator.setHead("Fanse");

            }
            fArc.addToIndexes(aJCas);
          }
        }
      }

      // now creat semantic edges of these nodes
      // Map<FanseToken, List<FanseSemanticRelation>> semanticHeadRelationMap = new
      // HashMap<FanseToken, List<FanseSemanticRelation>>();
      // Map<FanseToken, List<FanseSemanticRelation>> semanticChildRelationMap = new
      // HashMap<FanseToken, List<FanseSemanticRelation>>();

      ArrayListMultimap<FanseToken, FanseSemanticRelation> semanticHeadRelationMap = ArrayListMultimap
              .create();
      ArrayListMultimap<FanseToken, FanseSemanticRelation> semanticChildRelationMap = ArrayListMultimap
              .create();

      for (Arc arc : semanticParse.getArcs()) {
        if (arc != null && arc.getSemanticAnnotation() != null) {
          FanseToken childToken = Fanse2UimaMap.get(arc.getChild());
          FanseToken headToken = Fanse2UimaMap.get(arc.getHead());

          if (childToken != null || headToken != null) {
            FanseSemanticRelation fArc = new FanseSemanticRelation(aJCas);
            fArc.setHead(headToken);
            fArc.setChild(childToken);
            fArc.setSemanticAnnotation(arc.getSemanticAnnotation());

            semanticHeadRelationMap.put(childToken, fArc);
            semanticChildRelationMap.put(headToken, fArc);
            if (fArc.getGenerators() == null) {
              NonEmptyStringList generator = new NonEmptyStringList(aJCas);
              fArc.setGenerators(generator);
              generator.setHead("Fanse");

            } else {
              NonEmptyStringList generator = (NonEmptyStringList) fArc.getGenerators();
              generator.setTail(new NonEmptyStringList(aJCas));
              generator = (NonEmptyStringList) generator.getTail();
              generator.setHead("Fanse");

            }
            fArc.addToIndexes(aJCas);
          }
        }
      }

      // associate token annotation with arc
      for (FanseToken fToken : Fanse2UimaMap.values()) {
        if (dependencyHeadRelationMap.containsKey(fToken)) {
          fToken.setHeadDependencyRelations(FSCollectionFactory.createFSList(aJCas,
                  dependencyHeadRelationMap.get(fToken)));
        }
        if (dependencyChildRelationMap.containsKey(fToken)) {
          fToken.setChildDependencyRelations(FSCollectionFactory.createFSList(aJCas,
                  dependencyChildRelationMap.get(fToken)));
        }
        if (semanticHeadRelationMap.containsKey(fToken)) {
          fToken.setHeadSemanticRelations(FSCollectionFactory.createFSList(aJCas,
                  semanticHeadRelationMap.get(fToken)));
        }
        if (semanticChildRelationMap.containsKey(fToken)) {
          fToken.setChildSemanticRelations(FSCollectionFactory.createFSList(aJCas,
                  semanticChildRelationMap.get(fToken)));
        }

        if (fToken.getGenerators() == null) {
          NonEmptyStringList generator = new NonEmptyStringList(aJCas);
          fToken.setGenerators(generator);
          generator.setHead("Fanse");

        } else {
          NonEmptyStringList generator = (NonEmptyStringList) fToken.getGenerators();
          generator.setTail(new NonEmptyStringList(aJCas));
          generator = (NonEmptyStringList) generator.getTail();
          generator.setHead("Fanse");

        }
        fToken.addToIndexes(aJCas);
      }
    }
  }

  private Parse tokenListToParse(List<Token> tokenList) {
    tratz.parse.types.Token root = new tratz.parse.types.Token("[ROOT]", 0);
    List<tratz.parse.types.Token> tokens = new ArrayList<tratz.parse.types.Token>();
    List<Arc> arcs = new ArrayList<Arc>();

    int tokenNum = 0;
    for (Token stanfordToken : tokenList) {
      tokenNum++;
      String wordString = stanfordToken.getCoveredText();
      tratz.parse.types.Token token = new tratz.parse.types.Token(wordString, tokenNum);
      tokens.add(token);
    }

    // Currently does not implement the Quote converstion by Tratz in TokenizingSentenceReader
    // line = mDoubleQuoteMatcher.reset(line).replaceAll("\"");
    // line = mSingleQuoteMatcher.reset(line).replaceAll("'");

    Parse result = new Parse(new tratz.parse.types.Sentence(tokens), root, arcs);

    return result;
  }

}
