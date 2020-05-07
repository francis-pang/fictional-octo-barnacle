package interview.mdaq;

import java.util.HashMap;

/**
 * We are given a string S consisting of N lowercase letters. A sequence of two adjacent letters inside a string is
 * called a digram. The distance between two digrams is the distance between the first letter of the first digram and
 * the first letter of the second digram. For example, in string S = "abcde" the distance between digrams "bc" and
 * "dc" is 2.
 *
 * We want to find out the distance between the furthest identical digrams inside string S.
 *
 * Write a function:
 *  class StringDiagram { public int solution(String string); }
 *
 * that, given a string S consisting of N letters, returns the distance between the two identical digrams in the
 * string that lie furthest away from each other. If there are no two identical digrams insdie S, your function
 * should return -1.
 *
 * Examples:
 * 1. Given S = "aabcaabcabda" your function should return 7. The furthest identical digrams are "ab"s, starting in
 * positions 2 and 9 (enumerating from 1).
 * 2. Given S = "aaa" your function should return 1. The furthest identifical digrams are "aa"s starting at position
 * 1 and 2.
 * 3. Given S = "codility" your function should return -1. There are no two identical digrams in S.
 *
 * Write an efficient algorithm for the following assumptions:
 * - N is an integer within the range [2, 300,000].
 * - string S consists only of lowercase letters(a-z).
 */
public class StringDiagram {
  public int solution(String string) {
    int furthestDistance = -1;
    HashMap<String, Integer> digramEarliestPositions = new HashMap<>();
    for (int i = 0; i < string.length() - 1; i++) {
      char[] digramCharacters = new char[2];
      digramCharacters[0] = string.charAt(i);
      digramCharacters[1] = string.charAt(i + 1);
      String digram = new String(digramCharacters);
      if (digramEarliestPositions.containsKey(digram)) {
        int earliestPosition = digramEarliestPositions.get(digram);
        int distance = i - earliestPosition;
        if (distance > furthestDistance) {
          furthestDistance = distance;
        }
      } else {
        digramEarliestPositions.put(digram, i);
      }
    }
    return furthestDistance;
  }
}
