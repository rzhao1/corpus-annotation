

/* First created by JCasGen Fri Aug 02 02:50:43 EDT 2013 */
package org.oaqa.model.nlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.oaqa.model.gerp.GerpTop;


/** 
 * Updated by JCasGen Thu Aug 08 15:17:07 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class OntologyConcept extends GerpTop {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(OntologyConcept.class);
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
  protected OntologyConcept() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public OntologyConcept(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public OntologyConcept(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: codingScheme

  /** getter for codingScheme - gets 
   * @generated */
  public String getCodingScheme() {
    if (OntologyConcept_Type.featOkTst && ((OntologyConcept_Type)jcasType).casFeat_codingScheme == null)
      jcasType.jcas.throwFeatMissing("codingScheme", "org.oaqa.model.nlp.OntologyConcept");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OntologyConcept_Type)jcasType).casFeatCode_codingScheme);}
    
  /** setter for codingScheme - sets  
   * @generated */
  public void setCodingScheme(String v) {
    if (OntologyConcept_Type.featOkTst && ((OntologyConcept_Type)jcasType).casFeat_codingScheme == null)
      jcasType.jcas.throwFeatMissing("codingScheme", "org.oaqa.model.nlp.OntologyConcept");
    jcasType.ll_cas.ll_setStringValue(addr, ((OntologyConcept_Type)jcasType).casFeatCode_codingScheme, v);}    
   
    
  //*--------------*
  //* Feature: code

  /** getter for code - gets 
   * @generated */
  public String getCode() {
    if (OntologyConcept_Type.featOkTst && ((OntologyConcept_Type)jcasType).casFeat_code == null)
      jcasType.jcas.throwFeatMissing("code", "org.oaqa.model.nlp.OntologyConcept");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OntologyConcept_Type)jcasType).casFeatCode_code);}
    
  /** setter for code - sets  
   * @generated */
  public void setCode(String v) {
    if (OntologyConcept_Type.featOkTst && ((OntologyConcept_Type)jcasType).casFeat_code == null)
      jcasType.jcas.throwFeatMissing("code", "org.oaqa.model.nlp.OntologyConcept");
    jcasType.ll_cas.ll_setStringValue(addr, ((OntologyConcept_Type)jcasType).casFeatCode_code, v);}    
   
    
  //*--------------*
  //* Feature: oid

  /** getter for oid - gets 
   * @generated */
  public String getOid() {
    if (OntologyConcept_Type.featOkTst && ((OntologyConcept_Type)jcasType).casFeat_oid == null)
      jcasType.jcas.throwFeatMissing("oid", "org.oaqa.model.nlp.OntologyConcept");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OntologyConcept_Type)jcasType).casFeatCode_oid);}
    
  /** setter for oid - sets  
   * @generated */
  public void setOid(String v) {
    if (OntologyConcept_Type.featOkTst && ((OntologyConcept_Type)jcasType).casFeat_oid == null)
      jcasType.jcas.throwFeatMissing("oid", "org.oaqa.model.nlp.OntologyConcept");
    jcasType.ll_cas.ll_setStringValue(addr, ((OntologyConcept_Type)jcasType).casFeatCode_oid, v);}    
   
    
  //*--------------*
  //* Feature: oui

  /** getter for oui - gets 
   * @generated */
  public String getOui() {
    if (OntologyConcept_Type.featOkTst && ((OntologyConcept_Type)jcasType).casFeat_oui == null)
      jcasType.jcas.throwFeatMissing("oui", "org.oaqa.model.nlp.OntologyConcept");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OntologyConcept_Type)jcasType).casFeatCode_oui);}
    
  /** setter for oui - sets  
   * @generated */
  public void setOui(String v) {
    if (OntologyConcept_Type.featOkTst && ((OntologyConcept_Type)jcasType).casFeat_oui == null)
      jcasType.jcas.throwFeatMissing("oui", "org.oaqa.model.nlp.OntologyConcept");
    jcasType.ll_cas.ll_setStringValue(addr, ((OntologyConcept_Type)jcasType).casFeatCode_oui, v);}    
  }

    