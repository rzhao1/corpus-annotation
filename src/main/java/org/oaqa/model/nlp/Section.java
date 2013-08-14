

/* First created by JCasGen Mon Jul 22 21:07:28 EDT 2013 */
package org.oaqa.model.nlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.oaqa.model.gerp.GerpAnnotation;


/** 
 * Updated by JCasGen Tue Aug 13 23:03:25 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class Section extends GerpAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Section.class);
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
  protected Section() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Section(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Section(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Section(JCas jcas, int begin, int end) {
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
  //* Feature: title

  /** getter for title - gets 
   * @generated */
  public String getTitle() {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "org.oaqa.model.nlp.Section");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Section_Type)jcasType).casFeatCode_title);}
    
  /** setter for title - sets  
   * @generated */
  public void setTitle(String v) {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "org.oaqa.model.nlp.Section");
    jcasType.ll_cas.ll_setStringValue(addr, ((Section_Type)jcasType).casFeatCode_title, v);}    
   
    
  //*--------------*
  //* Feature: depth

  /** getter for depth - gets 
   * @generated */
  public int getDepth() {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_depth == null)
      jcasType.jcas.throwFeatMissing("depth", "org.oaqa.model.nlp.Section");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Section_Type)jcasType).casFeatCode_depth);}
    
  /** setter for depth - sets  
   * @generated */
  public void setDepth(int v) {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_depth == null)
      jcasType.jcas.throwFeatMissing("depth", "org.oaqa.model.nlp.Section");
    jcasType.ll_cas.ll_setIntValue(addr, ((Section_Type)jcasType).casFeatCode_depth, v);}    
  }

    