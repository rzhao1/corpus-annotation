package edu.cmu.lti.oaqa.corpus_annotation.gerpphase.core;

import java.util.Date;

/**
 * 
 * @author Ran Zhao
 * 
 *         Phase: Tokenizer helper-function
 * 
 */
public class TokenizerHelper {

  static final char APOSTROPHE = '\'';

  static final char PERIOD = '.';

  static final char HYPHEN_OR_MINUS_SIGN = '-';

  static final char NEWLINE = '\n';

  static final char CR = '\r';

  static final char COMMA = ',';

  /**
   * @return s.length() or index of nonalphanumeric character Note does NOT return -1 if the rest
   *         are all alphanumeric, returns s.length in that case Returns -1 if s == null. returns
   *         s.length() if fromIndex is too big
   */
  static public int findNextNonAlphaNum(String s, int fromIndex) {
    if (s == null)
      throw new IndexOutOfBoundsException("s==null, fromIndex = " + fromIndex);
    for (int i = fromIndex; i < s.length(); i++) {
      if (!Character.isLetterOrDigit(s.charAt(i)))
        return i;
    }
    return s.length();
  }

  // returns true if starts with 'tis and either that's all or the next char is not a letter
  static boolean startsWithWithoutBeingFollowedByLetter(String s, String compareTo) {
    if (s.startsWith(compareTo)) {
      if (s.length() == compareTo.length())
        return true;
      char next = s.charAt(compareTo.length());
      if (Character.isLetter(next))
        return false;
      return true;
    }
    return false;
  }

  // Copied isPunctuation from edu.mayo.bmi.nlp.tokenizer.Tokenizer
  static boolean isPunctuation(char c) {
    if ((c == ';') || (c == ':') || (c == ',') || (c == '.') || (c == '(') || (c == ')')
            || (c == '[') || (c == ']') || (c == '{') || (c == '}') || (c == '<') || (c == '>')
            || (c == '\'') || (c == '"') || (c == '/') || (c == '\\') || (c == '-')) {
      return true;
    } else {
      return false;
    }
  }

}
