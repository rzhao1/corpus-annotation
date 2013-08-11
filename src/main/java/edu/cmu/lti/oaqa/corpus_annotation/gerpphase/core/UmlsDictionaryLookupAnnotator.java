package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import org.apache.ctakes.utils.env.EnvironmentVariable;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * UIMA annotator that identified entities based on lookup.
 * 
 * @author Ran Zhao
 * 
 *         Phase: UmlsDictionaryLookup Annotator
 * 
 */
public class UmlsDictionaryLookupAnnotator extends DictionaryLookupAnnotator_Db {
  /*
   * Special implementation to pre bundle the UMLS SnowmedCT/RxNorm dictionaries Performs a check
   * for user's UMLS licence at init time via their RESTful API User's will need to configure their
   * UMLS username/password in their config
   */
  private final static String UMLSADDR_PARAM = "umlsaddr";

  private final static String UMLSVENDOR_PARAM = "umlsvendor";

  private final static String UMLSUSER_PARAM = "umlsuser";

  private final static String UMLSPW_PARAM = "umlspw";

  private Logger iv_logger = Logger.getLogger(getClass().getName());

  private String UMLSAddr;

  private String UMLSVendor;

  private String UMLSUser;

  private String UMLSPW;

  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);

    try {
      UMLSAddr = EnvironmentVariable.getEnv(UMLSADDR_PARAM, aContext);
      UMLSVendor = EnvironmentVariable.getEnv(UMLSVENDOR_PARAM, aContext);
      UMLSUser = EnvironmentVariable.getEnv(UMLSUSER_PARAM, aContext);
      UMLSPW = EnvironmentVariable.getEnv(UMLSPW_PARAM, aContext);

      iv_logger.info("Using " + UMLSADDR_PARAM + ": " + UMLSAddr + ": " + UMLSUser);
      if (!isValidUMLSUser(UMLSAddr, UMLSVendor, UMLSUser, UMLSPW)) {
        iv_logger
                .error("Error: Invalid UMLS License.  A UMLS License is required to use the UMLS dictionary lookup. \n"
                        + "Error: You may request one at: https://uts.nlm.nih.gov/license.html \n"
                        + "Please verify your UMLS license settings in the DictionaryLookupAnnotatorUMLS.xml configuration.");
        throw new Exception("Failed to initilize.  Invalid UMLS License");
      }

    } catch (Exception e) {
      throw new ResourceInitializationException(e);
    }
  }

  public static boolean isValidUMLSUser(String umlsaddr, String vendor, String username,
          String password) throws Exception {
    String data = URLEncoder.encode("licenseCode", "UTF-8") + "="
            + URLEncoder.encode(vendor, "UTF-8");
    data += "&" + URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
    data += "&" + URLEncoder.encode("password", "UTF-8") + "="
            + URLEncoder.encode(password, "UTF-8");
    URL url = new URL(umlsaddr);
    URLConnection conn = url.openConnection();
    conn.setDoOutput(true);
    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    wr.write(data);
    wr.flush();
    boolean result = false;
    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line;
    while ((line = rd.readLine()) != null) {
      if (line != null && line.trim().length() > 0) {
        result = line.trim().equalsIgnoreCase("<Result>true</Result>");
      }
    }
    wr.close();
    rd.close();
    return result;
  }
}