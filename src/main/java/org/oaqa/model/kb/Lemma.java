

/* First created by JCasGen Sun Jul 28 00:02:30 EDT 2013 */
package org.oaqa.model.kb;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.oaqa.model.gerp.GerpTop;


/** 
 * Updated by JCasGen Tue Aug 13 23:03:25 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class Lemma extends GerpTop {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Lemma.class);
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
  protected Lemma() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Lemma(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Lemma(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: key

  /** getter for key - gets 
   * @generated */
  public String getKey() {
    if (Lemma_Type.featOkTst && ((Lemma_Type)jcasType).casFeat_key == null)
      jcasType.jcas.throwFeatMissing("key", "org.oaqa.model.kb.Lemma");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Lemma_Type)jcasType).casFeatCode_key);}
    
  /** setter for key - sets  
   * @generated */
  public void setKey(String v) {
    if (Lemma_Type.featOkTst && ((Lemma_Type)jcasType).casFeat_key == null)
      jcasType.jcas.throwFeatMissing("key", "org.oaqa.model.kb.Lemma");
    jcasType.ll_cas.ll_setStringValue(addr, ((Lemma_Type)jcasType).casFeatCode_key, v);}    
   
    
  //*--------------*
  //* Feature: posTag

  /** getter for posTag - gets 
   * @generated */
  public String getPosTag() {
    if (Lemma_Type.featOkTst && ((Lemma_Type)jcasType).casFeat_posTag == null)
      jcasType.jcas.throwFeatMissing("posTag", "org.oaqa.model.kb.Lemma");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Lemma_Type)jcasType).casFeatCode_posTag);}
    
  /** setter for posTag - sets  
   * @generated */
  public void setPosTag(String v) {
    if (Lemma_Type.featOkTst && ((Lemma_Type)jcasType).casFeat_posTag == null)
      jcasType.jcas.throwFeatMissing("posTag", "org.oaqa.model.kb.Lemma");
    jcasType.ll_cas.ll_setStringValue(addr, ((Lemma_Type)jcasType).casFeatCode_posTag, v);}    
  }

    