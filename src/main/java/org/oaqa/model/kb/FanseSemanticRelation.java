

/* First created by JCasGen Tue Aug 13 23:02:56 EDT 2013 */
package org.oaqa.model.kb;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.oaqa.model.gerp.GerpTop;
import org.oaqa.model.nlp.FanseToken;


/** 
 * Updated by JCasGen Wed Aug 14 15:53:56 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class FanseSemanticRelation extends GerpTop {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(FanseSemanticRelation.class);
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
  protected FanseSemanticRelation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FanseSemanticRelation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FanseSemanticRelation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: semanticAnnotation

  /** getter for semanticAnnotation - gets 
   * @generated */
  public String getSemanticAnnotation() {
    if (FanseSemanticRelation_Type.featOkTst && ((FanseSemanticRelation_Type)jcasType).casFeat_semanticAnnotation == null)
      jcasType.jcas.throwFeatMissing("semanticAnnotation", "org.oaqa.model.kb.FanseSemanticRelation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FanseSemanticRelation_Type)jcasType).casFeatCode_semanticAnnotation);}
    
  /** setter for semanticAnnotation - sets  
   * @generated */
  public void setSemanticAnnotation(String v) {
    if (FanseSemanticRelation_Type.featOkTst && ((FanseSemanticRelation_Type)jcasType).casFeat_semanticAnnotation == null)
      jcasType.jcas.throwFeatMissing("semanticAnnotation", "org.oaqa.model.kb.FanseSemanticRelation");
    jcasType.ll_cas.ll_setStringValue(addr, ((FanseSemanticRelation_Type)jcasType).casFeatCode_semanticAnnotation, v);}    
   
    
  //*--------------*
  //* Feature: head

  /** getter for head - gets 
   * @generated */
  public FanseToken getHead() {
    if (FanseSemanticRelation_Type.featOkTst && ((FanseSemanticRelation_Type)jcasType).casFeat_head == null)
      jcasType.jcas.throwFeatMissing("head", "org.oaqa.model.kb.FanseSemanticRelation");
    return (FanseToken)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FanseSemanticRelation_Type)jcasType).casFeatCode_head)));}
    
  /** setter for head - sets  
   * @generated */
  public void setHead(FanseToken v) {
    if (FanseSemanticRelation_Type.featOkTst && ((FanseSemanticRelation_Type)jcasType).casFeat_head == null)
      jcasType.jcas.throwFeatMissing("head", "org.oaqa.model.kb.FanseSemanticRelation");
    jcasType.ll_cas.ll_setRefValue(addr, ((FanseSemanticRelation_Type)jcasType).casFeatCode_head, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: child

  /** getter for child - gets 
   * @generated */
  public FanseToken getChild() {
    if (FanseSemanticRelation_Type.featOkTst && ((FanseSemanticRelation_Type)jcasType).casFeat_child == null)
      jcasType.jcas.throwFeatMissing("child", "org.oaqa.model.kb.FanseSemanticRelation");
    return (FanseToken)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FanseSemanticRelation_Type)jcasType).casFeatCode_child)));}
    
  /** setter for child - sets  
   * @generated */
  public void setChild(FanseToken v) {
    if (FanseSemanticRelation_Type.featOkTst && ((FanseSemanticRelation_Type)jcasType).casFeat_child == null)
      jcasType.jcas.throwFeatMissing("child", "org.oaqa.model.kb.FanseSemanticRelation");
    jcasType.ll_cas.ll_setRefValue(addr, ((FanseSemanticRelation_Type)jcasType).casFeatCode_child, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    