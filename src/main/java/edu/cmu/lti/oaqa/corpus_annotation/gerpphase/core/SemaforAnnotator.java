package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.xerces.parsers.DOMParser;
import org.oaqa.model.nlp.Sentence;
import org.uimafit.component.JCasAnnotator_ImplBase;
import org.uimafit.descriptor.ConfigurationParameter;
import org.uimafit.util.JCasUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import edu.cmu.lti.event_coref.type.SemaforAnnotationSet;
import edu.cmu.lti.event_coref.type.SemaforLabel;
import edu.cmu.lti.event_coref.type.SemaforLayer;
import edu.cmu.lti.event_coref.type.SemaforParse;

/**
 * Adopted from Metaphor project, should be coded by Eric and also developed by Hector
 * 
 * This code use EditDistance method to match the offset errors
 * 
 * Required annotation: sentence
 * 
 * @author Ran Zhao
 * 
 */
public class SemaforAnnotator extends JCasAnnotator_ImplBase {

  public static final String PARAM_SEMAFOR_TEMP = "Semafor_tmpdir";

  public static final String PARAM_SEMAFOR_COMMAND = "Semafor_command";

  public static final String PARAM_SEMAFOR_HOME = "Semafor_home";

  public static final String PARAM_SHELL_PATH = "Shell_path";

  private String Semafor_tmpdir;

  private static String Semafor_command;

  private static String Semafor_home;

  private static String Shell_path;

  // this is temporarily created by system
  static String semaforFilename = "semafor_input.txt";

  File semaforInputFile, semaforOutputFile;

  FileInputStream inputStream;

  NodeList nodes;

  // int sentences, article;
  static int recursion = 0;

  static SemaforLabel currentLabel;

  static SemaforLayer currentLayer;

  static SemaforAnnotationSet currentAnnoSet;

  static String originalSentence;

  static String currentSentence;

  static Map<Integer, Integer> parsedSentOffset2origin;

  // private static String InputComponentID;

  public static DOMParser parser;

