
/* First created by JCasGen Sun Jul 28 00:02:30 EDT 2013 */
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
 * Updated by JCasGen Thu Aug 08 15:17:07 EDT 2013
 * @generated */
public class Lemma_Type extends GerpTop_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Lemma_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Lemma_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Lemma(addr, Lemma_Type.this);
  			   Lemma_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Lemma(addr, Lemma_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Lemma.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.oaqa.model.kb.Lemma");
 
  /** @generated */
  final Feature casFeat_key;
  /** @generated */
  final int     casFeatCode_key;
  /** @generated */ 
  public String getKey(int addr) {
        if (featOkTst && casFeat_key == null)
      jcas.throwFeatMissing("key", "org.oaqa.model.kb.Lemma");
    return ll_cas.ll_getStringValue(addr, casFeatCode_key);
  }
  /** @generated */    
  public void setKey(int addr, String v) {
        if (featOkTst && casFeat_key == null)
      jcas.throwFeatMissing("key", "org.oaqa.model.kb.Lemma");
    ll_cas.ll_setStringValue(addr, casFeatCode_key, v);}
    
  
 
  /** @generated */
  final Feature casFeat_posTag;
  /** @generated */
  final int     casFeatCode_posTag;
  /** @generated */ 
  public String getPosTag(int addr) {
        if (featOkTst && casFeat_posTag == null)
      jcas.throwFeatMissing("posTag", "org.oaqa.model.kb.Lemma");
    return ll_cas.ll_getStringValue(addr, casFeatCode_posTag);
  }
  /** @generated */    
  public void setPosTag(int addr, String v) {
        if (featOkTst && casFeat_posTag == null)
      jcas.throwFeatMissing("posTag", "org.oaqa.model.kb.Lemma");
    ll_cas.ll_setStringValue(addr, casFeatCode_posTag, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Lemma_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_key = jcas.getRequiredFeatureDE(casType, "key", "uima.cas.String", featOkTst);
    casFeatCode_key  = (null == casFeat_key) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_key).getCode();

 
    casFeat_posTag = jcas.getRequiredFeatureDE(casType, "posTag", "uima.cas.String", featOkTst);
    casFeatCode_posTag  = (null == casFeat_posTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_posTag).getCode();

  }
}



    