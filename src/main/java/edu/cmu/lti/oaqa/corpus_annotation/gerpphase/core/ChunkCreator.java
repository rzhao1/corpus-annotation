package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * @author Ran Zhao
 * 
 * Phase: Chunker helper-function
 */

/**
 * Implementations of this interface are responsible for creating chunk annotations. A chunk could
 * really be anything and not necessarily a phrase type as found in shallow parsing.
 * */
public interface ChunkCreator {

  public void initialize(UimaContext annotatorContext) throws ResourceInitializationException;

  /**
   * 
   * @param jCas
   *          the view to which to add the created chunk.
   * @param begin
   *          the beginning offset of the chunk
   * @param end
   *          the ending offset of the chunk
   * @param chunkType
   *          a string description of the chunk type - e.g. "NP"
   * @return the annotation created by this method. If no annotation is created, then return null.
   * @throws AnalysisEngineProcessException
   */
  public Annotation createChunk(JCas jCas, int begin, int end, String chunkType)
          throws AnalysisEngineProcessException;

}
