package facebook.abcs.arraysandstrings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * We consider two strings to be anagrams of each other if the first string's letters can be rearranged to form the
 * second string. In other words, both strings must contain the same exact letters in the same exact frequency. For
 * example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.
 * <p>Alice is taking a cryptography class and finding anagrams to be very useful. She decides on an encryption scheme
 * involving two large strings where encryption is dependent on the minimum number of character deletions required to
 * make the two strings anagrams. Can you help her find this number?
 * </p>
 * <p>Given two strings, <i>s1</i> and <i>s2</i>, that may not be of the same length, determine the minimum number of
 * character deletions required to make <i>s1</i> and <i>s2</i> anagrams. Any characters can be deleted from either of
 * the strings.
 * </p>
 * <p>For example, <i>s1 = abc</i> and <i>s2 = amnop</i>. The only characters that match are the <i>a</i>'s so we have
 * to remove <i>bc</i> from <i>bc</i> and <i>mnop</i> from <i>s2</i> for a total of <i>6</i> deletions.
 * </p>
 * <b>Function Description</b>
 * Complete the <i>makingAnagrams</i> function in the editor below. It should return an integer representing the minimum
 * number of deletions needed to make the strings anagrams.
 * <br/>
 * <i>makingAnagrams</i> has the following parameter(s):
 * <list>
 * <u>s1: a string</u>
 * <u>s2: a string</u>
 * </list>
 *
 * <b>Input Format</b>
 * The first line contains a single string, <i>s1</i>.
 * The second line contains a single string, <i>s2</i>.
 *
 * <b>Constraints</b>
 * <list>
 * <u><i>1 <= |s1, |s2| <= 10^4</i></u>
 * <u>It is guaranteed that <i>s1</i> and <i>s2</i> consist of lowercase English letters, ascii[a-z].</u>
 * </list>
 *
 * <b>Output Format</b>
 * Print a single integer denoting the minimum number of characters which must be deleted to make the two strings
 * anagrams of each other.
 *
 * <b>Sample Input</b>
 * cde
 * abc
 *
 * <b>Sample Output</b>
 * 4
 *
 * <b>Explanation</b>
 * We delete the following characters from our two strings to turn them into anagrams of each other:
 *
 * <list>
 * <l>Remove d and e from cde to get c.</l>
 * <l>Remove a and b from abc to get c.</l>
 * </list>
 * <p>
 * We had to delete <i>4</i> characters to make both strings anagrams.
 */
public class MakingAnagrams {
  public static class Solution {

    // Complete the makingAnagrams function below.
    static int makingAnagrams(String s1, String s2) {
      Map<Character, Integer> numberOfOffsetByCharacter = new HashMap<>();
      for (int charIndex = 0; charIndex < s1.length(); charIndex++) {
        numberOfOffsetByCharacter.compute(s1.charAt(charIndex), (character, count) -> (count == null) ? 1 : count + 1);
      }
      for (int charIndex = 0; charIndex < s2.length(); charIndex++) {
        numberOfOffsetByCharacter.compute(s2.charAt(charIndex), ((character, count) -> (count == null) ? -1 :
            count - 1));
      }
      int minimumCharacters = 0;
      for (int value : numberOfOffsetByCharacter.values()) {
        minimumCharacters += Math.abs(value);
      }
      return minimumCharacters;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      String s1 = scanner.nextLine();

      String s2 = scanner.nextLine();

      int result = makingAnagrams(s1, s2);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
    }
  }

}
