
/* First created by JCasGen Wed Jul 17 21:01:03 EDT 2013 */
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

/** Annotation on a token, which may have arguments if they are predicates; useful in transforming text into a "logical form", with affordances similar to the ones provided in Extended WordNet and the like. --- Source: Murdock
 * Updated by JCasGen Tue Aug 13 23:03:26 EDT 2013
 * @generated */
public class Token_Type extends GerpAnnotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Token_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Token_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Token(addr, Token_Type.this);
  			   Token_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Token(addr, Token_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Token.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.oaqa.model.nlp.Token");
 
  /** @generated */
  final Feature casFeat_arguments;
  /** @generated */
  final int     casFeatCode_arguments;
  /** @generated */ 
  public int getArguments(int addr) {
        if (featOkTst && casFeat_arguments == null)
      jcas.throwFeatMissing("arguments", "org.oaqa.model.nlp.Token");
    return ll_cas.ll_getRefValue(addr, casFeatCode_arguments);
  }
  /** @generated */    
  public void setArguments(int addr, int v) {
        if (featOkTst && casFeat_arguments == null)
      jcas.throwFeatMissing("arguments", "org.oaqa.model.nlp.Token");
    ll_cas.ll_setRefValue(addr, casFeatCode_arguments, v);}
    
   /** @generated */
  public int getArguments(int addr, int i) {
        if (featOkTst && casFeat_arguments == null)
      jcas.throwFeatMissing("arguments", "org.oaqa.model.nlp.Token");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_arguments), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_arguments), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_arguments), i);
  }
   
  /** @generated */ 
  public void setArguments(int addr, int i, int v) {
        if (featOkTst && casFeat_arguments == null)
      jcas.throwFeatMissing("arguments", "org.oaqa.model.nlp.Token");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_arguments), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_arguments), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_arguments), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_argumentLabels;
  /** @generated */
  final int     casFeatCode_argumentLabels;
  /** @generated */ 
  public int getArgumentLabels(int addr) {
        if (featOkTst && casFeat_argumentLabels == null)
      jcas.throwFeatMissing("argumentLabels", "org.oaqa.model.nlp.Token");
    return ll_cas.ll_getRefValue(addr, casFeatCode_argumentLabels);
  }
  /** @generated */    
  public void setArgumentLabels(int addr, int v) {
        if (featOkTst && casFeat_argumentLabels == null)
      jcas.throwFeatMissing("argumentLabels", "org.oaqa.model.nlp.Token");
    ll_cas.ll_setRefValue(addr, casFeatCode_argumentLabels, v);}
    
   /** @generated */
  public String getArgumentLabels(int addr, int i) {
        if (featOkTst && casFeat_argumentLabels == null)
      jcas.throwFeatMissing("argumentLabels", "org.oaqa.model.nlp.Token");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_argumentLabels), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_argumentLabels), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_argumentLabels), i);
  }
   
  /** @generated */ 
  public void setArgumentLabels(int addr, int i, String v) {
        if (featOkTst && casFeat_argumentLabels == null)
      jcas.throwFeatMissing("argumentLabels", "org.oaqa.model.nlp.Token");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_argumentLabels), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_argumentLabels), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_argumentLabels), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_parse;
  /** @generated */
  final int     casFeatCode_parse;
  /** @generated */ 
  public int getParse(int addr) {
        if (featOkTst && casFeat_parse == null)
      jcas.throwFeatMissing("parse", "org.oaqa.model.nlp.Token");
    return ll_cas.ll_getRefValue(addr, casFeatCode_parse);
  }
  /** @generated */    
  public void setParse(int addr, int v) {
        if (featOkTst && casFeat_parse == null)
      jcas.throwFeatMissing("parse", "org.oaqa.model.nlp.Token");
    ll_cas.ll_setRefValue(addr, casFeatCode_parse, v);}
    
  
 
  /** @generated */
  final Feature casFeat_semanticType;
  /** @generated */
  final int     casFeatCode_semanticType;
  /** @generated */ 
  public String getSemanticType(int addr) {
        if (featOkTst && casFeat_semanticType == null)
      jcas.throwFeatMissing("semanticType", "org.oaqa.model.nlp.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_semanticType);
  }
  /** @generated */    
  public void setSemanticType(int addr, String v) {
        if (featOkTst && casFeat_semanticType == null)
      jcas.throwFeatMissing("semanticType", "org.oaqa.model.nlp.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_semanticType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_partOfSpeech;
  /** @generated */
  final int     casFeatCode_partOfSpeech;
  /** @generated */ 
  public String getPartOfSpeech(int addr) {
        if (featOkTst && casFeat_partOfSpeech == null)
      jcas.throwFeatMissing("partOfSpeech", "org.oaqa.model.nlp.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_partOfSpeech);
  }
  /** @generated */    
  public void setPartOfSpeech(int addr, String v) {
        if (featOkTst && casFeat_partOfSpeech == null)
      jcas.throwFeatMissing("partOfSpeech", "org.oaqa.model.nlp.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_partOfSpeech, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lemmaForm;
  /** @generated */
  final int     casFeatCode_lemmaForm;
  /** @generated */ 
  public String getLemmaForm(int addr) {
        if (featOkTst && casFeat_lemmaForm == null)
      jcas.throwFeatMissing("lemmaForm", "org.oaqa.model.nlp.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_lemmaForm);
  }
  /** @generated */    
  public void setLemmaForm(int addr, String v) {
        if (featOkTst && casFeat_lemmaForm == null)
      jcas.throwFeatMissing("lemmaForm", "org.oaqa.model.nlp.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_lemmaForm, v);}
    
  
 
  /** @generated */
  final Feature casFeat_isMainReference;
  /** @generated */
  final int     casFeatCode_isMainReference;
  /** @generated */ 
  public boolean getIsMainReference(int addr) {
        if (featOkTst && casFeat_isMainReference == null)
      jcas.throwFeatMissing("isMainReference", "org.oaqa.model.nlp.Token");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_isMainReference);
  }
  /** @generated */    
  public void setIsMainReference(int addr, boolean v) {
        if (featOkTst && casFeat_isMainReference == null)
      jcas.throwFeatMissing("isMainReference", "org.oaqa.model.nlp.Token");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_isMainReference, v);}
    
  
 
  /** @generated */
  final Feature casFeat_isVariable;
  /** @generated */
  final int     casFeatCode_isVariable;
  /** @generated */ 
  public boolean getIsVariable(int addr) {
        if (featOkTst && casFeat_isVariable == null)
      jcas.throwFeatMissing("isVariable", "org.oaqa.model.nlp.Token");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_isVariable);
  }
  /** @generated */    
  public void setIsVariable(int addr, boolean v) {
        if (featOkTst && casFeat_isVariable == null)
      jcas.throwFeatMissing("isVariable", "org.oaqa.model.nlp.Token");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_isVariable, v);}
    
  
 
  /** @generated */
  final Feature casFeat_determiner;
  /** @generated */
  final int     casFeatCode_determiner;
  /** @generated */ 
  public String getDeterminer(int addr) {
        if (featOkTst && casFeat_determiner == null)
      jcas.throwFeatMissing("determiner", "org.oaqa.model.nlp.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_determiner);
  }
  /** @generated */    
  public void setDeterminer(int addr, String v) {
        if (featOkTst && casFeat_determiner == null)
      jcas.throwFeatMissing("determiner", "org.oaqa.model.nlp.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_determiner, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lemmaEntries;
  /** @generated */
  final int     casFeatCode_lemmaEntries;
  /** @generated */ 
  public int getLemmaEntries(int addr) {
        if (featOkTst && casFeat_lemmaEntries == null)
      jcas.throwFeatMissing("lemmaEntries", "org.oaqa.model.nlp.Token");
    return ll_cas.ll_getRefValue(addr, casFeatCode_lemmaEntries);
  }
  /** @generated */    
  public void setLemmaEntries(int addr, int v) {
        if (featOkTst && casFeat_lemmaEntries == null)
      jcas.throwFeatMissing("lemmaEntries", "org.oaqa.model.nlp.Token");
    ll_cas.ll_setRefValue(addr, casFeatCode_lemmaEntries, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tokenType;
  /** @generated */
  final int     casFeatCode_tokenType;
  /** @generated */ 
  public String getTokenType(int addr) {
        if (featOkTst && casFeat_tokenType == null)
      jcas.throwFeatMissing("tokenType", "org.oaqa.model.nlp.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_tokenType);
  }
  /** @generated */    
  public void setTokenType(int addr, String v) {
        if (featOkTst && casFeat_tokenType == null)
      jcas.throwFeatMissing("tokenType", "org.oaqa.model.nlp.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_tokenType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Token_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_arguments = jcas.getRequiredFeatureDE(casType, "arguments", "uima.cas.FSArray", featOkTst);
    casFeatCode_arguments  = (null == casFeat_arguments) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_arguments).getCode();

 
    casFeat_argumentLabels = jcas.getRequiredFeatureDE(casType, "argumentLabels", "uima.cas.StringArray", featOkTst);
    casFeatCode_argumentLabels  = (null == casFeat_argumentLabels) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_argumentLabels).getCode();

 
    casFeat_parse = jcas.getRequiredFeatureDE(casType, "parse", "org.oaqa.model.nlp.Token", featOkTst);
    casFeatCode_parse  = (null == casFeat_parse) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_parse).getCode();

 
    casFeat_semanticType = jcas.getRequiredFeatureDE(casType, "semanticType", "uima.cas.String", featOkTst);
    casFeatCode_semanticType  = (null == casFeat_semanticType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_semanticType).getCode();

 
    casFeat_partOfSpeech = jcas.getRequiredFeatureDE(casType, "partOfSpeech", "uima.cas.String", featOkTst);
    casFeatCode_partOfSpeech  = (null == casFeat_partOfSpeech) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_partOfSpeech).getCode();

 
    casFeat_lemmaForm = jcas.getRequiredFeatureDE(casType, "lemmaForm", "uima.cas.String", featOkTst);
    casFeatCode_lemmaForm  = (null == casFeat_lemmaForm) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lemmaForm).getCode();

 
    casFeat_isMainReference = jcas.getRequiredFeatureDE(casType, "isMainReference", "uima.cas.Boolean", featOkTst);
    casFeatCode_isMainReference  = (null == casFeat_isMainReference) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_isMainReference).getCode();

 
    casFeat_isVariable = jcas.getRequiredFeatureDE(casType, "isVariable", "uima.cas.Boolean", featOkTst);
    casFeatCode_isVariable  = (null == casFeat_isVariable) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_isVariable).getCode();

 
    casFeat_determiner = jcas.getRequiredFeatureDE(casType, "determiner", "uima.cas.String", featOkTst);
    casFeatCode_determiner  = (null == casFeat_determiner) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_determiner).getCode();

 
    casFeat_lemmaEntries = jcas.getRequiredFeatureDE(casType, "lemmaEntries", "uima.cas.FSList", featOkTst);
    casFeatCode_lemmaEntries  = (null == casFeat_lemmaEntries) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lemmaEntries).getCode();

 
    casFeat_tokenType = jcas.getRequiredFeatureDE(casType, "tokenType", "uima.cas.String", featOkTst);
    casFeatCode_tokenType  = (null == casFeat_tokenType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tokenType).getCode();

  }
}



    