

/* First created by JCasGen Mon Jul 29 16:06:46 EDT 2013 */
package org.oaqa.model.nlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.oaqa.model.gerp.GerpAnnotation;


/** 
 * Updated by JCasGen Thu Aug 08 15:17:07 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class ConllDependencyNode extends GerpAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ConllDependencyNode.class);
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
  protected ConllDependencyNode() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ConllDependencyNode(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ConllDependencyNode(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ConllDependencyNode(JCas jcas, int begin, int end) {
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
     
 
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets 
   * @generated */
  public int getId() {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "org.oaqa.model.nlp.ConllDependencyNode");
    return jcasType.ll_cas.ll_getIntValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated */
  public void setId(int v) {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "org.oaqa.model.nlp.ConllDependencyNode");
    jcasType.ll_cas.ll_setIntValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: form

  /** getter for form - gets 
   * @generated */
  public String getForm() {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_form == null)
      jcasType.jcas.throwFeatMissing("form", "org.oaqa.model.nlp.ConllDependencyNode");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_form);}
    
  /** setter for form - sets  
   * @generated */
  public void setForm(String v) {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_form == null)
      jcasType.jcas.throwFeatMissing("form", "org.oaqa.model.nlp.ConllDependencyNode");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_form, v);}    
   
    
  //*--------------*
  //* Feature: lemma

  /** getter for lemma - gets 
   * @generated */
  public String getLemma() {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "org.oaqa.model.nlp.ConllDependencyNode");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets  
   * @generated */
  public void setLemma(String v) {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "org.oaqa.model.nlp.ConllDependencyNode");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_lemma, v);}    
   
    
  //*--------------*
  //* Feature: cpostag

  /** getter for cpostag - gets 
   * @generated */
  public String getCpostag() {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_cpostag == null)
      jcasType.jcas.throwFeatMissing("cpostag", "org.oaqa.model.nlp.ConllDependencyNode");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_cpostag);}
    
  /** setter for cpostag - sets  
   * @generated */
  public void setCpostag(String v) {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_cpostag == null)
      jcasType.jcas.throwFeatMissing("cpostag", "org.oaqa.model.nlp.ConllDependencyNode");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_cpostag, v);}    
   
    
  //*--------------*
  //* Feature: postag

  /** getter for postag - gets 
   * @generated */
  public String getPostag() {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_postag == null)
      jcasType.jcas.throwFeatMissing("postag", "org.oaqa.model.nlp.ConllDependencyNode");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_postag);}
    
  /** setter for postag - sets  
   * @generated */
  public void setPostag(String v) {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_postag == null)
      jcasType.jcas.throwFeatMissing("postag", "org.oaqa.model.nlp.ConllDependencyNode");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_postag, v);}    
   
    
  //*--------------*
  //* Feature: feats

  /** getter for feats - gets 
   * @generated */
  public String getFeats() {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_feats == null)
      jcasType.jcas.throwFeatMissing("feats", "org.oaqa.model.nlp.ConllDependencyNode");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_feats);}
    
  /** setter for feats - sets  
   * @generated */
  public void setFeats(String v) {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_feats == null)
      jcasType.jcas.throwFeatMissing("feats", "org.oaqa.model.nlp.ConllDependencyNode");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_feats, v);}    
   
    
  //*--------------*
  //* Feature: head

  /** getter for head - gets 
   * @generated */
  public ConllDependencyNode getHead() {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_head == null)
      jcasType.jcas.throwFeatMissing("head", "org.oaqa.model.nlp.ConllDependencyNode");
    return (ConllDependencyNode)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_head)));}
    
  /** setter for head - sets  
   * @generated */
  public void setHead(ConllDependencyNode v) {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_head == null)
      jcasType.jcas.throwFeatMissing("head", "org.oaqa.model.nlp.ConllDependencyNode");
    jcasType.ll_cas.ll_setRefValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_head, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: deprel

  /** getter for deprel - gets 
   * @generated */
  public String getDeprel() {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_deprel == null)
      jcasType.jcas.throwFeatMissing("deprel", "org.oaqa.model.nlp.ConllDependencyNode");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_deprel);}
    
  /** setter for deprel - sets  
   * @generated */
  public void setDeprel(String v) {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_deprel == null)
      jcasType.jcas.throwFeatMissing("deprel", "org.oaqa.model.nlp.ConllDependencyNode");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_deprel, v);}    
   
    
  //*--------------*
  //* Feature: phead

  /** getter for phead - gets 
   * @generated */
  public ConllDependencyNode getPhead() {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_phead == null)
      jcasType.jcas.throwFeatMissing("phead", "org.oaqa.model.nlp.ConllDependencyNode");
    return (ConllDependencyNode)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_phead)));}
    
  /** setter for phead - sets  
   * @generated */
  public void setPhead(ConllDependencyNode v) {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_phead == null)
      jcasType.jcas.throwFeatMissing("phead", "org.oaqa.model.nlp.ConllDependencyNode");
    jcasType.ll_cas.ll_setRefValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_phead, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: pdeprel

  /** getter for pdeprel - gets 
   * @generated */
  public String getPdeprel() {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_pdeprel == null)
      jcasType.jcas.throwFeatMissing("pdeprel", "org.oaqa.model.nlp.ConllDependencyNode");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_pdeprel);}
    
  /** setter for pdeprel - sets  
   * @generated */
  public void setPdeprel(String v) {
    if (ConllDependencyNode_Type.featOkTst && ((ConllDependencyNode_Type)jcasType).casFeat_pdeprel == null)
      jcasType.jcas.throwFeatMissing("pdeprel", "org.oaqa.model.nlp.ConllDependencyNode");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConllDependencyNode_Type)jcasType).casFeatCode_pdeprel, v);}    
  }

    