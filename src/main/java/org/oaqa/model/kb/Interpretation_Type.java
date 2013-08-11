
/* First created by JCasGen Wed Jul 17 21:01:03 EDT 2013 */
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

/** A container that integrates Concepts and ConceptMentions.
 * Updated by JCasGen Thu Aug 08 15:17:07 EDT 2013
 * @generated */
public class Interpretation_Type extends GerpTop_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Interpretation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Interpretation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Interpretation(addr, Interpretation_Type.this);
  			   Interpretation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Interpretation(addr, Interpretation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Interpretation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.oaqa.model.kb.Interpretation");
 
  /** @generated */
  final Feature casFeat_concepts;
  /** @generated */
  final int     casFeatCode_concepts;
  /** @generated */ 
  public int getConcepts(int addr) {
        if (featOkTst && casFeat_concepts == null)
      jcas.throwFeatMissing("concepts", "org.oaqa.model.kb.Interpretation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_concepts);
  }
  /** @generated */    
  public void setConcepts(int addr, int v) {
        if (featOkTst && casFeat_concepts == null)
      jcas.throwFeatMissing("concepts", "org.oaqa.model.kb.Interpretation");
    ll_cas.ll_setRefValue(addr, casFeatCode_concepts, v);}
    
  
 
  /** @generated */
  final Feature casFeat_mentions;
  /** @generated */
  final int     casFeatCode_mentions;
  /** @generated */ 
  public int getMentions(int addr) {
        if (featOkTst && casFeat_mentions == null)
      jcas.throwFeatMissing("mentions", "org.oaqa.model.kb.Interpretation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_mentions);
  }
  /** @generated */    
  public void setMentions(int addr, int v) {
        if (featOkTst && casFeat_mentions == null)
      jcas.throwFeatMissing("mentions", "org.oaqa.model.kb.Interpretation");
    ll_cas.ll_setRefValue(addr, casFeatCode_mentions, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Interpretation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_concepts = jcas.getRequiredFeatureDE(casType, "concepts", "uima.cas.FSList", featOkTst);
    casFeatCode_concepts  = (null == casFeat_concepts) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_concepts).getCode();

 
    casFeat_mentions = jcas.getRequiredFeatureDE(casType, "mentions", "uima.cas.FSList", featOkTst);
    casFeatCode_mentions  = (null == casFeat_mentions) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mentions).getCode();

  }
}



    