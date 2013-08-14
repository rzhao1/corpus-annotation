

/* First created by JCasGen Wed Jul 17 21:01:03 EDT 2013 */
package org.oaqa.model.gerp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.oaqa.model.core.OAQATop;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.StringArray;


import org.apache.uima.jcas.cas.StringList;


/** The base class for Gerp feature structures that are not Annotations, with G/E/E/P features defined.
 * Updated by JCasGen Tue Aug 13 23:03:25 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class GerpTop extends OAQATop {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GerpTop.class);
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
  protected GerpTop() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public GerpTop(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public GerpTop(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: generators

  /** getter for generators - gets An array of genenator names that nominee this feature structure to be the candidate of a certain targeted entity.
   * @generated */
  public StringList getGenerators() {
    if (GerpTop_Type.featOkTst && ((GerpTop_Type)jcasType).casFeat_generators == null)
      jcasType.jcas.throwFeatMissing("generators", "org.oaqa.model.gerp.GerpTop");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((GerpTop_Type)jcasType).casFeatCode_generators)));}
    
  /** setter for generators - sets An array of genenator names that nominee this feature structure to be the candidate of a certain targeted entity. 
   * @generated */
  public void setGenerators(StringList v) {
    if (GerpTop_Type.featOkTst && ((GerpTop_Type)jcasType).casFeat_generators == null)
      jcasType.jcas.throwFeatMissing("generators", "org.oaqa.model.gerp.GerpTop");
    jcasType.ll_cas.ll_setRefValue(addr, ((GerpTop_Type)jcasType).casFeatCode_generators, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: evidences

  /** getter for evidences - gets An array of evidences generated by evidencers for a particular GERP feature structure. Each element corresponds to the evidence from an evidencer.
   * @generated */
  public FSList getEvidences() {
    if (GerpTop_Type.featOkTst && ((GerpTop_Type)jcasType).casFeat_evidences == null)
      jcasType.jcas.throwFeatMissing("evidences", "org.oaqa.model.gerp.GerpTop");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((GerpTop_Type)jcasType).casFeatCode_evidences)));}
    
  /** setter for evidences - sets An array of evidences generated by evidencers for a particular GERP feature structure. Each element corresponds to the evidence from an evidencer. 
   * @generated */
  public void setEvidences(FSList v) {
    if (GerpTop_Type.featOkTst && ((GerpTop_Type)jcasType).casFeat_evidences == null)
      jcasType.jcas.throwFeatMissing("evidences", "org.oaqa.model.gerp.GerpTop");
    jcasType.ll_cas.ll_setRefValue(addr, ((GerpTop_Type)jcasType).casFeatCode_evidences, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: ranks

  /** getter for ranks - gets An array of ranks generated by rankers for a particular GERP feature structure. Each element corresponds to the rank from a ranker.
   * @generated */
  public FSList getRanks() {
    if (GerpTop_Type.featOkTst && ((GerpTop_Type)jcasType).casFeat_ranks == null)
      jcasType.jcas.throwFeatMissing("ranks", "org.oaqa.model.gerp.GerpTop");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((GerpTop_Type)jcasType).casFeatCode_ranks)));}
    
  /** setter for ranks - sets An array of ranks generated by rankers for a particular GERP feature structure. Each element corresponds to the rank from a ranker. 
   * @generated */
  public void setRanks(FSList v) {
    if (GerpTop_Type.featOkTst && ((GerpTop_Type)jcasType).casFeat_ranks == null)
      jcasType.jcas.throwFeatMissing("ranks", "org.oaqa.model.gerp.GerpTop");
    jcasType.ll_cas.ll_setRefValue(addr, ((GerpTop_Type)jcasType).casFeatCode_ranks, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: pruningDecisions

  /** getter for pruningDecisions - gets An array of purning decisions generated by pruners for a particular GERP feature structure. Each element corresponds to the pruning decision from a pruner. The final decision will be made by a multiplexer (or phrase driver).
   * @generated */
  public FSList getPruningDecisions() {
    if (GerpTop_Type.featOkTst && ((GerpTop_Type)jcasType).casFeat_pruningDecisions == null)
      jcasType.jcas.throwFeatMissing("pruningDecisions", "org.oaqa.model.gerp.GerpTop");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((GerpTop_Type)jcasType).casFeatCode_pruningDecisions)));}
    
  /** setter for pruningDecisions - sets An array of purning decisions generated by pruners for a particular GERP feature structure. Each element corresponds to the pruning decision from a pruner. The final decision will be made by a multiplexer (or phrase driver). 
   * @generated */
  public void setPruningDecisions(FSList v) {
    if (GerpTop_Type.featOkTst && ((GerpTop_Type)jcasType).casFeat_pruningDecisions == null)
      jcasType.jcas.throwFeatMissing("pruningDecisions", "org.oaqa.model.gerp.GerpTop");
    jcasType.ll_cas.ll_setRefValue(addr, ((GerpTop_Type)jcasType).casFeatCode_pruningDecisions, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    