package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import gov.nih.nlm.nls.lvg.Api.LvgCmdApi;
import gov.nih.nlm.nls.lvg.Api.LvgLexItemApi;

/**
 * 
 * @author Ran Zhao
 * 
 *         Phase: LvgToken helper-function
 * 
 */

public interface LvgCmdApiResource {

  public LvgCmdApi getLvg();

  public LvgLexItemApi getLvgLex();

}
