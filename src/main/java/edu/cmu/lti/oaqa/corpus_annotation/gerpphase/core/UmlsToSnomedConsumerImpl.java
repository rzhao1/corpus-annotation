package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyStringList;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.NonEmptyStringList;

import org.apache.ctakes.dictionary.lookup.DictionaryException;
import org.apache.ctakes.dictionary.lookup.MetaDataHit;
import org.apache.ctakes.dictionary.lookup.ae.BaseLookupConsumerImpl;
import org.apache.ctakes.dictionary.lookup.ae.LookupConsumer;
import org.apache.ctakes.dictionary.lookup.vo.LookupHit;
import org.apache.ctakes.typesystem.type.constants.CONST;
import org.oaqa.model.kb.ConceptMention;
import org.oaqa.model.kb.EntityMention;
import org.oaqa.model.nlp.UmlsConcept;


/**
 * Implementation that takes UMLS dictionary lookup hits and stores as NamedEntity objects only the
 * ones that have a SNOMED synonym. Override abstract method <code>getSnomedCodes</code> and
 * implement looking up the CUI->SNOMED mappings
 * 
 * @author Ran Zhao
 * 
 * 
 */
public abstract class UmlsToSnomedConsumerImpl extends BaseLookupConsumerImpl implements
        LookupConsumer {

  private final String CUI_MF_PRP_KEY = "cuiMetaField";

  private final String TUI_MF_PRP_KEY = "tuiMetaField";

  private final String CODING_SCHEME_PRP_KEY = "codingScheme";

  private final String ANT_SITE_TUIS_PRP_KEY = "anatomicalSiteTuis";

  private final String PROCEDURE_TUIS_PRP_KEY = "procedureTuis";

  private final String DISORDER_TUIS_PRP_KEY = "disorderTuis";

  private final String FINDING_TUIS_PRP_KEY = "findingTuis";

  private Set antSiteTuiSet = new HashSet();

  private Set procedureTuiSet = new HashSet();

  private Set disorderTuiSet = new HashSet();

  private Set findingTuiSet = new HashSet();

  private Set validTuiSet = new HashSet();

  protected Properties props;

  public UmlsToSnomedConsumerImpl(UimaContext aCtx, Properties properties) throws Exception {
    // TODO property validation could be done here
    props = properties;

    antSiteTuiSet = loadList(props.getProperty(ANT_SITE_TUIS_PRP_KEY));
    procedureTuiSet = loadList(props.getProperty(PROCEDURE_TUIS_PRP_KEY));
    disorderTuiSet = loadList(props.getProperty(DISORDER_TUIS_PRP_KEY));
    findingTuiSet = loadList(props.getProperty(FINDING_TUIS_PRP_KEY));

    validTuiSet.addAll(antSiteTuiSet);
    validTuiSet.addAll(procedureTuiSet);
    validTuiSet.addAll(disorderTuiSet);
    validTuiSet.addAll(findingTuiSet);
  }

  /**
   * Searches for the Snomed codes that are synonyms of the UMLS concept with CUI
   * <code>umlsCode</code>
   * 
   * @param umlsCode
   * @return Set of SNOMED codes for the given UMLS CUI.
   * @throws SQLException
   *           , DictionaryException
   */
  protected abstract Set getSnomedCodes(String umlsCode) throws SQLException, DictionaryException;

  public void consumeHits(JCas jcas, Iterator lhItr) throws AnalysisEngineProcessException {
    try {

      Iterator hitsByOffsetItr = organizeByOffset(lhItr);
      while (hitsByOffsetItr.hasNext()) {
        Collection hitsAtOffsetCol = (Collection) hitsByOffsetItr.next();

        // Iterate over the LookupHit objects and group Snomed codes by NE type
        // For each NE type for which there is a hit, get all the Snomed codes
        // that map to the given CUI.

        // Use key "cui,tui" to avoid duplicates at this offset
        Set cuiTuiSet = new HashSet();

        // key = type of named entity (java.lang.Integer)
        // val = set of UmlsConcept objects (java.util.Set)
        Map conceptMap = new HashMap();

        Iterator lhAtOffsetItr = hitsAtOffsetCol.iterator();
        int neBegin = -1;
        int neEnd = -1;
        while (lhAtOffsetItr.hasNext()) {
          LookupHit lh = (LookupHit) lhAtOffsetItr.next();
          neBegin = lh.getStartOffset();
          neEnd = lh.getEndOffset();

          MetaDataHit mdh = lh.getDictMetaDataHit();
          String cui = mdh.getMetaFieldValue(props.getProperty(CUI_MF_PRP_KEY));
          String tui = mdh.getMetaFieldValue(props.getProperty(TUI_MF_PRP_KEY));

          // String text = lh.getDictMetaDataHit().getMetaFieldValue("text");
          if (validTuiSet.contains(tui)) {
            String cuiTuiKey = getUniqueKey(cui, tui);
            if (!cuiTuiSet.contains(cuiTuiKey)) {
              cuiTuiSet.add(cuiTuiKey);
              Set snomedCodeSet = getSnomedCodes(cui);
              if (snomedCodeSet.size() > 0) {
                Integer neType = new Integer(getNamedEntityType(tui));
                Set conceptSet;
                if (conceptMap.containsKey(neType)) {
                  conceptSet = (Set) conceptMap.get(neType);
                } else {
                  conceptSet = new HashSet();
                }

                Collection conceptCol = createConceptCol(jcas, cui, tui, snomedCodeSet);
                conceptSet.addAll(conceptCol);

                conceptMap.put(neType, conceptSet);
              }
            }
          }
        }

        Iterator neTypeItr = conceptMap.keySet().iterator();
        while (neTypeItr.hasNext()) {
          Integer neType = (Integer) neTypeItr.next();
          Set conceptSet = (Set) conceptMap.get(neType);

          // Skip updating CAS if all Concepts for this type were filtered out
          // for this span.
          if (conceptSet.size() > 0) {
            FSArray conceptArr = new FSArray(jcas, conceptSet.size());
            int arrIdx = 0;
            Iterator conceptItr = conceptSet.iterator();
            while (conceptItr.hasNext()) {
              UmlsConcept uc = (UmlsConcept) conceptItr.next();
              conceptArr.set(arrIdx, uc);
              arrIdx++;
            }

            ConceptMention neAnnot;
        
            neAnnot = new EntityMention(jcas);

            neAnnot.setTypeID(neType.intValue());
            neAnnot.setBegin(neBegin);
            neAnnot.setEnd(neEnd);
            neAnnot.setOntologyConceptArr(conceptArr);
            if (neAnnot.getGenerators() == null) {
              NonEmptyStringList generator = new NonEmptyStringList(jcas);
              neAnnot.setGenerators(generator);
              generator.setHead("UMLS:SNOMED CT");
              
            } else {
              NonEmptyStringList generator=(NonEmptyStringList) neAnnot.getGenerators();
              generator.setTail(new NonEmptyStringList(jcas));
              generator=(NonEmptyStringList) generator.getTail();
              generator.setHead("UMLS:SNOMED CT");
 
            }
            neAnnot.addToIndexes();
          }

        }
      }
    } catch (Exception e) {
      throw new AnalysisEngineProcessException(e);
    }
  }

  private int getNamedEntityType(String tui) throws Exception {
    if (disorderTuiSet.contains(tui)) {
      return CONST.NE_TYPE_ID_DISORDER;
    } else if (findingTuiSet.contains(tui)) {
      return CONST.NE_TYPE_ID_FINDING;
    } else if (antSiteTuiSet.contains(tui)) {
      return CONST.NE_TYPE_ID_ANATOMICAL_SITE;
    } else if (procedureTuiSet.contains(tui)) {
      return CONST.NE_TYPE_ID_PROCEDURE;
    } else {
      throw new Exception("TUI is not part of valid named entity types: " + tui);
    }
  }

  /**
   * For each SNOMED code, create a corresponding JCas UmlsConcept object and store in a Collection.
   * 
   * @param jcas
   * @param snomedCodesCol
   * @return
   */
  private Collection createConceptCol(JCas jcas, String cui, String tui, Collection snomedCodesCol) {
    List conceptList = new ArrayList();
    Iterator codeItr = snomedCodesCol.iterator();
    while (codeItr.hasNext()) {
      String snomedCode = (String) codeItr.next();
      UmlsConcept uc = new UmlsConcept(jcas);
      uc.setCode(snomedCode);
      uc.setCodingScheme(props.getProperty(CODING_SCHEME_PRP_KEY));
      uc.setCui(cui);
      uc.setTui(tui);
      conceptList.add(uc);
    }
    return conceptList;
  }

  private String getUniqueKey(String cui, String tui) {
    StringBuffer sb = new StringBuffer();
    sb.append(cui);
    sb.append(':');
    sb.append(tui);
    return sb.toString();
  }

  /**
   * Load a comma delimited list
   * 
   * @param delimitedString
   * @return
   */
  private Set loadList(String delimitedString) {
    String[] stringArr = delimitedString.split(",");
    Set stringSet = new HashSet();
    for (int i = 0; i < stringArr.length; i++) {
      String s = stringArr[i].trim();
      if (s.length() > 0) {
        stringSet.add(s);
      }
    }
    return stringSet;
  }
}