
/* First created by JCasGen Wed Jul 17 21:01:03 EDT 2013 */
package org.oaqa.model.doc;

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

/** It stores the document meta data. Such as the name of document.
 * Updated by JCasGen Thu Aug 08 15:17:07 EDT 2013
 * @generated */
public class DocMeta_Type extends GerpTop_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DocMeta_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DocMeta_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DocMeta(addr, DocMeta_Type.this);
  			   DocMeta_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DocMeta(addr, DocMeta_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = DocMeta.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.oaqa.model.doc.DocMeta");
 
  /** @generated */
  final Feature casFeat_docType;
  /** @generated */
  final int     casFeatCode_docType;
  /** @generated */ 
  public String getDocType(int addr) {
        if (featOkTst && casFeat_docType == null)
      jcas.throwFeatMissing("docType", "org.oaqa.model.doc.DocMeta");
    return ll_cas.ll_getStringValue(addr, casFeatCode_docType);
  }
  /** @generated */    
  public void setDocType(int addr, String v) {
        if (featOkTst && casFeat_docType == null)
      jcas.throwFeatMissing("docType", "org.oaqa.model.doc.DocMeta");
    ll_cas.ll_setStringValue(addr, casFeatCode_docType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_source;
  /** @generated */
  final int     casFeatCode_source;
  /** @generated */ 
  public String getSource(int addr) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "org.oaqa.model.doc.DocMeta");
    return ll_cas.ll_getStringValue(addr, casFeatCode_source);
  }
  /** @generated */    
  public void setSource(int addr, String v) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "org.oaqa.model.doc.DocMeta");
    ll_cas.ll_setStringValue(addr, casFeatCode_source, v);}
    
  
 
  /** @generated */
  final Feature casFeat_docID;
  /** @generated */
  final int     casFeatCode_docID;
  /** @generated */ 
  public String getDocID(int addr) {
        if (featOkTst && casFeat_docID == null)
      jcas.throwFeatMissing("docID", "org.oaqa.model.doc.DocMeta");
    return ll_cas.ll_getStringValue(addr, casFeatCode_docID);
  }
  /** @generated */    
  public void setDocID(int addr, String v) {
        if (featOkTst && casFeat_docID == null)
      jcas.throwFeatMissing("docID", "org.oaqa.model.doc.DocMeta");
    ll_cas.ll_setStringValue(addr, casFeatCode_docID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_author;
  /** @generated */
  final int     casFeatCode_author;
  /** @generated */ 
  public int getAuthor(int addr) {
        if (featOkTst && casFeat_author == null)
      jcas.throwFeatMissing("author", "org.oaqa.model.doc.DocMeta");
    return ll_cas.ll_getRefValue(addr, casFeatCode_author);
  }
  /** @generated */    
  public void setAuthor(int addr, int v) {
        if (featOkTst && casFeat_author == null)
      jcas.throwFeatMissing("author", "org.oaqa.model.doc.DocMeta");
    ll_cas.ll_setRefValue(addr, casFeatCode_author, v);}
    
   /** @generated */
  public String getAuthor(int addr, int i) {
        if (featOkTst && casFeat_author == null)
      jcas.throwFeatMissing("author", "org.oaqa.model.doc.DocMeta");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_author), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_author), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_author), i);
  }
   
  /** @generated */ 
  public void setAuthor(int addr, int i, String v) {
        if (featOkTst && casFeat_author == null)
      jcas.throwFeatMissing("author", "org.oaqa.model.doc.DocMeta");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_author), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_author), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_author), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_title;
  /** @generated */
  final int     casFeatCode_title;
  /** @generated */ 
  public String getTitle(int addr) {
        if (featOkTst && casFeat_title == null)
      jcas.throwFeatMissing("title", "org.oaqa.model.doc.DocMeta");
    return ll_cas.ll_getStringValue(addr, casFeatCode_title);
  }
  /** @generated */    
  public void setTitle(int addr, String v) {
        if (featOkTst && casFeat_title == null)
      jcas.throwFeatMissing("title", "org.oaqa.model.doc.DocMeta");
    ll_cas.ll_setStringValue(addr, casFeatCode_title, v);}
    
  
 
  /** @generated */
  final Feature casFeat_keyWordList;
  /** @generated */
  final int     casFeatCode_keyWordList;
  /** @generated */ 
  public int getKeyWordList(int addr) {
        if (featOkTst && casFeat_keyWordList == null)
      jcas.throwFeatMissing("keyWordList", "org.oaqa.model.doc.DocMeta");
    return ll_cas.ll_getRefValue(addr, casFeatCode_keyWordList);
  }
  /** @generated */    
  public void setKeyWordList(int addr, int v) {
        if (featOkTst && casFeat_keyWordList == null)
      jcas.throwFeatMissing("keyWordList", "org.oaqa.model.doc.DocMeta");
    ll_cas.ll_setRefValue(addr, casFeatCode_keyWordList, v);}
    
   /** @generated */
  public String getKeyWordList(int addr, int i) {
        if (featOkTst && casFeat_keyWordList == null)
      jcas.throwFeatMissing("keyWordList", "org.oaqa.model.doc.DocMeta");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_keyWordList), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_keyWordList), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_keyWordList), i);
  }
   
  /** @generated */ 
  public void setKeyWordList(int addr, int i, String v) {
        if (featOkTst && casFeat_keyWordList == null)
      jcas.throwFeatMissing("keyWordList", "org.oaqa.model.doc.DocMeta");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_keyWordList), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_keyWordList), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_keyWordList), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DocMeta_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_docType = jcas.getRequiredFeatureDE(casType, "docType", "uima.cas.String", featOkTst);
    casFeatCode_docType  = (null == casFeat_docType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_docType).getCode();

 
    casFeat_source = jcas.getRequiredFeatureDE(casType, "source", "uima.cas.String", featOkTst);
    casFeatCode_source  = (null == casFeat_source) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_source).getCode();

 
    casFeat_docID = jcas.getRequiredFeatureDE(casType, "docID", "uima.cas.String", featOkTst);
    casFeatCode_docID  = (null == casFeat_docID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_docID).getCode();

 
    casFeat_author = jcas.getRequiredFeatureDE(casType, "author", "uima.cas.StringArray", featOkTst);
    casFeatCode_author  = (null == casFeat_author) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_author).getCode();

 
    casFeat_title = jcas.getRequiredFeatureDE(casType, "title", "uima.cas.String", featOkTst);
    casFeatCode_title  = (null == casFeat_title) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_title).getCode();

 
    casFeat_keyWordList = jcas.getRequiredFeatureDE(casType, "keyWordList", "uima.cas.StringArray", featOkTst);
    casFeatCode_keyWordList  = (null == casFeat_keyWordList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_keyWordList).getCode();

  }
}



    