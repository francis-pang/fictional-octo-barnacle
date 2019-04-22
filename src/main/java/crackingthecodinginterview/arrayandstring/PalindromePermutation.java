/**
 *
 */
package crackingthecodinginterview.arrayandstring;

/**
 * @author franc
 *
 */
public class PalindromePermutation {
  public boolean isStringAPermutationOfPalindrome(String string) {
    string = string.toUpperCase();

    // Edge case check
    switch (string.length()) {
      case 0:
      case 1:
        return true;
      case 2:
        return (string.charAt(0) == string.charAt(1) ? true : false);
    }

    // Building the array
    final int SIZE_OF_ALL_LETTER = 26;
    final int UNICODE_OF_AT = 64;
    int[] characterArray = new int[SIZE_OF_ALL_LETTER];
    int letterOrDigitLength = 0;
    for (char character : string.toCharArray()) {
      if (!Character.isLetterOrDigit(character)) {
        continue;
      }
      letterOrDigitLength++;
      characterArray[character - UNICODE_OF_AT]++;
    }

    // Verify for palindrome
    boolean allowOddCountLetter = (letterOrDigitLength % 2 == 0 ? false : true);
    for (int occurance : characterArray) {
      if (occurance != 0 && occurance % 2 != 0) {
        if (allowOddCountLetter) {
          allowOddCountLetter = false;
        } else {
          return false;
        }
      }
    }
    return true;
  }
}
