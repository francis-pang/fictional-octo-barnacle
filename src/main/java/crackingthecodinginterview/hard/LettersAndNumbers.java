package crackingthecodinginterview.hard;

import java.util.Arrays;

public class LettersAndNumbers {

  public char[] longestSubArrayOfEqualLetterNumberSize(char[] array) {
    int longestArrayStartPosition = 0;
    int longestArrayEndPosition = 0;
    int maxNumberOfPairs = 0;

    for (int start = 0; start < array.length; start++) {
      int numberOfChar = 0;
      int numberOfDigit = 0;
      for (int index = start; index < array.length; index++) {
        if (Character.isDigit(array[index])) {
          numberOfDigit++;
        } else { // Assume to be letter
          numberOfChar++;
        }
        if (numberOfChar == numberOfDigit &&
            numberOfChar > maxNumberOfPairs) {
          longestArrayStartPosition = start;
          longestArrayEndPosition = index;
        }
      }
    }
    return Arrays.copyOfRange(array, longestArrayStartPosition, longestArrayEndPosition);
  }
}
