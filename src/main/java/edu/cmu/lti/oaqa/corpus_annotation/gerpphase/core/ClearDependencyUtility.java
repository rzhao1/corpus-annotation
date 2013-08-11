package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.oaqa.model.nlp.ConllDependencyNode;
import org.oaqa.model.nlp.Sentence;
import org.oaqa.model.nlp.Token;

import clear.dep.DepNode;
import clear.dep.DepTree;

/**
 * 
 * @author Ran Zhao
 * 
 *         Phase: ClearParserDependencyParserAE helper-function
 */
public class ClearDependencyUtility extends DependencyUtility {

  // LOG4J logger based on class name
  public Logger logger = Logger.getLogger(getClass().getName());

  public static ArrayList<ConllDependencyNode> convert(JCas jcas, DepTree clearTree,
          Sentence sentence, List<Token> tokens) {

    ArrayList<ConllDependencyNode> uimaNodes = new ArrayList<ConllDependencyNode>(tokens.size() + 1);

    // Create the root node first
    int rootBegin = tokens.get(0).getBegin();
    int rootEnd = tokens.get(tokens.size() - 1).getEnd();
    uimaNodes.add(0, new ConllDependencyNode(jcas, rootBegin, rootEnd));

    // Create all the other nodes
    for (int i = 1; i < clearTree.size(); i++) {
      int nodeBegin = tokens.get(i - 1).getBegin(); // assumes that tokens are off 1 from clearTree
      int nodeEnd = tokens.get(i - 1).getEnd();
      ConllDependencyNode uimaNode = new ConllDependencyNode(jcas, nodeBegin, nodeEnd);
      uimaNodes.add(i, uimaNode);
    }

    // Set values in all the other nodes
    for (int i = 1; i < clearTree.size(); i++) {

      DepNode clearNode = clearTree.get(i);
      ConllDependencyNode uimaNode = uimaNodes.get(i);

      uimaNode.setId(clearNode.id);
      uimaNode.setForm(clearNode.form);
      uimaNode.setLemma(clearNode.lemma);
      uimaNode.setCpostag(clearNode.pos);
      uimaNode.setPostag(clearNode.pos);
      uimaNode.setFeats("_");
      uimaNode.setHead(uimaNodes.get(clearNode.headId));
      uimaNode.setDeprel(clearNode.deprel);
      uimaNode.setPhead(null);
      uimaNode.setPdeprel("_");

    }

    return uimaNodes;// uimaNodes.get(0); //return the root node
  }

  /** Equality expressions to aid in converting between DepNodes and CAS objects */
  public static boolean equalCoverage(Annotation annot1, Annotation annot2) {
    return annot1.getBegin() == annot2.getBegin() && annot1.getEnd() == annot2.getEnd()
            && annot1.getCoveredText() == annot2.getCoveredText();
  }
}
