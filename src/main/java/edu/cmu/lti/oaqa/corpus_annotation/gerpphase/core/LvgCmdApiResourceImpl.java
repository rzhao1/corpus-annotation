package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

import gov.nih.nlm.nls.lvg.Api.LvgCmdApi;
import gov.nih.nlm.nls.lvg.Api.LvgLexItemApi;

/**
 * 
 * @author Ran Zhao
 * 
 *         Phase: LvgToken helper-function
 * 
 */

public class LvgCmdApiResourceImpl implements LvgCmdApiResource {
  // LOG4J logger based on class name
  private Logger logger = Logger.getLogger(getClass().getName());

  private LvgCmdApi lvg;

  private LvgLexItemApi lvgLexItem;

  private static String CWD_PROPERTY = "user.dir"; // Name of property for current working directory

  public void load(String Uri) throws ResourceInitializationException {
    String configFileName = null;
    String cwd = null;
    try {

      File configFile = new File(Uri);
      configFileName = configFile.getPath();

      logger.info("Loading NLM Norm and Lvg with config file = " + configFileName);
      logger.info("  config file absolute path = " + configFile.getAbsolutePath());

      // Set the current working directory appropriately so the lvg files
      // will be found if the lvg properties file contains LVG_DIR=AUTO_MODE
      // If unable to change the current working directory, continue, so that
      // if the properties file LVG_DIR value was changed to a hardcoded path,
      // we allow that path to be used.
      String lvgDir = getLvgDir(configFile);
      cwd = getCurrentWorkingDirectory();
      if (cwd != null) {
        logger.info("cwd = " + cwd);
        changeCurrentWorkingDirectory(lvgDir);
      }

      lvg = new LvgCmdApi("-f:l:b", configFileName);
      lvgLexItem = new LvgLexItemApi("-f:i -SC", configFileName);

    } catch (Exception e) {

      e.printStackTrace();

    } finally {
      // try to change the current working directory back to what it was
      if (cwd != null) {
        changeCurrentWorkingDirectory(cwd);
      }

    }
  }

  private String getCurrentWorkingDirectory() {
    String cwd = null;
    try {
      cwd = System.getProperty(CWD_PROPERTY);
    } catch (SecurityException se) {
      se.printStackTrace();
      return cwd;
    }
    return cwd;
  }

  private boolean changeCurrentWorkingDirectory(String s) {
    try {
      System.setProperty(CWD_PROPERTY, s);
      logger.info("cd " + s);
    } catch (SecurityException se) {
      se.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Get the path to the root directory of the installation of NLMs lvg lexical tools. Note, does
   * not look at the LVG_DIR properties in the lvg properties file because LVG_DIR might not contain
   * a path - LVG_DIR=AUTO_MODE. Assumes the lvg properties file is in data\config\. For example, if
   * LVG project is installed into <br>
   * /pipeline/LVG <br>
   * with the lvg.properties file therefore in <br>
   * /pipeline/ctakes-lvg/resources/ctakes-lvg/data/config/ <br>
   * this method will return <br>
   * /pipeline/ctakes-lvg/resources/lvg <br>
   * 
   */
  private String getLvgDir(File configFile) {

    String pathToLvgRoot = null;
    File configDir = configFile.getParentFile();
    String configDirAbsPath = configDir.getPath();

    // Use the path after stripping off data/config
    // If path is not what was expected, try cwd as a last resort
    String dataConfigPath = "data" + File.separator + "config";
    if (!configDirAbsPath.endsWith(dataConfigPath)) {
      pathToLvgRoot = getCurrentWorkingDirectory();
    } else {
      int configDirAbsPathLen = configDirAbsPath.length();
      int pathLen = dataConfigPath.length();
      pathToLvgRoot = configDirAbsPath.substring(0, configDirAbsPathLen - pathLen);
    }

    return pathToLvgRoot;
  }

  /**
   * An LvgCmdApi takes a term from the input and returns a vector of strings.
   * 
   * @see org.apache.ctakes.lvg.resource.LvgCmdApiResource#getLvg()
   */
  public LvgCmdApi getLvg() {
    return lvg;
  }

  /**
   * The thing to run through Lvg
   */
  public LvgLexItemApi getLvgLex() {
    return lvgLexItem;
  }

}
