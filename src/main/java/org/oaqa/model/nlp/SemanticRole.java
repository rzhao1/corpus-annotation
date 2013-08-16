

/* First created by JCasGen Wed Jul 17 21:01:03 EDT 2013 */
package org.oaqa.model.nlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.oaqa.model.kb.RelationMention;
import org.oaqa.model.gerp.GerpAnnotation;


/** A semantic role label.
 * Updated by JCasGen Wed Aug 14 15:53:56 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class SemanticRole extends GerpAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SemanticRole.class);
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
  protected SemanticRole() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SemanticRole(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SemanticRole(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SemanticRole(JCas jcas, int begin, int end) {
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
  //* Feature: label

  /** getter for label - gets The semantic role label.
   * @generated */
  public String getLabel() {
    if (SemanticRole_Type.featOkTst && ((SemanticRole_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "org.oaqa.model.nlp.SemanticRole");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SemanticRole_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets The semantic role label. 
   * @generated */
  public void setLabel(String v) {
    if (SemanticRole_Type.featOkTst && ((SemanticRole_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "org.oaqa.model.nlp.SemanticRole");
    jcasType.ll_cas.ll_setStringValue(addr, ((SemanticRole_Type)jcasType).casFeatCode_label, v);}    
   
    
  //*--------------*
  //* Feature: relation

  /** getter for relation - gets 
   * @generated */
  public RelationMention getRelation() {
    if (SemanticRole_Type.featOkTst && ((SemanticRole_Type)jcasType).casFeat_relation == null)
      jcasType.jcas.throwFeatMissing("relation", "org.oaqa.model.nlp.SemanticRole");
    return (RelationMention)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SemanticRole_Type)jcasType).casFeatCode_relation)));}
    
  /** setter for relation - sets  
   * @generated */
  public void setRelation(RelationMention v) {
    if (SemanticRole_Type.featOkTst && ((SemanticRole_Type)jcasType).casFeat_relation == null)
      jcasType.jcas.throwFeatMissing("relation", "org.oaqa.model.nlp.SemanticRole");
    jcasType.ll_cas.ll_setRefValue(addr, ((SemanticRole_Type)jcasType).casFeatCode_relation, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    