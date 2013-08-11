package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import org.apache.lucene.index.IndexReader;

/**
 * @author Ran Zhao
 * 
 *         Phase: DictionaryLookup helper-function
 */
public interface LuceneIndexReaderResource {
  public IndexReader getIndexReader();
}