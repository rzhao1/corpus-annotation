

/* First created by JCasGen Tue Aug 13 23:02:56 EDT 2013 */
package org.oaqa.model.nlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.oaqa.model.gerp.GerpAnnotation;


/** 
 * Updated by JCasGen Wed Aug 14 15:53:56 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class FanseToken extends GerpAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(FanseToken.class);
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
  protected FanseToken() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FanseToken(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FanseToken(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public FanseToken(JCas jcas, int begin, int end) {
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
  //* Feature: coarsePos

  /** getter for coarsePos - gets 
   * @generated */
  public String getCoarsePos() {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_coarsePos == null)
      jcasType.jcas.throwFeatMissing("coarsePos", "org.oaqa.model.nlp.FanseToken");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FanseToken_Type)jcasType).casFeatCode_coarsePos);}
    
  /** setter for coarsePos - sets  
   * @generated */
  public void setCoarsePos(String v) {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_coarsePos == null)
      jcasType.jcas.throwFeatMissing("coarsePos", "org.oaqa.model.nlp.FanseToken");
    jcasType.ll_cas.ll_setStringValue(addr, ((FanseToken_Type)jcasType).casFeatCode_coarsePos, v);}    
   
    
  //*--------------*
  //* Feature: pos

  /** getter for pos - gets 
   * @generated */
  public String getPos() {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "org.oaqa.model.nlp.FanseToken");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FanseToken_Type)jcasType).casFeatCode_pos);}
    
  /** setter for pos - sets  
   * @generated */
  public void setPos(String v) {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "org.oaqa.model.nlp.FanseToken");
    jcasType.ll_cas.ll_setStringValue(addr, ((FanseToken_Type)jcasType).casFeatCode_pos, v);}    
   
    
  //*--------------*
  //* Feature: lexicalSense

  /** getter for lexicalSense - gets 
   * @generated */
  public String getLexicalSense() {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_lexicalSense == null)
      jcasType.jcas.throwFeatMissing("lexicalSense", "org.oaqa.model.nlp.FanseToken");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FanseToken_Type)jcasType).casFeatCode_lexicalSense);}
    
  /** setter for lexicalSense - sets  
   * @generated */
  public void setLexicalSense(String v) {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_lexicalSense == null)
      jcasType.jcas.throwFeatMissing("lexicalSense", "org.oaqa.model.nlp.FanseToken");
    jcasType.ll_cas.ll_setStringValue(addr, ((FanseToken_Type)jcasType).casFeatCode_lexicalSense, v);}    
   
    
  //*--------------*
  //* Feature: headDependencyRelations

  /** getter for headDependencyRelations - gets 
   * @generated */
  public FSList getHeadDependencyRelations() {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_headDependencyRelations == null)
      jcasType.jcas.throwFeatMissing("headDependencyRelations", "org.oaqa.model.nlp.FanseToken");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FanseToken_Type)jcasType).casFeatCode_headDependencyRelations)));}
    
  /** setter for headDependencyRelations - sets  
   * @generated */
  public void setHeadDependencyRelations(FSList v) {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_headDependencyRelations == null)
      jcasType.jcas.throwFeatMissing("headDependencyRelations", "org.oaqa.model.nlp.FanseToken");
    jcasType.ll_cas.ll_setRefValue(addr, ((FanseToken_Type)jcasType).casFeatCode_headDependencyRelations, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: childDependencyRelations

  /** getter for childDependencyRelations - gets 
   * @generated */
  public FSList getChildDependencyRelations() {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_childDependencyRelations == null)
      jcasType.jcas.throwFeatMissing("childDependencyRelations", "org.oaqa.model.nlp.FanseToken");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FanseToken_Type)jcasType).casFeatCode_childDependencyRelations)));}
    
  /** setter for childDependencyRelations - sets  
   * @generated */
  public void setChildDependencyRelations(FSList v) {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_childDependencyRelations == null)
      jcasType.jcas.throwFeatMissing("childDependencyRelations", "org.oaqa.model.nlp.FanseToken");
    jcasType.ll_cas.ll_setRefValue(addr, ((FanseToken_Type)jcasType).casFeatCode_childDependencyRelations, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: headSemanticRelations

  /** getter for headSemanticRelations - gets 
   * @generated */
  public FSList getHeadSemanticRelations() {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_headSemanticRelations == null)
      jcasType.jcas.throwFeatMissing("headSemanticRelations", "org.oaqa.model.nlp.FanseToken");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FanseToken_Type)jcasType).casFeatCode_headSemanticRelations)));}
    
  /** setter for headSemanticRelations - sets  
   * @generated */
  public void setHeadSemanticRelations(FSList v) {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_headSemanticRelations == null)
      jcasType.jcas.throwFeatMissing("headSemanticRelations", "org.oaqa.model.nlp.FanseToken");
    jcasType.ll_cas.ll_setRefValue(addr, ((FanseToken_Type)jcasType).casFeatCode_headSemanticRelations, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: childSemanticRelations

  /** getter for childSemanticRelations - gets 
   * @generated */
  public FSList getChildSemanticRelations() {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_childSemanticRelations == null)
      jcasType.jcas.throwFeatMissing("childSemanticRelations", "org.oaqa.model.nlp.FanseToken");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FanseToken_Type)jcasType).casFeatCode_childSemanticRelations)));}
    
  /** setter for childSemanticRelations - sets  
   * @generated */
  public void setChildSemanticRelations(FSList v) {
    if (FanseToken_Type.featOkTst && ((FanseToken_Type)jcasType).casFeat_childSemanticRelations == null)
      jcasType.jcas.throwFeatMissing("childSemanticRelations", "org.oaqa.model.nlp.FanseToken");
    jcasType.ll_cas.ll_setRefValue(addr, ((FanseToken_Type)jcasType).casFeatCode_childSemanticRelations, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    