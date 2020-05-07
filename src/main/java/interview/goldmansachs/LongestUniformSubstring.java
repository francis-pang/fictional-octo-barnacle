package interview.goldmansachs;

/* Problem Name is &&& Longest Uniform Substring &&& PLEASE DO NOT REMOVE THIS LINE. */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Instructions to candidate.
 * 1) Run this code in the REPL to observe its behaviour. The
 * execution entry point is main().
 * 2) Your task is to implement the following method ('longestUniformSubstring')
 * <p>
 * This method should return an integer array with two elements that correctly identifies the location of the longest
 * uniform substring within the input string. The first element of the array should be the starting index of the longest
 * substring and the second element should be the length.
 * <p>
 * e.g.
 * <p>
 * for the input: "abbbccda" the longest uniform substring is "bbb" (which starts at index 1 and is 3 characters long).
 * <p>
 * 3) If time permits, add some additional test cases.
 */
public class LongestUniformSubstring {

  private static final Map<String, int[]> testCases = new HashMap<String, int[]>();

  static int[] longestUniformSubstring(String input) {
    int longestStart = -1;
    int longestLength = 0;
    if (input.length() == 0) {
      return new int[]{longestStart, longestLength};
    }

    // todo: implement the longestUniformSubstring logic
    int counter = 1;
    int startPosition = 0;
    for (int i = 1; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      char prevChar = input.charAt(i - 1);
      if (currentChar == prevChar) {
        counter++;
      } else {
        if (counter > longestLength) {
          longestStart = startPosition;
          longestLength = counter;
        }
        counter = 1;
        startPosition = i;
      }
    }
    if (counter > longestLength) {
      longestStart = startPosition;
      longestLength = counter;
    }
    return new int[]{longestStart, longestLength};
  }

  public static void main(String[] args) {
    testCases.put("", new int[]{-1, 0});
    testCases.put("10000111", new int[]{1, 4});
    testCases.put("aabbbbbCdAA", new int[]{2, 5});
    testCases.put("abbbccbda", new int[]{1, 3});
    testCases.put("abbbccbdaeeeeeee", new int[]{9, 7});
    // todo: implement more tests, please
    // feel free to make testing more elegant

    boolean pass = true;
    for (Map.Entry<String, int[]> testCase : testCases.entrySet()) {
      int[] result = longestUniformSubstring(testCase.getKey());
      pass = pass && (Arrays.equals(result, testCase.getValue()));
    }
    if (pass) {
      System.out.println("All tests pass!");
    } else {
      System.out.println("At least one failure! :( ");
    }
  }
}
