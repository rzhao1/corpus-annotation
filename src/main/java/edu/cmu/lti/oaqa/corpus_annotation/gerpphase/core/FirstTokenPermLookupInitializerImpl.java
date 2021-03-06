package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import org.apache.ctakes.core.util.JCasUtil;
import org.apache.ctakes.dictionary.lookup.DictionaryEngine;
import org.apache.ctakes.dictionary.lookup.ae.LookupAnnotationToJCasAdapter;
import org.apache.ctakes.dictionary.lookup.ae.LookupInitializer;
import org.apache.ctakes.dictionary.lookup.algorithms.FirstTokenPermutationImpl;
import org.apache.ctakes.dictionary.lookup.algorithms.LookupAlgorithm;
import org.apache.ctakes.dictionary.lookup.phrasebuilder.PhraseBuilder;
import org.apache.ctakes.dictionary.lookup.phrasebuilder.VariantPhraseBuilderImpl;
import org.apache.ctakes.dictionary.lookup.vo.LookupToken;
import org.oaqa.model.nlp.Token;

/**
 * @author Ran Zhao
 * 
 *         Phase: DictionaryLookup helper-function
 */
public class FirstTokenPermLookupInitializerImpl implements LookupInitializer {
  // LOG4J logger based on class name
  private Logger iv_logger = Logger.getLogger(getClass().getName());

  // properties for firstWordPermutation algorithm
  private final String TEXT_MFS_PRP_KEY = "textMetaFields";

  private final String MAX_P_LEVEL_PRP_KEY = "maxPermutationLevel";

  private final String WINDOW_ANNOT_PRP_KEY = "windowAnnotations";

  private final String EXC_TAGS_PRP_KEY = "exclusionTags"; // optional

  private final String CANONICAL_VARIANT_ATTR = "canonicalATTR";

  Properties iv_props;

  // array of JCas window annotation type values
  private int[] iv_annotTypeArr;

  // set of exclusion POS tags (lower cased)
  private Set iv_exclusionTagSet = null;

  public FirstTokenPermLookupInitializerImpl(UimaContext aCtx, Properties props)
          throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
    // TODO property validation could be done here
    iv_props = props;

    // optional context window annotations
    String windowAnnots = iv_props.getProperty(WINDOW_ANNOT_PRP_KEY);
    if (windowAnnots != null) {
      String[] windowAnnotArr = windowAnnots.split("\\|");
      iv_annotTypeArr = new int[windowAnnotArr.length];
      for (int i = 0; i < windowAnnotArr.length; i++) {
        iv_annotTypeArr[i] = JCasUtil.getType(windowAnnotArr[i]);
      }
    }

