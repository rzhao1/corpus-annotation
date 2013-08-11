
/* First created by JCasGen Wed Jul 17 21:01:03 EDT 2013 */
package org.oaqa.model.retrieval;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** A search result from a triple store, e.g., an RDF store.
 * Updated by JCasGen Thu Aug 08 15:17:07 EDT 2013
 * @generated */
public class RelationSearchResult_Type extends AnswerSearchResult_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (RelationSearchResult_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = RelationSearchResult_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new RelationSearchResult(addr, RelationSearchResult_Type.this);
  			   RelationSearchResult_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new RelationSearchResult(addr, RelationSearchResult_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = RelationSearchResult.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.oaqa.model.retrieval.RelationSearchResult");
 
  /** @generated */
  final Feature casFeat_context;
  /** @generated */
  final int     casFeatCode_context;
  /** @generated */ 
  public int getContext(int addr) {
        if (featOkTst && casFeat_context == null)
      jcas.throwFeatMissing("context", "org.oaqa.model.retrieval.RelationSearchResult");
    return ll_cas.ll_getRefValue(addr, casFeatCode_context);
  }
  /** @generated */    
  public void setContext(int addr, int v) {
        if (featOkTst && casFeat_context == null)
      jcas.throwFeatMissing("context", "org.oaqa.model.retrieval.RelationSearchResult");
    ll_cas.ll_setRefValue(addr, casFeatCode_context, v);}
    
   /** @generated */
  public int getContext(int addr, int i) {
        if (featOkTst && casFeat_context == null)
      jcas.throwFeatMissing("context", "org.oaqa.model.retrieval.RelationSearchResult");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_context), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_context), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_context), i);
  }
   
  /** @generated */ 
  public void setContext(int addr, int i, int v) {
        if (featOkTst && casFeat_context == null)
      jcas.throwFeatMissing("context", "org.oaqa.model.retrieval.RelationSearchResult");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_context), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_context), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_context), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public RelationSearchResult_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_context = jcas.getRequiredFeatureDE(casType, "context", "uima.cas.FSArray", featOkTst);
    casFeatCode_context  = (null == casFeat_context) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_context).getCode();

  }
}



    