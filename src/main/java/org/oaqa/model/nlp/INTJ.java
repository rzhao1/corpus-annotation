

/* First created by JCasGen Mon Jul 22 21:53:58 EDT 2013 */
package org.oaqa.model.nlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Tue Aug 13 23:03:25 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class INTJ extends Chunk {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(INTJ.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected INTJ() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public INTJ(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public INTJ(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public INTJ(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
}

    