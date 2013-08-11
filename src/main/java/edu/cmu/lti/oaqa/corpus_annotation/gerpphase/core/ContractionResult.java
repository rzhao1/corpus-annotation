package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

/**
 * 
 * @author Ran Zhao
 * 
 *         Phase: Tokenizer helper-function
 */
public class ContractionResult {

  private int wordTokenLen;

  private int contractionTokenLen;

  public void setContractionTokenLen(int contractionTokenLen) {
    this.contractionTokenLen = contractionTokenLen;
  }

  public int getContractionTokenLen() {
    return contractionTokenLen;
  }

  public void setWordTokenLen(int wordTokenLen) {
    this.wordTokenLen = wordTokenLen;
  }

  public int getWordTokenLen() {
    return wordTokenLen;
  }

}
