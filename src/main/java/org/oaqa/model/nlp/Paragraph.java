

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
public class Paragraph extends GerpAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Paragraph.class);
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
  protected Paragraph() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Paragraph(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Paragraph(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Paragraph(JCas jcas, int begin, int end) {
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
    if (Paragraph_Type.featOkTst && ((Paragraph_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "org.oaqa.model.nlp.Paragraph");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Paragraph_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated */
  public void setId(int v) {
    if (Paragraph_Type.featOkTst && ((Paragraph_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "org.oaqa.model.nlp.Paragraph");
    jcasType.ll_cas.ll_setIntValue(addr, ((Paragraph_Type)jcasType).casFeatCode_id, v);}    
  }

    