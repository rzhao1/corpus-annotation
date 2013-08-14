
/* First created by JCasGen Mon Jul 22 21:07:28 EDT 2013 */
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
public class Section_Type extends GerpAnnotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Section_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Section_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Section(addr, Section_Type.this);
  			   Section_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Section(addr, Section_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Section.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.oaqa.model.nlp.Section");
 
  /** @generated */
  final Feature casFeat_title;
  /** @generated */
  final int     casFeatCode_title;
  /** @generated */ 
  public String getTitle(int addr) {
        if (featOkTst && casFeat_title == null)
      jcas.throwFeatMissing("title", "org.oaqa.model.nlp.Section");
    return ll_cas.ll_getStringValue(addr, casFeatCode_title);
  }
  /** @generated */    
  public void setTitle(int addr, String v) {
        if (featOkTst && casFeat_title == null)
      jcas.throwFeatMissing("title", "org.oaqa.model.nlp.Section");
    ll_cas.ll_setStringValue(addr, casFeatCode_title, v);}
    
  
 
  /** @generated */
  final Feature casFeat_depth;
  /** @generated */
  final int     casFeatCode_depth;
  /** @generated */ 
  public int getDepth(int addr) {
        if (featOkTst && casFeat_depth == null)
      jcas.throwFeatMissing("depth", "org.oaqa.model.nlp.Section");
    return ll_cas.ll_getIntValue(addr, casFeatCode_depth);
  }
  /** @generated */    
  public void setDepth(int addr, int v) {
        if (featOkTst && casFeat_depth == null)
      jcas.throwFeatMissing("depth", "org.oaqa.model.nlp.Section");
    ll_cas.ll_setIntValue(addr, casFeatCode_depth, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Section_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_title = jcas.getRequiredFeatureDE(casType, "title", "uima.cas.String", featOkTst);
    casFeatCode_title  = (null == casFeat_title) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_title).getCode();

 
    casFeat_depth = jcas.getRequiredFeatureDE(casType, "depth", "uima.cas.Integer", featOkTst);
    casFeatCode_depth  = (null == casFeat_depth) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_depth).getCode();

  }
}



    