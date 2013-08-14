
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

/** A named relation mention that identify or define the named relation concept.
 * Updated by JCasGen Tue Aug 13 23:03:25 EDT 2013
 * @generated */
public class RelationMention_Type extends ConceptMention_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (RelationMention_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = RelationMention_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new RelationMention(addr, RelationMention_Type.this);
  			   RelationMention_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new RelationMention(addr, RelationMention_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = RelationMention.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.oaqa.model.kb.RelationMention");
 
  /** @generated */
  final Feature casFeat_argumentMentions;
  /** @generated */
  final int     casFeatCode_argumentMentions;
  /** @generated */ 
  public int getArgumentMentions(int addr) {
        if (featOkTst && casFeat_argumentMentions == null)
      jcas.throwFeatMissing("argumentMentions", "org.oaqa.model.kb.RelationMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_argumentMentions);
  }
  /** @generated */    
  public void setArgumentMentions(int addr, int v) {
        if (featOkTst && casFeat_argumentMentions == null)
      jcas.throwFeatMissing("argumentMentions", "org.oaqa.model.kb.RelationMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_argumentMentions, v);}
    
  
 
  /** @generated */
  final Feature casFeat_eventMention;
  /** @generated */
  final int     casFeatCode_eventMention;
  /** @generated */ 
  public int getEventMention(int addr) {
        if (featOkTst && casFeat_eventMention == null)
      jcas.throwFeatMissing("eventMention", "org.oaqa.model.kb.RelationMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_eventMention);
  }
  /** @generated */    
  public void setEventMention(int addr, int v) {
        if (featOkTst && casFeat_eventMention == null)
      jcas.throwFeatMissing("eventMention", "org.oaqa.model.kb.RelationMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_eventMention, v);}
    
  
 
  /** @generated */
  final Feature casFeat_semanticArgument;
  /** @generated */
  final int     casFeatCode_semanticArgument;
  /** @generated */ 
  public int getSemanticArgument(int addr) {
        if (featOkTst && casFeat_semanticArgument == null)
      jcas.throwFeatMissing("semanticArgument", "org.oaqa.model.kb.RelationMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_semanticArgument);
  }
  /** @generated */    
  public void setSemanticArgument(int addr, int v) {
        if (featOkTst && casFeat_semanticArgument == null)
      jcas.throwFeatMissing("semanticArgument", "org.oaqa.model.kb.RelationMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_semanticArgument, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public RelationMention_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_argumentMentions = jcas.getRequiredFeatureDE(casType, "argumentMentions", "uima.cas.FSList", featOkTst);
    casFeatCode_argumentMentions  = (null == casFeat_argumentMentions) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_argumentMentions).getCode();

 
    casFeat_eventMention = jcas.getRequiredFeatureDE(casType, "eventMention", "org.oaqa.model.kb.EventMention", featOkTst);
    casFeatCode_eventMention  = (null == casFeat_eventMention) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_eventMention).getCode();

 
    casFeat_semanticArgument = jcas.getRequiredFeatureDE(casType, "semanticArgument", "org.oaqa.model.nlp.SemanticRole", featOkTst);
    casFeatCode_semanticArgument  = (null == casFeat_semanticArgument) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_semanticArgument).getCode();

  }
}



    