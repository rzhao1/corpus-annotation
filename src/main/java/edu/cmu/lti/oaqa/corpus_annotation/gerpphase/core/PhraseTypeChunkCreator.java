package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyStringList;
import org.apache.uima.jcas.cas.NonEmptyStringList;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import org.oaqa.model.nlp.ADJP;
import org.oaqa.model.nlp.ADVP;
import org.oaqa.model.nlp.CONJP;
import org.oaqa.model.nlp.Chunk;
import org.oaqa.model.nlp.INTJ;
import org.oaqa.model.nlp.LST;
import org.oaqa.model.nlp.NP;
import org.oaqa.model.nlp.O;
import org.oaqa.model.nlp.PP;
import org.oaqa.model.nlp.PRT;
import org.oaqa.model.nlp.SBAR;
import org.oaqa.model.nlp.UCP;
import org.oaqa.model.nlp.VP;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * This chunker creator creates annotations of chunk and sets the chunkType feature of the
 * annotation to the passed in parameter chunkType.
 * 
 * @author Ran Zhao
 * 
 *         Phase: chunker helper-function
 */

public class PhraseTypeChunkCreator implements ChunkCreator {

  public void initialize(UimaContext uimaContext) throws ResourceInitializationException {

  }

  public Annotation createChunk(JCas jCas, int start, int end, String chunkType) {
    Chunk chunk;
    if (chunkType.equals("ADJP")) {
      chunk = new ADJP(jCas, start, end);
    } else if (chunkType.equals("ADVP")) {
      chunk = new ADVP(jCas, start, end);
    } else if (chunkType.equals("CONJP")) {
      chunk = new CONJP(jCas, start, end);
    } else if (chunkType.equals("INTJ")) {
      chunk = new INTJ(jCas, start, end);
    } else if (chunkType.equals("LST")) {
      chunk = new LST(jCas, start, end);
    } else if (chunkType.equals("NP")) {
      chunk = new NP(jCas, start, end);
    } else if (chunkType.equals("PP")) {
      chunk = new PP(jCas, start, end);
    } else if (chunkType.equals("PRT")) {
      chunk = new PRT(jCas, start, end);
    } else if (chunkType.equals("SBAR")) {
      chunk = new SBAR(jCas, start, end);
    } else if (chunkType.equals("UCP")) {
      chunk = new UCP(jCas, start, end);
    } else if (chunkType.equals("VP")) {
      chunk = new VP(jCas, start, end);
    } else if (chunkType.equals("O")) {
      chunk = new O(jCas, start, end);
    } else {
      chunk = new Chunk(jCas, start, end);
    }

    chunk.setChunkType(chunkType);
    if (chunk.getGenerators() == null) {
      NonEmptyStringList generator = new NonEmptyStringList(jCas);
      chunk.setGenerators(generator);
      generator.setHead("OpenNLP");

    } else {
      NonEmptyStringList generator = (NonEmptyStringList) chunk.getGenerators();
      generator.setTail(new NonEmptyStringList(jCas));
      generator = (NonEmptyStringList) generator.getTail();
      generator.setHead("OpenNLP");
    }
    chunk.addToIndexes();
    return chunk;
  }

}
