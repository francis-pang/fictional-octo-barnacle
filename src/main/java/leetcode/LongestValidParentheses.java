package leetcode;

public class LongestValidParentheses {
  private static final char CLOSE_BRACKET = ')';
  private static final char OPEN_BRACKET = '(';

  public int longestValidParentheses(String string) {
    return dpSolution(string);
  }

  private int dpSolution(String string) {
    int[] memoTable = new int[string.length()];
    int maxLength = 0;
    for (int i = 0; i < string.length(); i++) {
      char c = string.charAt(i);
      if (c == OPEN_BRACKET) {
        continue;
      }
      char previous = ' ';
      if (i > 0) {
        previous = string.charAt(i - 1);
      }
      int length = 0;
      switch (previous) {
        case CLOSE_BRACKET -> {
          int lengthOfPreviousValidSubstring = memoTable[i - 1];
          if (lengthOfPreviousValidSubstring == 0) {
            continue;
          }
          int leftNeighbourIndex = i - 1 - lengthOfPreviousValidSubstring;
          if (leftNeighbourIndex <= 0) {
            continue;
          }
          char leftToPreviousSubString = string.charAt(leftNeighbourIndex);
          if (leftToPreviousSubString == OPEN_BRACKET) {
            length = 2 + lengthOfPreviousValidSubstring;
            if (leftNeighbourIndex - 1 > 0) {
              length += memoTable[leftNeighbourIndex - 1];
            }
          }
        }
        case OPEN_BRACKET -> {
          length = 2;
          if ((i - 2) > 0) {
            length += memoTable[i - 2];
          }
        }
      }
      memoTable[i] = length;
      maxLength = Math.max(length, maxLength);
    }
    return maxLength;
  }
}
