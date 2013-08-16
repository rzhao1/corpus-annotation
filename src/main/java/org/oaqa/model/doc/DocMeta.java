

/* First created by JCasGen Wed Jul 17 21:01:03 EDT 2013 */
package org.oaqa.model.doc;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.oaqa.model.gerp.GerpTop;
import org.apache.uima.jcas.cas.StringArray;


/** It stores the document meta data. Such as the name of document.
 * Updated by JCasGen Wed Aug 14 15:53:56 EDT 2013
 * XML source: /Users/ranzhao/git/corpus-annotation/src/main/resources/edu/cmu/lti/oaqa/OAQATypes.xml
 * @generated */
public class DocMeta extends GerpTop {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DocMeta.class);
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
  protected DocMeta() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DocMeta(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DocMeta(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: docType

  /** getter for docType - gets 
   * @generated */
  public String getDocType() {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_docType == null)
      jcasType.jcas.throwFeatMissing("docType", "org.oaqa.model.doc.DocMeta");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DocMeta_Type)jcasType).casFeatCode_docType);}
    
  /** setter for docType - sets  
   * @generated */
  public void setDocType(String v) {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_docType == null)
      jcasType.jcas.throwFeatMissing("docType", "org.oaqa.model.doc.DocMeta");
    jcasType.ll_cas.ll_setStringValue(addr, ((DocMeta_Type)jcasType).casFeatCode_docType, v);}    
   
    
  //*--------------*
  //* Feature: source

  /** getter for source - gets 
   * @generated */
  public String getSource() {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "org.oaqa.model.doc.DocMeta");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DocMeta_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated */
  public void setSource(String v) {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "org.oaqa.model.doc.DocMeta");
    jcasType.ll_cas.ll_setStringValue(addr, ((DocMeta_Type)jcasType).casFeatCode_source, v);}    
   
    
  //*--------------*
  //* Feature: docID

  /** getter for docID - gets 
   * @generated */
  public String getDocID() {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_docID == null)
      jcasType.jcas.throwFeatMissing("docID", "org.oaqa.model.doc.DocMeta");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DocMeta_Type)jcasType).casFeatCode_docID);}
    
  /** setter for docID - sets  
   * @generated */
  public void setDocID(String v) {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_docID == null)
      jcasType.jcas.throwFeatMissing("docID", "org.oaqa.model.doc.DocMeta");
    jcasType.ll_cas.ll_setStringValue(addr, ((DocMeta_Type)jcasType).casFeatCode_docID, v);}    
   
    
  //*--------------*
  //* Feature: author

  /** getter for author - gets 
   * @generated */
  public StringArray getAuthor() {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_author == null)
      jcasType.jcas.throwFeatMissing("author", "org.oaqa.model.doc.DocMeta");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_author)));}
    
  /** setter for author - sets  
   * @generated */
  public void setAuthor(StringArray v) {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_author == null)
      jcasType.jcas.throwFeatMissing("author", "org.oaqa.model.doc.DocMeta");
    jcasType.ll_cas.ll_setRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_author, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for author - gets an indexed value - 
   * @generated */
  public String getAuthor(int i) {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_author == null)
      jcasType.jcas.throwFeatMissing("author", "org.oaqa.model.doc.DocMeta");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_author), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_author), i);}

  /** indexed setter for author - sets an indexed value - 
   * @generated */
  public void setAuthor(int i, String v) { 
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_author == null)
      jcasType.jcas.throwFeatMissing("author", "org.oaqa.model.doc.DocMeta");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_author), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_author), i, v);}
   
    
  //*--------------*
  //* Feature: title

  /** getter for title - gets 
   * @generated */
  public String getTitle() {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "org.oaqa.model.doc.DocMeta");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DocMeta_Type)jcasType).casFeatCode_title);}
    
  /** setter for title - sets  
   * @generated */
  public void setTitle(String v) {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "org.oaqa.model.doc.DocMeta");
    jcasType.ll_cas.ll_setStringValue(addr, ((DocMeta_Type)jcasType).casFeatCode_title, v);}    
   
    
  //*--------------*
  //* Feature: keyWordList

  /** getter for keyWordList - gets 
   * @generated */
  public StringArray getKeyWordList() {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_keyWordList == null)
      jcasType.jcas.throwFeatMissing("keyWordList", "org.oaqa.model.doc.DocMeta");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_keyWordList)));}
    
  /** setter for keyWordList - sets  
   * @generated */
  public void setKeyWordList(StringArray v) {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_keyWordList == null)
      jcasType.jcas.throwFeatMissing("keyWordList", "org.oaqa.model.doc.DocMeta");
    jcasType.ll_cas.ll_setRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_keyWordList, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for keyWordList - gets an indexed value - 
   * @generated */
  public String getKeyWordList(int i) {
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_keyWordList == null)
      jcasType.jcas.throwFeatMissing("keyWordList", "org.oaqa.model.doc.DocMeta");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_keyWordList), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_keyWordList), i);}

  /** indexed setter for keyWordList - sets an indexed value - 
   * @generated */
  public void setKeyWordList(int i, String v) { 
    if (DocMeta_Type.featOkTst && ((DocMeta_Type)jcasType).casFeat_keyWordList == null)
      jcasType.jcas.throwFeatMissing("keyWordList", "org.oaqa.model.doc.DocMeta");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_keyWordList), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DocMeta_Type)jcasType).casFeatCode_keyWordList), i, v);}
  }

    