    // optional exclusion POS tags
    String tagStr = iv_props.getProperty(EXC_TAGS_PRP_KEY);
    if (tagStr != null) {
      iv_exclusionTagSet = new HashSet();
      String[] tagArr = tagStr.split(",");
      for (int i = 0; i < tagArr.length; i++) {
        iv_exclusionTagSet.add(tagArr[i].toLowerCase());
      }
      iv_logger.info("Exclusion tagset loaded: " + iv_exclusionTagSet);
    }
  }

  public LookupAlgorithm getLookupAlgorithm(DictionaryEngine dictEngine)
          throws AnnotatorInitializationException {
    // variant support
    String[] variantArr = { CANONICAL_VARIANT_ATTR };
    PhraseBuilder pb = new VariantPhraseBuilderImpl(variantArr, true);

    String textMetaFields = iv_props.getProperty(TEXT_MFS_PRP_KEY);
    String[] textMetaFieldNameArr;
    if (textMetaFields == null)
      textMetaFieldNameArr = new String[] {};
    else
      textMetaFieldNameArr = textMetaFields.split("\\|");

    int maxPermutationLevel = Integer.parseInt(iv_props.getProperty(MAX_P_LEVEL_PRP_KEY));

    return new FirstTokenPermutationImpl(dictEngine, pb, textMetaFieldNameArr, maxPermutationLevel);
  }

  private boolean isTagExcluded(String tag) {
    if ((iv_exclusionTagSet == null) || (tag == null)) {
      return false;
    }

    return iv_exclusionTagSet.contains(tag.toLowerCase());
  }

  public Iterator getLookupTokenIterator(JCas jcas) throws AnnotatorInitializationException {
    List ltList = new ArrayList();

    JFSIndexRepository indexes = jcas.getJFSIndexRepository();
    Iterator btaItr = indexes.getAnnotationIndex(Token.type).iterator();
    while (btaItr.hasNext()) {

      Token bta = (Token) btaItr.next();
      if (bta.getTokenType() == null) {
        continue;
      }
      // System.out.println("The token is "+bta.getCoveredText());
      // System.out.println("The token type is "+bta.getTokenType());
      if (!((bta.getTokenType().equals("NewlineToken"))
              || (bta.getTokenType().equals("PunctuationToken"))
              || (bta.getTokenType().equals("ContractionToken")) || (bta.getTokenType()
              .equals("SymbolToken")))) {
        // System.out.println("Not Puncutation: "+bta.getTokenType());
        LookupToken lt = new LookupAnnotationToJCasAdapter(bta);

        // POS exclusion logic for first word lookup
        if (isTagExcluded(bta.getPartOfSpeech())) {
          lt.addStringAttribute(FirstTokenPermutationImpl.LT_KEY_USE_FOR_LOOKUP, "false");
        } else {
          lt.addStringAttribute(FirstTokenPermutationImpl.LT_KEY_USE_FOR_LOOKUP, "true");
        }

        if (bta.getTokenType().equals("WordToken")) {

          String canonicalForm = bta.getLemmaForm();
          if (canonicalForm != null) {
            lt.addStringAttribute(CANONICAL_VARIANT_ATTR, canonicalForm);
          }
        }

        ltList.add(lt);
      }
    }
    return ltList.iterator();
  }

  public Iterator getLookupWindowIterator(JCas jcas) throws AnnotatorInitializationException {
    try {
      JFSIndexRepository indexes = jcas.getJFSIndexRepository();
      String objClassName = iv_props.getProperty(WINDOW_ANNOT_PRP_KEY);
      int windowType = JCasUtil.getType(objClassName);
      return indexes.getAnnotationIndex(windowType).iterator();
    } catch (Exception e) {
      throw new AnnotatorInitializationException(e);
    }
  }

  public Map getContextMap(JCas jcas, int windowBegin, int windowEnd)
          throws AnnotatorInitializationException {
    if (iv_annotTypeArr != null) {
      List list = new ArrayList();

      // algorithm depends on a window for permutations
      JFSIndexRepository indexes = jcas.getJFSIndexRepository();
      for (int i = 0; i < iv_annotTypeArr.length; i++) {
        Iterator itr = indexes.getAnnotationIndex(iv_annotTypeArr[i]).iterator();
        list.addAll(constrainToWindow(windowBegin, windowEnd, itr));
      }

      Map m = new HashMap();
      m.put(FirstTokenPermutationImpl.CTX_KEY_WINDOW_ANNOTATIONS, list);
      return m;
    } else {
      return new HashMap();
    }
  }

  /**
   * Gets a list of LookupAnnotation objects within the specified window.
   * 
   * @param annotItr
   * @return
   */
  private List constrainToWindow(int begin, int end, Iterator annotItr) {
    List list = new ArrayList();

    while (annotItr.hasNext()) {
      Annotation annot = (Annotation) annotItr.next();

      // only consider if it's within the window
      if ((annot.getBegin() >= begin) && (annot.getEnd() <= end)) {
        list.add(new LookupAnnotationToJCasAdapter(annot));
      }
    }
    return list;
  }
}