  public static diff_match_patch DMP;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);

    parser = new DOMParser();

    try {
      parser.setFeature("http://xml.org/sax/features/validation", false);
      // parser.setEntityResolver(EntityResolver er);
    } catch (Exception e) {

      e.printStackTrace();
      System.err.println("Caught exception setting parser feature: " + e.getMessage());

      throw new RuntimeException("Could not enable DTD validation!");
    }

     Semafor_tmpdir = (String) aContext.getConfigParameterValue(PARAM_SEMAFOR_TEMP);
    Semafor_command = (String) aContext.getConfigParameterValue(PARAM_SEMAFOR_COMMAND );
    Semafor_home = (String) aContext.getConfigParameterValue(PARAM_SEMAFOR_HOME);
    Shell_path=(String)aContext.getConfigParameterValue(PARAM_SHELL_PATH);

    DMP = new diff_match_patch();

  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    PrintWriter pw;
    // currentFile = new File("../METAL.semaforAnnotator/data/Metaphor-examples-2.out.txt");

    String semaforInputPath = Semafor_tmpdir + File.separator + semaforFilename;

    String semaforOutputPath = Semafor_tmpdir + File.separator + semaforFilename + ".out";

    System.out.println("Preparing Semafor input file at: " + semaforInputPath);

    semaforInputFile = new File(semaforInputPath);
    semaforOutputFile = new File(semaforOutputPath);

    if (!semaforInputFile.exists()) {
      try {
        semaforInputFile.createNewFile();
      } catch (IOException e) {
        throw new AnalysisEngineProcessException(e);
      }
    }

    if (!semaforOutputFile.exists()) {
      try {
        semaforOutputFile.createNewFile();
      } catch (IOException e) {
        throw new AnalysisEngineProcessException(e);
      }
    }

    try {
      pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
              semaforInputFile), "ISO-8859-1")));
    } catch (Exception e) {
      e.printStackTrace();
      throw new AnalysisEngineProcessException(e);
    }

    FileInputStream xmlFile;
    InputSource source;
    
    List<Sentence> sentences = new ArrayList<Sentence>(JCasUtil.select(aJCas, Sentence.class));

    for (Sentence sent : sentences) {
      String strippedSentence = sent.getCoveredText().replaceAll("\n", " ");
     
      // pw.println(sent.getCoveredText());
      pw.println(strippedSentence);
    }

    pw.close();

    try {
      execSemaforPocess(semaforInputFile.getAbsolutePath(),semaforOutputFile.getAbsolutePath());
    } catch (Exception e) {
      e.printStackTrace();
      throw new AnalysisEngineProcessException(e);
    }

    // semaforOutputFile = new File(Semafor_tmpdir + File.separator + semaforFilename + ".out");

    try {

      xmlFile = new FileInputStream(semaforOutputFile);
      source = new InputSource(xmlFile);

      parser.parse(source);
      Document document = parser.getDocument();

      nodes = document.getElementsByTagName("sentence");
      int sentenceCount = nodes.getLength();

      for (int s = 0; s < sentenceCount; s++) {
        SemaforParse sp = new SemaforParse(aJCas);
        Sentence sent = sentences.get(s);
       
        // // Test to see if we can use token offsets
        // AnnotationIndex tokenAnnotIndex = idx.getAnnotationIndex(Token.typeIndexID);
        // FSIterator<Token> tokenIter = tokenAnnotIndex.subiterator(sent);
        // int i=0;
        // annotatedTokens = new Vector<Token>();
        // while(tokenIter.hasNext()) {
        // annotatedTokens.add(tokenIter.get());
        // i++;
        // tokenIter.moveToNext();
        // }

        // annotatedTokens = new Vector<Word>(JCasUtil.selectCovered(Word.class, sent));
        // annotatedTokens = new Vector<Word>(UimaConvenience.getAnnotationList(aJCas, Word.class));
        // System.out.println(annotatedTokens.size());

        // System.out.println(sentences.get(s).getCoveredText());
        recurse(nodes.item(s), aJCas, sp, sent);
      }
      // SemaforAnnotationSet s = new SemaforAnnotationSet(jcas);

    } catch (SAXException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Instantiates an Semafor process using the supplied parameter file.
   * 
   * @param input
   *          a File referencing an input file initialized previously with a call to
   *          createInputFile()
   */
  private static void execSemaforPocess(String input,String output) throws Exception {
    // instantiate an annotator process
    // int ret = 0;
    Process p = null;
    // String[] commands = { "./"+Semafor_command, input };
    // String[] commands = { "/bin/sh", "./" + Semafor_command, input };
    String[] commands = { Shell_path, "./" + Semafor_command, input,output,"xml" };

    File f = new File(Semafor_home);
    try {
      String line;

      // In this way, the system PATH might not have been passed in
      // Currenlty PATH is hard-wired in "config"
      p = Runtime.getRuntime().exec(commands, null, f);
      //
      // BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
      // BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));

      // while ((line = bri.readLine()) != null) {
      // // Reads the stream.
      // LogUtils.log(line);
      // }
      // bri.close();
      //
      // while ((line = bre.readLine()) != null) {
      // // Reads the stream.
      // LogUtils.log(line);
      // }
      // bre.close();

      int ret = p.waitFor();
      System.out.println("Process exit with Value: " + ret);

    } catch (Exception e) {
      e.printStackTrace();
      p.destroy();
      System.err.println("Error executing child process");
    }
  }

  private static void recurse(Node n, JCas jcas, SemaforParse sp, Sentence s) {
    if (null == n)
      return;
    String name = n.getNodeName();

    // DEBUG
    // for (int i=0; i < recursion; i++)
    // System.out.print("  ");
    // System.out.println(name);

    // Top level annotation

    // Structure time

    if (n.hasChildNodes()) {
      NodeList children = n.getChildNodes();
      int childcount = children.getLength();

      if (name.equals("text")) {
         //System.out.println("text: " + n.getTextContent().split(" ").length);
        // currentSentence = n.getTextContent();
        // currentTokens = populateParsedTokens(n.getTextContent());
        currentSentence = n.getTextContent();
        LinkedList<Diff> diffs = DMP.diff_main(s.getCoveredText(), currentSentence);

        parsedSentOffset2origin = new HashMap<Integer, Integer>();

        int parsedTextPointer = 0;
        int originTextPointer = 0;

        for (Diff diff : diffs) {
          switch (diff.operation) {
            case INSERT:
              for (int len = 0; len < diff.text.length(); len++) {
                parsedSentOffset2origin.put(parsedTextPointer, originTextPointer);
                parsedTextPointer++;
              }
              // System.out.println("Find INSERT");
              // System.out.println(diff.text);
              // System.out.println(parsedTextPointer);
              // System.out.println(originTextPointer);

              break;
            case DELETE:
              for (int len = 0; len < diff.text.length(); len++) {
                originTextPointer++;
              }
              // System.out.println("Find DELETE");
              // System.out.println(diff.text);
              // System.out.println(parsedTextPointer);
              // System.out.println(originTextPointer);

              break;

            case EQUAL:
              for (int len = 0; len < diff.text.length(); len++) {
                parsedSentOffset2origin.put(parsedTextPointer, originTextPointer);
                parsedTextPointer++;
                originTextPointer++;
              }
              break;
          }
        }

        // System.out.println("****************************");
        // System.out.println("Debug sentence alignment");
        // System.out.println(currentSentence.length());
        // System.out.println(s.getCoveredText().length());
        // System.out.println(parsedTextPointer);
        // System.out.println(originTextPointer);
        // System.out.println("****************************");
      }

      if (name.equals("layer")) {
        SemaforLayer layer = new SemaforLayer(jcas);

        parseLayer(n, layer);
        layer.setBegin(s.getBegin());
        layer.setEnd(s.getEnd());
        // layer.setComponentID(componentID);
        layer.addToIndexes();
        currentLayer = layer;

        currentAnnoSet.setLayers(updateArray(jcas, currentAnnoSet.getLayers(), layer));
      } else if (name.equals("annotationSet")) {
        SemaforAnnotationSet annoSet = new SemaforAnnotationSet(jcas);

        parseAnnoSet(n, annoSet);
        annoSet.setBegin(s.getBegin());
        annoSet.setEnd(s.getEnd());
        // annoSet.setComponentID(componentID);
        annoSet.addToIndexes();
        currentAnnoSet = annoSet;

        sp.setAnnotationSets(updateArray(jcas, sp.getAnnotationSets(), annoSet));
      } else if (n.hasAttributes()) {
        /*
         * NamedNodeMap attrMap = n.getAttributes(); if ( attrMap != null ) {
         * 
         * for ( int i = 0; i < attrMap.getLength(); i++ ) { Node attrNode = attrMap.item( i );
         * 
         * String nodeName = attrNode.getNodeName(); System.out.println("attrNode: " + nodeName);
         * String value = attrNode.getNodeValue(); System.out.println("value:" + value); } }
         */
      }
    } else {
      if (name.equals("label")) {
        SemaforLabel label = new SemaforLabel(jcas);
        parseLabel(n, label, s);
        // label.setComponentID(componentID);
        label.addToIndexes();
        // add to list of labels in layer
        currentLayer.setLabels(updateArray(jcas, currentLayer.getLabels(), label));
      }
    }

    if (n.hasChildNodes()) {
      NodeList children = n.getChildNodes();
      recursion++;
      for (int i = 0; i < children.getLength(); i++) {

        recurse(children.item(i), jcas, sp, s);
      }
      recursion--;
    }
  }

  private static void parseAnnoSet(Node n, SemaforAnnotationSet sl) {
    if (n.hasAttributes()) {
      NamedNodeMap attrMap = n.getAttributes();
      if (attrMap != null) {

        for (int i = 0; i < attrMap.getLength(); i++) {
          Node attrNode = attrMap.item(i);

          String nodeName = attrNode.getNodeName(); // System.out.println("attrNode: " + nodeName);
          String value = attrNode.getNodeValue(); // System.out.println("value:" + value);

          if (nodeName.equals("ID"))
            sl.setID(Integer.parseInt(value));
          if (nodeName.equals("frameName"))
            sl.setFrameName(value);
        }
      }
    }
  }

  private static void parseLabel(Node n, SemaforLabel sl, Sentence s) {
    if (n.hasAttributes()) {
      NamedNodeMap attrMap = n.getAttributes();
      if (attrMap != null) {

        for (int i = 0; i < attrMap.getLength(); i++) {
          Node attrNode = attrMap.item(i);

          String nodeName = attrNode.getNodeName(); // System.out.println("attrNode: " + nodeName);
          String value = attrNode.getNodeValue(); // System.out.println("value:" + value);
          int offset = 0;
          try {
            offset = Integer.parseInt(value);
          } catch (NumberFormatException e) { // ignore if non numeric
          }

          if (nodeName.equals("ID"))
            sl.setID(Integer.parseInt(value));
          if (nodeName.equals("start")) {
            // sl.setBegin(s.getBegin() + offset - countBadChars(s.getCoveredText(), offset));
            // sl.setBegin(correctedBegin(offset));
            sl.setBegin(parsedSentOffset2origin.get(offset) + s.getBegin());
          }
          if (nodeName.equals("end")) {
            // int computed = s.getBegin() + offset + 1 - countBadChars(s.getCoveredText(), offset);
            // if (computed > s.getEnd())
            // computed = s.getEnd();
            // sl.setEnd(computed);
            // sl.setEnd(correctedEnd(offset + 1));
            sl.setEnd(parsedSentOffset2origin.get(offset) + s.getBegin() + 1);
          }
          if (nodeName.equals("name"))
            sl.setName(value);
          if (nodeName.equals("label"))
            sl.setID(offset);
        }
      }
    }
  }

  private static void parseLayer(Node n, SemaforLayer sl) {
    if (n.hasAttributes()) {
      NamedNodeMap attrMap = n.getAttributes();
      if (attrMap != null) {

        for (int i = 0; i < attrMap.getLength(); i++) {
          Node attrNode = attrMap.item(i);

          String nodeName = attrNode.getNodeName(); // System.out.println("attrNode: " + nodeName);
          String value = attrNode.getNodeValue(); // System.out.println("value:" + value);
          if (nodeName.equals("ID"))
            sl.setID(Integer.parseInt(value));
          if (nodeName.equals("name"))
            sl.setName(value);
        }
      }
    }
  }

  /**
   * 
   * Add a single element to (the end of) an existing FSArray
   * 
   * 
   * 
   * @param jcas
   * 
   * @param array
   * 
   * @param ann
   * 
   * @return
   */

  public static FSArray updateArray(JCas jcas, FSArray array, FeatureStructure ann) {
    FSArray arr;
    if (array == null) {
      arr = new FSArray(jcas, 1);
    } else {
      arr = new FSArray(jcas, array.size() + 1);
      for (int i = 0; i < array.size(); i++)
        arr.set(i, array.get(i));
    }
    arr.set(arr.size() - 1, ann);
    return arr;
  }
}
