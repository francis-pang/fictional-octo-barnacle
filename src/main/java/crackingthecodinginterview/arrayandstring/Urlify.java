package crackingthecodinginterview.arrayandstring;

/**
 * URLify: Write a method to replace all spaces in a string with '%20: You may assume that the string has sufficient
 * space at the end to hold the additional characters, and that you are given the "true" length of the string. (Note:
 * If implementing in Java, please use a character array so that you can perform this operation in place.)
 *
 * @author franc
 */
public class Urlify {
  public char[] replaceEmptySpacesInString(char[] string, int stringLength) {
    // Edge case: Empty string
    if (stringLength == 0) {
      return new char[0];
    }
    int firstNonEmptyCharPosition = locateFirstNonEmptyChar(string);
    // Edge case: single character string
    if (stringLength == 1) {
      char[] singleChar = {string[firstNonEmptyCharPosition]};
      return singleChar;
    }
    // Only enter if there is leading space
    if (firstNonEmptyCharPosition > 0) {
      string = trimLeadingSpace(string, firstNonEmptyCharPosition, stringLength);
    }
    return replaceSpaceInTrimedString(string, stringLength);
  }

  /**
   * return -1 if the whole string has no letter.
   **/
  private int locateFirstNonEmptyChar(char[] string) {
    final int FIRST_CHAR_NOT_FOUND = -1;
    for (int i = 0; i < string.length; i++) {
      if (string[i] != ' ') {
        return i;
      }
    }
    return FIRST_CHAR_NOT_FOUND;
  }

  private char[] trimLeadingSpace(char[] string, int startPosition, int stringLength) {
    for (int i = 0; i < stringLength; i++) {
      string[i] = string[i + startPosition];
      string[i + startPosition] = ' ';
    }
    return string;
  }

  private char[] replaceSpaceInTrimedString(char[] string, int stringLength) {
    for (int i = 0; i < stringLength; i++) {
      if (string[i] == ' ') {
        //Swapping
        for (int j = stringLength + 1; j > i + 2; j--) { //Shift by 2 characters
          string[j] = string[j - 2];
          string[j - 1] = string[j - 3];
        }
        string[i] = '%';
        string[i + 1] = '2';
        string[i + 2] = '0';
        // Mistake made. We need to add 2 more character to the string length because
        // the length of the string has been extended when we replace the space.
        stringLength += 2;
      }
    }
    return string;
  }
}
