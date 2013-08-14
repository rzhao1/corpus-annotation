
/* First created by JCasGen Tue Aug 13 23:02:56 EDT 2013 */
package org.oaqa.model.kb;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.oaqa.model.gerp.GerpTop_Type;

/** 
 * Updated by JCasGen Tue Aug 13 23:03:25 EDT 2013
 * @generated */
public class FanseDependencyRelation_Type extends GerpTop_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (FanseDependencyRelation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = FanseDependencyRelation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new FanseDependencyRelation(addr, FanseDependencyRelation_Type.this);
  			   FanseDependencyRelation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new FanseDependencyRelation(addr, FanseDependencyRelation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = FanseDependencyRelation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.oaqa.model.kb.FanseDependencyRelation");
 
  /** @generated */
  final Feature casFeat_head;
  /** @generated */
  final int     casFeatCode_head;
  /** @generated */ 
  public int getHead(int addr) {
        if (featOkTst && casFeat_head == null)
      jcas.throwFeatMissing("head", "org.oaqa.model.kb.FanseDependencyRelation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_head);
  }
  /** @generated */    
  public void setHead(int addr, int v) {
        if (featOkTst && casFeat_head == null)
      jcas.throwFeatMissing("head", "org.oaqa.model.kb.FanseDependencyRelation");
    ll_cas.ll_setRefValue(addr, casFeatCode_head, v);}
    
  
 
  /** @generated */
  final Feature casFeat_child;
  /** @generated */
  final int     casFeatCode_child;
  /** @generated */ 
  public int getChild(int addr) {
        if (featOkTst && casFeat_child == null)
      jcas.throwFeatMissing("child", "org.oaqa.model.kb.FanseDependencyRelation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_child);
  }
  /** @generated */    
  public void setChild(int addr, int v) {
        if (featOkTst && casFeat_child == null)
      jcas.throwFeatMissing("child", "org.oaqa.model.kb.FanseDependencyRelation");
    ll_cas.ll_setRefValue(addr, casFeatCode_child, v);}
    
  
 
  /** @generated */
  final Feature casFeat_dependency;
  /** @generated */
  final int     casFeatCode_dependency;
  /** @generated */ 
  public String getDependency(int addr) {
        if (featOkTst && casFeat_dependency == null)
      jcas.throwFeatMissing("dependency", "org.oaqa.model.kb.FanseDependencyRelation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_dependency);
  }
  /** @generated */    
  public void setDependency(int addr, String v) {
        if (featOkTst && casFeat_dependency == null)
      jcas.throwFeatMissing("dependency", "org.oaqa.model.kb.FanseDependencyRelation");
    ll_cas.ll_setStringValue(addr, casFeatCode_dependency, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public FanseDependencyRelation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_head = jcas.getRequiredFeatureDE(casType, "head", "org.oaqa.model.nlp.FanseToken", featOkTst);
    casFeatCode_head  = (null == casFeat_head) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_head).getCode();

 
    casFeat_child = jcas.getRequiredFeatureDE(casType, "child", "org.oaqa.model.nlp.FanseToken", featOkTst);
    casFeatCode_child  = (null == casFeat_child) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_child).getCode();

 
    casFeat_dependency = jcas.getRequiredFeatureDE(casType, "dependency", "uima.cas.String", featOkTst);
    casFeatCode_dependency  = (null == casFeat_dependency) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_dependency).getCode();

  }
}



    