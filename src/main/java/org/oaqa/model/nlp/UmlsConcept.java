

/* First created by JCasGen Fri Aug 02 02:50:43 EDT 2013 */
package org.oaqa.model.nlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Wed Aug 14 15:53:56 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class UmlsConcept extends OntologyConcept {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(UmlsConcept.class);
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
  protected UmlsConcept() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public UmlsConcept(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public UmlsConcept(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: cui

  /** getter for cui - gets 
   * @generated */
  public String getCui() {
    if (UmlsConcept_Type.featOkTst && ((UmlsConcept_Type)jcasType).casFeat_cui == null)
      jcasType.jcas.throwFeatMissing("cui", "org.oaqa.model.nlp.UmlsConcept");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UmlsConcept_Type)jcasType).casFeatCode_cui);}
    
  /** setter for cui - sets  
   * @generated */
  public void setCui(String v) {
    if (UmlsConcept_Type.featOkTst && ((UmlsConcept_Type)jcasType).casFeat_cui == null)
      jcasType.jcas.throwFeatMissing("cui", "org.oaqa.model.nlp.UmlsConcept");
    jcasType.ll_cas.ll_setStringValue(addr, ((UmlsConcept_Type)jcasType).casFeatCode_cui, v);}    
   
    
  //*--------------*
  //* Feature: tui

  /** getter for tui - gets 
   * @generated */
  public String getTui() {
    if (UmlsConcept_Type.featOkTst && ((UmlsConcept_Type)jcasType).casFeat_tui == null)
      jcasType.jcas.throwFeatMissing("tui", "org.oaqa.model.nlp.UmlsConcept");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UmlsConcept_Type)jcasType).casFeatCode_tui);}
    
  /** setter for tui - sets  
   * @generated */
  public void setTui(String v) {
    if (UmlsConcept_Type.featOkTst && ((UmlsConcept_Type)jcasType).casFeat_tui == null)
      jcasType.jcas.throwFeatMissing("tui", "org.oaqa.model.nlp.UmlsConcept");
    jcasType.ll_cas.ll_setStringValue(addr, ((UmlsConcept_Type)jcasType).casFeatCode_tui, v);}    
  }

    