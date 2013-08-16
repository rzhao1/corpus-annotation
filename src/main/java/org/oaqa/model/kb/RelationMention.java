

/* First created by JCasGen Wed Jul 17 21:01:03 EDT 2013 */
package org.oaqa.model.kb;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.oaqa.model.nlp.SemanticRole;
import org.apache.uima.jcas.cas.FSList;


/** A named relation mention that identify or define the named relation concept.
 * Updated by JCasGen Wed Aug 14 15:53:56 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class RelationMention extends ConceptMention {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(RelationMention.class);
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
  protected RelationMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public RelationMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public RelationMention(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public RelationMention(JCas jcas, int begin, int end) {
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
  //* Feature: argumentMentions

  /** getter for argumentMentions - gets A list of entity mentions being the arguments (e.g. sub, obj, etc.) of the relation mention.
   * @generated */
  public FSList getArgumentMentions() {
    if (RelationMention_Type.featOkTst && ((RelationMention_Type)jcasType).casFeat_argumentMentions == null)
      jcasType.jcas.throwFeatMissing("argumentMentions", "org.oaqa.model.kb.RelationMention");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((RelationMention_Type)jcasType).casFeatCode_argumentMentions)));}
    
  /** setter for argumentMentions - sets A list of entity mentions being the arguments (e.g. sub, obj, etc.) of the relation mention. 
   * @generated */
  public void setArgumentMentions(FSList v) {
    if (RelationMention_Type.featOkTst && ((RelationMention_Type)jcasType).casFeat_argumentMentions == null)
      jcasType.jcas.throwFeatMissing("argumentMentions", "org.oaqa.model.kb.RelationMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((RelationMention_Type)jcasType).casFeatCode_argumentMentions, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: eventMention

  /** getter for eventMention - gets 
   * @generated */
  public EventMention getEventMention() {
    if (RelationMention_Type.featOkTst && ((RelationMention_Type)jcasType).casFeat_eventMention == null)
      jcasType.jcas.throwFeatMissing("eventMention", "org.oaqa.model.kb.RelationMention");
    return (EventMention)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((RelationMention_Type)jcasType).casFeatCode_eventMention)));}
    
  /** setter for eventMention - sets  
   * @generated */
  public void setEventMention(EventMention v) {
    if (RelationMention_Type.featOkTst && ((RelationMention_Type)jcasType).casFeat_eventMention == null)
      jcasType.jcas.throwFeatMissing("eventMention", "org.oaqa.model.kb.RelationMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((RelationMention_Type)jcasType).casFeatCode_eventMention, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: semanticArgument

  /** getter for semanticArgument - gets 
   * @generated */
  public SemanticRole getSemanticArgument() {
    if (RelationMention_Type.featOkTst && ((RelationMention_Type)jcasType).casFeat_semanticArgument == null)
      jcasType.jcas.throwFeatMissing("semanticArgument", "org.oaqa.model.kb.RelationMention");
    return (SemanticRole)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((RelationMention_Type)jcasType).casFeatCode_semanticArgument)));}
    
  /** setter for semanticArgument - sets  
   * @generated */
  public void setSemanticArgument(SemanticRole v) {
    if (RelationMention_Type.featOkTst && ((RelationMention_Type)jcasType).casFeat_semanticArgument == null)
      jcasType.jcas.throwFeatMissing("semanticArgument", "org.oaqa.model.kb.RelationMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((RelationMention_Type)jcasType).casFeatCode_semanticArgument, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    