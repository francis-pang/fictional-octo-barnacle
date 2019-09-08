package facebook.abcs.arraysandstrings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * You are given a string containing characters <i>A</i> and <i>B</i> only. Your task is to change it into a string
 * such that there are no matching adjacent characters. To do this, you are allowed to delete zero or more characters
 * in the string.
 * Your task is to find the minimum number of required deletions.
 * For example, given the string <i>s = AABAAB</i>, remove an <i>A</i> at positions <i>0</i> and <i>3</i> to make
 * <i>s = ABAB</i> in <i>2</i> deletions.
 *
 * <b>Function Description</b>
 * Complete the <i>alternatingCharacters</i> function in the editor below. It must return an integer representing the
 * minimum number of deletions to make the alternating string.
 * alternatingCharacters has the following parameter(s):
 * <list>
 * <u>s: a string</u>
 * </list>
 * <b>Input Format</b>
 * The first line contains an integer <i>q</i>, the number of queries.
 * The next <i>q</i> lines each contain a string <i>s</i>.
 *
 * <b>Constraints</b>
 * Each string <i>s</i> will consist only of characters <i>A</i> and <i>B</i>
 *
 * <b>Output Format</b>
 * For each query, print the minimum number of deletions required on a new line.
 *
 * <b>Sample Input</b>
 * 5
 * AAAA
 * BBBBB
 * ABABABAB
 * BABABA
 * AAABBB
 *
 * <b>Sample Output</b>
 * 3
 * 4
 * 0
 * 0
 * 4
 *
 * <b>Explanation</b>
 * The characters marked red are the ones that can be deleted so that the string doesn't have matching consecutive
 * characters.
 * <img src="https://s3.amazonaws.com/hr-assets/0/1502450721-a0a2e9b5bd-alternatingCharacter2.png" alt="image" >
 */
public class AlternatingCharacters {
  public static class Solution {
    // Complete the alternatingCharacters function below.
    static int alternatingCharacters(String s) {
      char previousChar = s.charAt(0);
      int numberOfCharactersToRemove = 0;
      for (int index = 1; index < s.length(); index++) {
        if (s.charAt(index) == previousChar) {
          numberOfCharactersToRemove++;
        } else {
          // Swap character
          previousChar = (previousChar == 'A') ? 'B' : 'A';
        }
      }
      return numberOfCharactersToRemove;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int q = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int qItr = 0; qItr < q; qItr++) {
        String s = scanner.nextLine();

        int result = alternatingCharacters(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
      }
      bufferedWriter.close();
      scanner.close();
    }
  }

}
