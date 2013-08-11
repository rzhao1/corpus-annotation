package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;

/**
 * 
 * @author Ran Zhao
 * 
 *         Phase: DictionaryLookup helper-function
 * 
 */
public class LuceneIndexReaderResourceImpl implements LuceneIndexReaderResource,
        SharedResourceObject {

  // LOG4J logger based on class name
  private Logger iv_logger = Logger.getLogger(getClass().getName());

  private IndexReader iv_indexReader;

  /**
   * Loads a Lucene index for reading.
   */
  public void load(DataResource dr) throws ResourceInitializationException {

    ConfigurationParameterSettings cps = dr.getMetaData().getConfigurationParameterSettings();
    Boolean useMemoryIndex = (Boolean) cps.getParameterValue("UseMemoryIndex");
    System.out.println("UseMemoryIndex:" + useMemoryIndex);
    String indexDirStr = (String) cps.getParameterValue("IndexDirectory");
    System.out.println("IndexDirectory:" + indexDirStr);
    try {

      File indexDir = FileLocator.locateFile(indexDirStr);

      if (!indexDir.exists())
        iv_logger.info("indexDir=" + indexDirStr + "  does not exist!");
      else
        iv_logger.info("indexDir=" + indexDirStr + "  exists.");

      if (useMemoryIndex.booleanValue()) {

        iv_logger.info("Loading Lucene Index into memory: " + indexDir);
        FSDirectory fsd = FSDirectory.open(indexDir);
        Directory d = new RAMDirectory(fsd);
        iv_indexReader = IndexReader.open(d);
      } else {
        iv_logger.info("Loading Lucene Index: " + indexDir);
        FSDirectory fsd = FSDirectory.open(indexDir);
        iv_indexReader = IndexReader.open(fsd);
      }
      iv_logger.info("Loaded Lucene Index, # docs=" + iv_indexReader.numDocs());
    } catch (Exception e) {
      throw new ResourceInitializationException(e);
    }
  }

  public void initializeExternalResource(Boolean useMemoryIndex, String indexDirStr)
          throws ResourceInitializationException {

    try {

      File indexDir = FileLocator.locateFile(indexDirStr);

      if (!indexDir.exists())
        iv_logger.info("indexDir=" + indexDirStr + "  does not exist!");
      else
        iv_logger.info("indexDir=" + indexDirStr + "  exists.");

      if (useMemoryIndex.booleanValue()) {

        iv_logger.info("Loading Lucene Index into memory: " + indexDir);
        FSDirectory fsd = FSDirectory.open(indexDir);
        Directory d = new RAMDirectory(fsd);
        iv_indexReader = IndexReader.open(d);
      } else {
        iv_logger.info("Loading Lucene Index: " + indexDir);
        FSDirectory fsd = FSDirectory.open(indexDir);
        iv_indexReader = IndexReader.open(fsd);
      }
      iv_logger.info("Loaded Lucene Index, # docs=" + iv_indexReader.numDocs());
    } catch (Exception e) {
      throw new ResourceInitializationException(e);
    }
  }

  public IndexReader getIndexReader() {
    return iv_indexReader;
  }
}