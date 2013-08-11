package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Utility class that attempts to locate files.
 * 
 * @author Ran Zhao
 * 
 * 
 */
public class FileLocator {
  public static File locateFile(String location) throws FileNotFoundException {
    try {
      // System.out.println("cwd=" + new File(".").getAbsolutePath());
      return locateOnClasspath(location);
    } catch (Exception e) {
      return locateExplicitly(location);
    }
  }

  private static File locateOnClasspath(String cpLocation) throws FileNotFoundException,
          URISyntaxException {
    ClassLoader cl = FileLocator.class.getClassLoader();
    URL indexUrl = cl.getResource(cpLocation);
    if (indexUrl == null) {
      throw new FileNotFoundException(cpLocation);
    }

    URI indexUri = new URI(indexUrl.toExternalForm());
    File f = new File(indexUri);
    return f;
  }

  private static File locateExplicitly(String explicitLocation) throws FileNotFoundException {
    File f = new File(explicitLocation);
    if (!f.exists()) {
      throw new FileNotFoundException(explicitLocation);
    }
    return f;
  }
}