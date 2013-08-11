package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import org.apache.ctakes.core.util.DocumentIDAnnotationUtil;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyStringList;
import org.apache.uima.jcas.cas.NonEmptyStringList;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.cas.StringList;
import org.apache.uima.resource.ResourceInitializationException;
import org.oaqa.model.nlp.Segment;

/**
 * 
 * @author Ran Zhao
 * 
 *         Phase: Segment Annotator
 */

public class SegmentAnnotator extends JCasAnnotator_ImplBase {

  private String segmentId;

  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);

    segmentId = (String) aContext.getConfigParameterValue("SegmentID");
    if (segmentId == null) {
      segmentId = "SIMPLE_SEGMENT";
    }
  }

  /**
   * Entry point for processing.
   */
  public void process(JCas jCas) throws AnalysisEngineProcessException {
    Segment segment = new Segment(jCas);
    segment.setBegin(0);
    String text = jCas.getDocumentText();

    if (text == null) {
      String docId = DocumentIDAnnotationUtil.getDocumentID(jCas);
      throw new AnalysisEngineProcessException("text is null for docId=" + docId, null);
    }
    segment.setEnd(jCas.getDocumentText().length());
    segment.setId(segmentId);
    if (segment.getGenerators() == null) {
      NonEmptyStringList generator = new NonEmptyStringList(jCas);
      segment.setGenerators(generator);
      generator.setHead("OpenNLP");
      generator.setTail(new EmptyStringList(jCas));
    } else {
      NonEmptyStringList generator = (NonEmptyStringList) segment.getGenerators();
      generator.setTail(new NonEmptyStringList(jCas));
      generator = (NonEmptyStringList) generator.getTail();
      generator.setHead("OpenNLP");
      generator.setTail(new EmptyStringList(jCas));
    }
    segment.addToIndexes();
  }
}
