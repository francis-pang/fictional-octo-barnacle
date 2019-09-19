package facebook.abcs.arraysandstrings;

public class HighestValuePalindrome {
  public static class Solution {
    // Complete the highestValuePalindrome function below.
    private final static String CANNOT_FORM_PALINDROME = "-1";
    private final static char BIGGEST_DIGIT = '9';

    static String highestValuePalindrome(String s, int n, int k) {
      if (s == null || n == 0) {
        return CANNOT_FORM_PALINDROME;
      }
      // Short-circuit for edge cases
      if (k >= n) {
        StringBuilder append = new StringBuilder().append(String.valueOf(BIGGEST_DIGIT).repeat(Math.max(0, n)));
        return append.toString();
      }
      StringChange stringChange = new StringChange(k, n);
      char[] array = createPalindromeWithMinimumChanges(s, n, stringChange);
      array = updatePalindromeForHighestValue(array, stringChange);
      return (stringChange.numberOfChangesAllowed < 0) ? CANNOT_FORM_PALINDROME : new String(array);
    }

    private static char[] updatePalindromeForHighestValue(char[] array, StringChange stringChange) {
      final boolean STRING_IS_EVEN_LENGTH = array.length % 2 == 0;

      int remainingChangesLeft = stringChange.numberOfChangesAllowed;
      boolean[] changedPositions = stringChange.arrayChangedPositions;
      int currentPosition = 0;
      int positionBoundary = (STRING_IS_EVEN_LENGTH) ? array.length / 2 : (array.length + 1) / 2;
      while (remainingChangesLeft > 0 && currentPosition < positionBoundary) {
        if (array[currentPosition] == BIGGEST_DIGIT) {
          currentPosition++;
          continue;
        }
        int numberOfUpdateRequired = 0;
        if (!changedPositions[currentPosition]) {
          numberOfUpdateRequired++;
        }
        int correspondingRightPosition = array.length - currentPosition - 1;
        // The first check is for the odd length string so that we don't double count
        if (currentPosition != correspondingRightPosition && !changedPositions[correspondingRightPosition]) {
          numberOfUpdateRequired++;
        }
        if (remainingChangesLeft >= numberOfUpdateRequired) {
          remainingChangesLeft -= numberOfUpdateRequired;
          changedPositions[currentPosition] = true; // Need not do, but do it just to be consistent
          changedPositions[correspondingRightPosition] = true;
          array[currentPosition] = BIGGEST_DIGIT;
          array[correspondingRightPosition] = BIGGEST_DIGIT;
        }
        currentPosition++;
      }
      return array;
    }

    private static char[] createPalindromeWithMinimumChanges(String string, int lengthOfString,
                                                             StringChange stringChange) {
      final boolean STRING_IS_EVEN_LENGTH = lengthOfString % 2 == 0;
      int currentPosition = 0;
      char[] array = new char[lengthOfString];
      int positionBoundary = (STRING_IS_EVEN_LENGTH) ? lengthOfString / 2 : (lengthOfString + 1) / 2;
      while (stringChange.numberOfChangesAllowed >= 0 && currentPosition < positionBoundary) {
        char charAtLeftPos = string.charAt(currentPosition);
        int correspondingRightPosition = lengthOfString - currentPosition - 1;
        char charAtRightPos = string.charAt(correspondingRightPosition);
        char biggerChar;
        if (charAtLeftPos == charAtRightPos) {
          biggerChar = charAtLeftPos;
        } else if (charAtLeftPos > charAtRightPos) {
          biggerChar = charAtLeftPos;
          stringChange.numberOfChangesAllowed--;
          stringChange.arrayChangedPositions[correspondingRightPosition] = true;
        } else {
          biggerChar = charAtRightPos;
          stringChange.numberOfChangesAllowed--;
          stringChange.arrayChangedPositions[currentPosition] = true;
        }
        array[currentPosition] = biggerChar;
        array[correspondingRightPosition] = biggerChar;
        currentPosition++;
      }
      if (!STRING_IS_EVEN_LENGTH) {
        final int MID_POINT_POSITION = lengthOfString / 2;
        array[MID_POINT_POSITION] = string.charAt(MID_POINT_POSITION);
      }
      return array;
    }

    /*
     * Indicator of which character in the string is changed, and the number of changes left
     */
    private static class StringChange {
      public boolean[] arrayChangedPositions;
      public int numberOfChangesAllowed;

      public StringChange(final int numberOfChangesAllowed, final int sizeOfArray) {
        this.numberOfChangesAllowed = numberOfChangesAllowed;
        arrayChangedPositions = new boolean[sizeOfArray];
      }
    }

    public static void main(String[] args) {
      int n = 5;
      int k = 4;
      String s = "11331";
      String result = highestValuePalindrome(s, n, k);
      System.out.println(result);
    }
  }

}
