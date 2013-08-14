

/* First created by JCasGen Tue Aug 13 23:02:56 EDT 2013 */
package org.oaqa.model.kb;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.oaqa.model.gerp.GerpTop;
import org.oaqa.model.nlp.FanseToken;


/** 
 * Updated by JCasGen Tue Aug 13 23:03:25 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class FanseDependencyRelation extends GerpTop {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(FanseDependencyRelation.class);
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
  protected FanseDependencyRelation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FanseDependencyRelation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FanseDependencyRelation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: head

  /** getter for head - gets 
   * @generated */
  public FanseToken getHead() {
    if (FanseDependencyRelation_Type.featOkTst && ((FanseDependencyRelation_Type)jcasType).casFeat_head == null)
      jcasType.jcas.throwFeatMissing("head", "org.oaqa.model.kb.FanseDependencyRelation");
    return (FanseToken)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FanseDependencyRelation_Type)jcasType).casFeatCode_head)));}
    
  /** setter for head - sets  
   * @generated */
  public void setHead(FanseToken v) {
    if (FanseDependencyRelation_Type.featOkTst && ((FanseDependencyRelation_Type)jcasType).casFeat_head == null)
      jcasType.jcas.throwFeatMissing("head", "org.oaqa.model.kb.FanseDependencyRelation");
    jcasType.ll_cas.ll_setRefValue(addr, ((FanseDependencyRelation_Type)jcasType).casFeatCode_head, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: child

  /** getter for child - gets 
   * @generated */
  public FanseToken getChild() {
    if (FanseDependencyRelation_Type.featOkTst && ((FanseDependencyRelation_Type)jcasType).casFeat_child == null)
      jcasType.jcas.throwFeatMissing("child", "org.oaqa.model.kb.FanseDependencyRelation");
    return (FanseToken)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FanseDependencyRelation_Type)jcasType).casFeatCode_child)));}
    
  /** setter for child - sets  
   * @generated */
  public void setChild(FanseToken v) {
    if (FanseDependencyRelation_Type.featOkTst && ((FanseDependencyRelation_Type)jcasType).casFeat_child == null)
      jcasType.jcas.throwFeatMissing("child", "org.oaqa.model.kb.FanseDependencyRelation");
    jcasType.ll_cas.ll_setRefValue(addr, ((FanseDependencyRelation_Type)jcasType).casFeatCode_child, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: dependency

  /** getter for dependency - gets 
   * @generated */
  public String getDependency() {
    if (FanseDependencyRelation_Type.featOkTst && ((FanseDependencyRelation_Type)jcasType).casFeat_dependency == null)
      jcasType.jcas.throwFeatMissing("dependency", "org.oaqa.model.kb.FanseDependencyRelation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FanseDependencyRelation_Type)jcasType).casFeatCode_dependency);}
    
  /** setter for dependency - sets  
   * @generated */
  public void setDependency(String v) {
    if (FanseDependencyRelation_Type.featOkTst && ((FanseDependencyRelation_Type)jcasType).casFeat_dependency == null)
      jcasType.jcas.throwFeatMissing("dependency", "org.oaqa.model.kb.FanseDependencyRelation");
    jcasType.ll_cas.ll_setStringValue(addr, ((FanseDependencyRelation_Type)jcasType).casFeatCode_dependency, v);}    
  }

    