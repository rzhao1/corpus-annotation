package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import opennlp.maxent.io.SuffixSensitiveGISModelReader;
import opennlp.model.MaxentModel;
import opennlp.tools.sentdetect.DefaultSDContextGenerator;

import org.apache.ctakes.core.resource.FileLocator;
import org.apache.ctakes.core.resource.MaxentModelResource;
import org.apache.ctakes.core.sentence.EndOfSentenceScannerImpl;
import org.apache.ctakes.core.sentence.SentenceDetectorCtakes;
import org.apache.ctakes.core.sentence.SentenceSpan;
import org.apache.ctakes.core.util.ParamUtil;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.cas.NonEmptyStringList;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;
import org.oaqa.model.nlp.Segment;
import org.oaqa.model.nlp.Sentence;

/**
 * 
 * @author Ran Zhao
 * 
 *         Phase: Sentence Annotator
 */

public class SentenceDetector extends JCasAnnotator_ImplBase {

  /**
   * Value is "SegmentsToSkip". This parameter specifies which sections to skip. The parameter
   * should be of type String, should be multi-valued and optional.
   */
  public static final String PARAM_SEGMENTS_TO_SKIP = "SegmentsToSkip";

  public static final String PARAM_MODEL_FILE = "Model_Url";

  // LOG4J logger based on class name
  private Logger logger = Logger.getLogger(getClass().getName());

  private String MaxentModel;

  private MaxentModel iv_maxentModel;

  private UimaContext context;

  private Set<?> skipSegmentsSet;

  private SentenceDetectorCtakes sentenceDetector;

  private String NEWLINE = "\n";

  private int sentenceCount = 0;

