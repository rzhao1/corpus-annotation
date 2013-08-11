package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.cas.NonEmptyStringList;
import org.apache.uima.resource.ResourceInitializationException;
import org.oaqa.model.kb.ConceptMention;
import org.oaqa.model.kb.EntityMention;
import org.oaqa.model.nlp.Sentence;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.util.AbstractExternalizable;

/**
 * 
 * @author Ran Zhao
 * 
 *         Phase: GeneTag Annotator
 * 
 */

public class GeneTagAnnotator extends JCasAnnotator_ImplBase {

  public static final String MODEL_FILE_PARAM = "ModelFile";

  public static String ModelPath;

  public void initialize(UimaContext uimaContext) throws ResourceInitializationException {
    super.initialize(uimaContext);

    try {
      ModelPath = (String) uimaContext.getConfigParameterValue(MODEL_FILE_PARAM);
    } catch (Exception e) {
      throw new ResourceInitializationException(e);
    }
  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {

    String content;
    File modelFile = null;
    try {
      modelFile = FileLocator.locateFile(ModelPath);
    } catch (FileNotFoundException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    System.out.println("Reading chunker from file=" + modelFile);
    Chunker chunker = null;
    try {
      chunker = (Chunker) AbstractExternalizable.readObject(modelFile);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    JFSIndexRepository indexes = aJCas.getJFSIndexRepository();
    FSIterator<?> sentencesIter = indexes.getAnnotationIndex(Sentence.type).iterator();
    // Tokenize each sentence, adding the tokens to the cas index
    while (sentencesIter.hasNext()) {
      Sentence sentenceLine = (Sentence) sentencesIter.next();
      String line = sentenceLine.getCoveredText();
      // System.out.println("Content is" + line);
      Chunking chunking = chunker.chunk(line);
      Set<Chunk> chunkSet = chunking.chunkSet();
      Iterator<Chunk> it = chunkSet.iterator();
      while (it.hasNext()) {
        Chunk chunk = it.next();
        // System.out.println("Current chunk is " + chunk);
        // System.out.println(sentenceLine.getBegin()+"/"+sentenceLine.getEnd());
        ConceptMention neAnnot;
        neAnnot = new EntityMention(aJCas, chunk.start() + sentenceLine.getBegin(), chunk.end()
                + sentenceLine.getBegin());
        // System.out.println("The current EntityMention is " + neAnnot.getCoveredText());
        if (neAnnot.getGenerators() == null) {
          NonEmptyStringList generator = new NonEmptyStringList(aJCas);
          neAnnot.setGenerators(generator);
          generator.setHead("LingPipeNER");

        } else {
          NonEmptyStringList generator = (NonEmptyStringList) neAnnot.getGenerators();
          generator.setTail(new NonEmptyStringList(aJCas));
          generator = (NonEmptyStringList) generator.getTail();
          generator.setHead("LingPipeNER");

        }
        neAnnot.addToIndexes();
      }

    }

  }

}