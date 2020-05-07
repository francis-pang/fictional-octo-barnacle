package interview.Igloohome;

/**
 * For this challenge you will be determining if you can create a palindrome from a list of numbers.
 *
 * Have the function PalindromeCreator(str) take the str parameter being passed and determine if it is possible to
 * create a palindromic string of minimum length 3 characters by removing 1 or 2 characters. For example: if str is
 * "abjchba" then you can remove the characters jc to produce "abhba" which is a palindrome. For this example your
 * program should return the two characters that were removed with no delimiter and in the order they appear in the
 * string, so jc. If 1 or 2 characters cannot be removed to produce a palindrome, then return the string not
 * possible. If the input string is already a palindrome, your program should return the string palindrome.
 *
 * The input will only contain lowercase alphabetic characters. Your program should always attempt to create the
 * longest palindromic substring by removing 1 or 2 characters (see second sample test case as an example). The 2
 * characters you remove do not have to be adjacent in the string.
 */
public class PalindromeCreator {
  private static final String NO_PALINDROME = "not possible";
  public static String PalindromeCreator(String string) {
    return createPalindromeByCharacterRemoval(string, 0, string.length() - 1, 0);
  }

  private static String createPalindromeByCharacterRemoval(String string, int left, int right, int missCount) {
    String removedString = "";
    while (left <= right && missCount < 3) {
      char leftCharacter = string.charAt(left);
      char rightCharacter = string.charAt(right);
      if (leftCharacter == rightCharacter) {
        left++;
        right--;
        continue;
      }
      removedString = createPalindromeByCharacterRemoval(string, left + 1, right, missCount + 1);
      if (!NO_PALINDROME.equals(removedString)) {
        return leftCharacter + removedString;
      }
      removedString = createPalindromeByCharacterRemoval(string, left, right - 1, missCount + 1);
      if (!NO_PALINDROME.equals(removedString)) {
        return removedString + rightCharacter;
      }
      return NO_PALINDROME;
    }
    if (missCount < 3 && string.length() - missCount >= 3) {
      return removedString;
    } else {
      return NO_PALINDROME;
    }
  }

  public static void main (String[] args) {
    // keep this function call here
//    Scanner s = new Scanner(System.in);
//    System.out.print(PalindromeCreator(s.nextLine()));
    String s = "xyyabbccbayzyx";
    System.out.print(PalindromeCreator(s));
  }
}
