package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.util.List;
import java.util.Set;

import edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core.TokenizerPTB;
import org.apache.ctakes.core.util.ParamUtil;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.cas.NonEmptyStringList;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;
import org.oaqa.model.nlp.Segment;
import org.oaqa.model.nlp.Sentence;
import org.oaqa.model.nlp.Token;

/**
 * 
 * @author Ran Zhao
 * 
 *         Phase: Tokenizer Annotator
 */
public class TokenizerAnnotatorPTB extends JCasAnnotator_ImplBase {
  // LOG4J logger based on class name
  private Logger logger = Logger.getLogger(getClass().getName());

  /**
   * Value is "SegmentsToSkip". This parameter specifies which segments to skip. The parameter
   * should be of type String, should be multi-valued and optional.
   */
  public static final String PARAM_SEGMENTS_TO_SKIP = "SegmentsToSkip";

  private UimaContext context;

  private Set<String> skipSegmentsSet;

  private TokenizerPTB tokenizer;

  private int tokenCount = 0;

  public void initialize(UimaContext aContext) throws ResourceInitializationException {

    super.initialize(aContext);

    logger.info("Initializing " + this.getClass().getName());
    context = aContext;
    try {
      configInit();
    } catch (ResourceAccessException e) {
      throw new ResourceInitializationException(e);
    } finally {
    }
    ;
  }

  /**
   * Reads configuration parameters.
   * 
   * @throws ResourceAccessException
   */
  private void configInit() throws ResourceAccessException {

    skipSegmentsSet = ParamUtil.getStringParameterValuesSet(PARAM_SEGMENTS_TO_SKIP, context);

    tokenizer = new TokenizerPTB();

  }

  /**
   * Entry point for processing.
   */
  public void process(JCas jcas) throws AnalysisEngineProcessException {

    logger.info("process(JCas) in " + this.getClass().getName());

    tokenCount = 0;

    JFSIndexRepository indexes = jcas.getJFSIndexRepository();
    FSIterator<Annotation> segmentItr = indexes.getAnnotationIndex(Segment.type).iterator();
    while (segmentItr.hasNext()) {
      Segment sa = (Segment) segmentItr.next();
      String segmentID = sa.getId();
      if (!skipSegmentsSet.contains(segmentID)) {
        try {
          annotateRange(jcas, sa.getBegin(), sa.getEnd());
        } catch (AnnotatorProcessException e) {
          throw new AnalysisEngineProcessException(e);
        }
      }
    }
  }

  static char CR = '\r';

  static char LF = '\n';

  /**
   * Tokenizes a range of text, adding the tokens to the CAS Tokenizes one sentence at a time. Only
   * tokenizes what is within Sentence annotation. There must have been Sentence annotations created
   * beforehand in order for this method to tokenize anything.
   */
  protected void annotateRange(JCas jcas, int rangeBegin, int rangeEnd)
          throws AnnotatorProcessException {

    // int tokenCount = 0; // can't start with tokenCount=0 here because this method can be called
    // multiple times
    JFSIndexRepository indexes = jcas.getJFSIndexRepository();

    // First look for all newlines and carriage returns (which are not contained within sentences)
    String docText = jcas.getDocumentText();
    for (int i = rangeBegin; i < rangeEnd; i++) {

      if (docText.charAt(i) == CR) {

        Token nta;
        if (i + 1 < rangeEnd && docText.charAt(i + 1) == LF) {
          // single NewlineToken for the 2 characters
          nta = new Token(jcas, i, i + 2);
          i++; // skip past the LF
        } else {
          nta = new Token(jcas, i, i + 1);
        }
        if (nta.getGenerators() == null) {
          NonEmptyStringList generator = new NonEmptyStringList(jcas);
          nta.setGenerators(generator);
          generator.setHead("Token:cTakes Rule-based");

        } else {
          NonEmptyStringList generator = (NonEmptyStringList) nta.getGenerators();
          generator.setTail(new NonEmptyStringList(jcas));
          generator = (NonEmptyStringList) generator.getTail();
          generator.setHead("Token:cTakes Rule-based");

        }
        nta.addToIndexes();

      } else if (docText.charAt(i) == LF) {

        Token nta = new Token(jcas, i, i + 1);
        if (nta.getGenerators() == null) {
          NonEmptyStringList generator = new NonEmptyStringList(jcas);
          nta.setGenerators(generator);
          generator.setHead("Token:cTakes Rule-based");

        } else {
          NonEmptyStringList generator = (NonEmptyStringList) nta.getGenerators();
          generator.setTail(new NonEmptyStringList(jcas));
          generator = (NonEmptyStringList) generator.getTail();
          generator.setHead("Token:cTakes Rule-based");

        }
        nta.addToIndexes();

      }

    }

    // Now process each sentence
    FSIterator<?> sentencesIter = indexes.getAnnotationIndex(Sentence.type).iterator();
    // Tokenize each sentence, adding the tokens to the cas index
    while (sentencesIter.hasNext()) {
    
      Sentence sentence = (Sentence) sentencesIter.next();
      if (sentence.getBegin() < rangeBegin || sentence.getEnd() > rangeEnd) {
        continue;
      }
      List<Token> tokens = (List<Token>) tokenizer.tokenizeTextSegment(jcas,
              sentence.getCoveredText(), sentence.getBegin(), true);
      for (Token bta : tokens) {
        if (bta == null) {
          Exception e = new RuntimeException("bta==null tokenCount=" + tokenCount
                  + " tokens.size()==" + tokens.size());
          e.printStackTrace();
        } else {
          // logger.info("Token #" + tokenCount + " len = " + bta.getCoveredText().length() + " " +
          // bta.getCoveredText());
          // add the BaseToken to CAS index
          if (bta.getGenerators() == null) {
            NonEmptyStringList generator = new NonEmptyStringList(jcas);
            bta.setGenerators(generator);
            generator.setHead("Token:cTakes Rule-based");
          } else {
            NonEmptyStringList generator = (NonEmptyStringList) bta.getGenerators();
            generator.setTail(new NonEmptyStringList(jcas));
            generator = (NonEmptyStringList) generator.getTail();
            generator.setHead("Token:cTakes Rule-based");
          }
          bta.addToIndexes();
          tokenCount++;
        }
      }

    }

    // // Now add the tokenNumber in the order of offsets
    // FSIterator<?> baseTokenIter = indexes.getAnnotationIndex(Token.type).iterator();
    // while (baseTokenIter.hasNext()) {
    // Token bta = (BaseToken) baseTokenIter.next();
    // if (bta.getBegin()>=rangeBegin && bta.getBegin()<rangeEnd) {
    // bta.setTokenNumber(tokenCount);
    // tokenCount++;
    // }
    // }

  }
}
