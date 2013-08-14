
/* First created by JCasGen Tue Aug 13 23:02:56 EDT 2013 */
package org.oaqa.model.nlp;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.oaqa.model.gerp.GerpAnnotation_Type;

/** 
 * Updated by JCasGen Tue Aug 13 23:03:25 EDT 2013
 * @generated */
public class FanseToken_Type extends GerpAnnotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (FanseToken_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = FanseToken_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new FanseToken(addr, FanseToken_Type.this);
  			   FanseToken_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new FanseToken(addr, FanseToken_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = FanseToken.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.oaqa.model.nlp.FanseToken");
 
  /** @generated */
  final Feature casFeat_coarsePos;
  /** @generated */
  final int     casFeatCode_coarsePos;
  /** @generated */ 
  public String getCoarsePos(int addr) {
        if (featOkTst && casFeat_coarsePos == null)
      jcas.throwFeatMissing("coarsePos", "org.oaqa.model.nlp.FanseToken");
    return ll_cas.ll_getStringValue(addr, casFeatCode_coarsePos);
  }
  /** @generated */    
  public void setCoarsePos(int addr, String v) {
        if (featOkTst && casFeat_coarsePos == null)
      jcas.throwFeatMissing("coarsePos", "org.oaqa.model.nlp.FanseToken");
    ll_cas.ll_setStringValue(addr, casFeatCode_coarsePos, v);}
    
  
 
  /** @generated */
  final Feature casFeat_pos;
  /** @generated */
  final int     casFeatCode_pos;
  /** @generated */ 
  public String getPos(int addr) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "org.oaqa.model.nlp.FanseToken");
    return ll_cas.ll_getStringValue(addr, casFeatCode_pos);
  }
  /** @generated */    
  public void setPos(int addr, String v) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "org.oaqa.model.nlp.FanseToken");
    ll_cas.ll_setStringValue(addr, casFeatCode_pos, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lexicalSense;
  /** @generated */
  final int     casFeatCode_lexicalSense;
  /** @generated */ 
  public String getLexicalSense(int addr) {
        if (featOkTst && casFeat_lexicalSense == null)
      jcas.throwFeatMissing("lexicalSense", "org.oaqa.model.nlp.FanseToken");
    return ll_cas.ll_getStringValue(addr, casFeatCode_lexicalSense);
  }
  /** @generated */    
  public void setLexicalSense(int addr, String v) {
        if (featOkTst && casFeat_lexicalSense == null)
      jcas.throwFeatMissing("lexicalSense", "org.oaqa.model.nlp.FanseToken");
    ll_cas.ll_setStringValue(addr, casFeatCode_lexicalSense, v);}
    
  
 
  /** @generated */
  final Feature casFeat_headDependencyRelations;
  /** @generated */
  final int     casFeatCode_headDependencyRelations;
  /** @generated */ 
  public int getHeadDependencyRelations(int addr) {
        if (featOkTst && casFeat_headDependencyRelations == null)
      jcas.throwFeatMissing("headDependencyRelations", "org.oaqa.model.nlp.FanseToken");
    return ll_cas.ll_getRefValue(addr, casFeatCode_headDependencyRelations);
  }
  /** @generated */    
  public void setHeadDependencyRelations(int addr, int v) {
        if (featOkTst && casFeat_headDependencyRelations == null)
      jcas.throwFeatMissing("headDependencyRelations", "org.oaqa.model.nlp.FanseToken");
    ll_cas.ll_setRefValue(addr, casFeatCode_headDependencyRelations, v);}
    
  
 
  /** @generated */
  final Feature casFeat_childDependencyRelations;
  /** @generated */
  final int     casFeatCode_childDependencyRelations;
  /** @generated */ 
  public int getChildDependencyRelations(int addr) {
        if (featOkTst && casFeat_childDependencyRelations == null)
      jcas.throwFeatMissing("childDependencyRelations", "org.oaqa.model.nlp.FanseToken");
    return ll_cas.ll_getRefValue(addr, casFeatCode_childDependencyRelations);
  }
  /** @generated */    
  public void setChildDependencyRelations(int addr, int v) {
        if (featOkTst && casFeat_childDependencyRelations == null)
      jcas.throwFeatMissing("childDependencyRelations", "org.oaqa.model.nlp.FanseToken");
    ll_cas.ll_setRefValue(addr, casFeatCode_childDependencyRelations, v);}
    
  
 
  /** @generated */
  final Feature casFeat_headSemanticRelations;
  /** @generated */
  final int     casFeatCode_headSemanticRelations;
  /** @generated */ 
  public int getHeadSemanticRelations(int addr) {
        if (featOkTst && casFeat_headSemanticRelations == null)
      jcas.throwFeatMissing("headSemanticRelations", "org.oaqa.model.nlp.FanseToken");
    return ll_cas.ll_getRefValue(addr, casFeatCode_headSemanticRelations);
  }
  /** @generated */    
  public void setHeadSemanticRelations(int addr, int v) {
        if (featOkTst && casFeat_headSemanticRelations == null)
      jcas.throwFeatMissing("headSemanticRelations", "org.oaqa.model.nlp.FanseToken");
    ll_cas.ll_setRefValue(addr, casFeatCode_headSemanticRelations, v);}
    
  
 
  /** @generated */
  final Feature casFeat_childSemanticRelations;
  /** @generated */
  final int     casFeatCode_childSemanticRelations;
  /** @generated */ 
  public int getChildSemanticRelations(int addr) {
        if (featOkTst && casFeat_childSemanticRelations == null)
      jcas.throwFeatMissing("childSemanticRelations", "org.oaqa.model.nlp.FanseToken");
    return ll_cas.ll_getRefValue(addr, casFeatCode_childSemanticRelations);
  }
  /** @generated */    
  public void setChildSemanticRelations(int addr, int v) {
        if (featOkTst && casFeat_childSemanticRelations == null)
      jcas.throwFeatMissing("childSemanticRelations", "org.oaqa.model.nlp.FanseToken");
    ll_cas.ll_setRefValue(addr, casFeatCode_childSemanticRelations, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public FanseToken_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_coarsePos = jcas.getRequiredFeatureDE(casType, "coarsePos", "uima.cas.String", featOkTst);
    casFeatCode_coarsePos  = (null == casFeat_coarsePos) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_coarsePos).getCode();

 
    casFeat_pos = jcas.getRequiredFeatureDE(casType, "pos", "uima.cas.String", featOkTst);
    casFeatCode_pos  = (null == casFeat_pos) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pos).getCode();

 
    casFeat_lexicalSense = jcas.getRequiredFeatureDE(casType, "lexicalSense", "uima.cas.String", featOkTst);
    casFeatCode_lexicalSense  = (null == casFeat_lexicalSense) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lexicalSense).getCode();

 
    casFeat_headDependencyRelations = jcas.getRequiredFeatureDE(casType, "headDependencyRelations", "uima.cas.FSList", featOkTst);
    casFeatCode_headDependencyRelations  = (null == casFeat_headDependencyRelations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_headDependencyRelations).getCode();

 
    casFeat_childDependencyRelations = jcas.getRequiredFeatureDE(casType, "childDependencyRelations", "uima.cas.FSList", featOkTst);
    casFeatCode_childDependencyRelations  = (null == casFeat_childDependencyRelations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_childDependencyRelations).getCode();

 
    casFeat_headSemanticRelations = jcas.getRequiredFeatureDE(casType, "headSemanticRelations", "uima.cas.FSList", featOkTst);
    casFeatCode_headSemanticRelations  = (null == casFeat_headSemanticRelations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_headSemanticRelations).getCode();

 
    casFeat_childSemanticRelations = jcas.getRequiredFeatureDE(casType, "childSemanticRelations", "uima.cas.FSList", featOkTst);
    casFeatCode_childSemanticRelations  = (null == casFeat_childSemanticRelations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_childSemanticRelations).getCode();

  }
}



    