  public void initialize(UimaContext aContext) throws ResourceInitializationException {

    super.initialize(aContext);

    ClassLoader cl1 = getClass().getClassLoader();
    URL indexUrl = cl1.getResource((String) aContext.getConfigParameterValue(PARAM_MODEL_FILE));
    MaxentModel = indexUrl.getPath();
    context = aContext;

    try {
      configInit();
    } catch (ResourceAccessException ace) {
      throw new ResourceInitializationException(ace);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Reads configuration parameters.
   * 
   * @throws ResourceAccessException
   * @throws IOException
   */
  private void configInit() throws ResourceAccessException, IOException {

    File modelFile = new File(MaxentModel);
    SuffixSensitiveGISModelReader reader = new SuffixSensitiveGISModelReader(modelFile);

    iv_maxentModel = reader.getModel();

    if (iv_maxentModel == null) {
      // TODO Consider throwing an exception here
      logger.warn("Unable to locate resource.");
    } else {

      EndOfSentenceScannerImpl eoss = new EndOfSentenceScannerImpl();
      char[] eosc = eoss.getEndOfSentenceCharacters();
      // SentenceDContextGenerator cg = new SentenceDContextGenerator();
      DefaultSDContextGenerator cg = new DefaultSDContextGenerator(eosc);
      sentenceDetector = new SentenceDetectorCtakes(iv_maxentModel, cg, eoss);

    }

    skipSegmentsSet = ParamUtil.getStringParameterValuesSet(PARAM_SEGMENTS_TO_SKIP, context);

  }

  @Override
  /**
   * Entry point for processing.
   */
  public void process(JCas jcas) throws AnalysisEngineProcessException {

    logger.info("Starting processing.");

    sentenceCount = 0;

    String text = jcas.getDocumentText();

    JFSIndexRepository indexes = jcas.getJFSIndexRepository();
    Iterator<?> sectionItr = indexes.getAnnotationIndex(Segment.type).iterator();
    while (sectionItr.hasNext()) {
      Segment sa = (Segment) sectionItr.next();
      String sectionID = sa.getId();

      if (!skipSegmentsSet.contains(sectionID)) {
        sentenceCount = annotateRange(jcas, text, sa, sentenceCount);
        System.out.println(sentenceCount);
      }
    }
  }

  /**
   * Detect sentences within a section of the text and add annotations to the CAS. Uses OpenNLP
   * sentence detector, and then additionally forces sentences to end at end-of-line characters
   * (splitting into multiple sentences). Also trims sentences. And if the sentence detector does
   * happen to form a sentence that is just white space, it will be ignored.
   * 
   * @param jcas
   *          view of the CAS containing the text to run sentence detector against
   * @param text
   *          the document text
   * @param section
   *          the section this sentence is in
   * @param sentenceCount
   *          the number of sentences added already to the CAS (if processing one section at a time)
   * @return count The sum of <code>sentenceCount</code> and the number of Sentence annotations
   *         added to the CAS for this section
   * @throws AnnotatorProcessException
   */
  protected int annotateRange(JCas jcas, String text, Segment section, int sentenceCount)
          throws AnalysisEngineProcessException {

    int b = section.getBegin();
    int e = section.getEnd();

    // Use OpenNLP tools to split text into sentences
    // The sentence detector returns the offsets of the sentence-endings it
    // detects
    // within the string
    int[] sentenceBreaks = sentenceDetector.sentPosDetect(text.substring(b, e)); // OpenNLP tools
                                                                                 // 1.5 returns
                                                                                 // Spans rather
                                                                                 // than offsets
                                                                                 // that 1.4 did
    int numSentences = sentenceBreaks.length;
    // There might be text after the last sentence-ending found by detector,
    // so +1
    SentenceSpan[] potentialSentSpans = new SentenceSpan[numSentences + 1];

    int sentStart = b;
    int sentEnd = b;
    // Start by filling in sentence spans from what OpenNLP tools detected
    // Will trim leading or trailing whitespace when check for end-of-line
    // characters
    for (int i = 0; i < numSentences; i++) {
      sentEnd = sentenceBreaks[i] + b; // OpenNLP tools 1.5 returns Spans rather than offsets that
                                       // 1.4 did
      String coveredText = text.substring(sentStart, sentEnd);
      potentialSentSpans[i] = new SentenceSpan(sentStart, sentEnd, coveredText);
      sentStart = sentEnd;
    }

    // If detector didn't find any sentence-endings,
    // or there was text after the last sentence-ending found,
    // create a sentence from what's left, as long as it's not all
    // whitespace.
    // Will trim leading or trailing whitespace when check for end-of-line
    // characters
    if (sentEnd < e) {
      String coveredText = text.substring(sentEnd, e);
      if (coveredText.trim() != "") {
        potentialSentSpans[numSentences] = new SentenceSpan(sentEnd, e, coveredText);
        numSentences++;
      }
    }

    // Copy potentialSentSpans into sentenceSpans,
    // ignoring any that are entirely whitespace,
    // trimming the rest,
    // and splitting any of those that contain an end-of-line character.
    // Then trim any leading or trailing whitespace of ones that were split.
    ArrayList<SentenceSpan> sentenceSpans = new ArrayList<SentenceSpan>(0);
    for (int i = 0; i < potentialSentSpans.length; i++) {
      if (potentialSentSpans[i] != null) {
        sentenceSpans.addAll(potentialSentSpans[i].splitAtLineBreaksAndTrim(NEWLINE)); // TODO
                                                                                       // Determine
        // line break
        // type
      }
    }

    // Add sentence annotations to the CAS
    int previousEnd = -1;
    for (int i = 0; i < sentenceSpans.size(); i++) {
      SentenceSpan span = sentenceSpans.get(i);
      if (span.getStart() != span.getEnd()) { // skip empty lines
        Sentence sa = new Sentence(jcas);
        sa.setBegin(span.getStart());
        sa.setEnd(span.getEnd());
        if (previousEnd <= sa.getBegin()) {
          // System.out.println("Adding Sentence Annotation for " +
          // span.toString());
          sa.setSentenceNumber(sentenceCount);
          if (sa.getGenerators() == null) {
            NonEmptyStringList generator = new NonEmptyStringList(jcas);
            sa.setGenerators(generator);
            generator.setHead("OpenNLP");

          } else {
            NonEmptyStringList generator = (NonEmptyStringList) sa.getGenerators();
            generator.setTail(new NonEmptyStringList(jcas));
            generator = (NonEmptyStringList) generator.getTail();
            generator.setHead("OpenNLP");

          }
          sa.addToIndexes();
          sentenceCount++;
          previousEnd = span.getEnd();
        } else {
          logger.error("Skipping sentence from " + span.getStart() + " to " + span.getEnd());
          logger.error("Overlap with previous sentence that ended at " + previousEnd);
        }
      }
    }
    return sentenceCount;
  }

}
