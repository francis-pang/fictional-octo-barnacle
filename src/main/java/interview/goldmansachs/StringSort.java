package interview.goldmansachs;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class StringSort {
  public static void main(String[] args) {
    PriorityQueue<Student> studentPriorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.rank));
    studentPriorityQueue = new PriorityQueue<>(Comparator.comparing(o -> o.name));
    TreeSet<Student> studentTreeSet = new TreeSet<>();

    String string1 = "name";
    String string2 = "!!!";
    String string3 = "3.3.3.3";
    String string4 = "343.3.3";

    // Sorting order
    // 1. Number ("\d")
    // 2. Special character ("\sc")
    // 3. Aplphbetical character ("\a")
    // 4. Other

    // K -> no of rules
    // a -> max length of the string possible
    // n -> number of strings for comparison

    // Time: O(a * n^2 * k)
    // Space: O(1)

    // Pre-computation
    // Time: O(na + nlogn) -> Total value, weighted
    // Space:O(n)
  }

  private int compareTo(String s1, String s2) {
    int s1Iter = 0;
    int s2Iter = 0;
    while (s1Iter < s1.length() && s2Iter < s2.length()) {
      char s1Char = s1.charAt(s1Iter);
      char s2Char = s2.charAt(s2Iter);

      if (s1Char == s2Char) {
        s1Iter++;
        s2Iter++;
        continue;
      }
      compareTwoChar(s1Char, s2Char, "\\d"); // Number
      compareTwoChar(s1Char, s2Char, "\\s"); // Special
      compareTwoChar(s1Char, s2Char, "[A-za-z]"); // Alphabetical
    }

    // Check s1 later
    if (s1Iter < s1.length()) {
      return 1;
    }
    // Check s2 later
    if (s2Iter < s2.length()) {
      return -1;
    }
    return 0;
  }

  private int compareTwoChar(char s1Char, char s2Char, String patternToCheck) {
    if (isOfPattern(s1Char, patternToCheck) && isOfPattern(s2Char, patternToCheck)) {
      // Check their natural order
      return Character.compare(s1Char, s2Char);
    } else if (isOfPattern(s1Char, patternToCheck)) {
      return 1; // s1 is bigger
    } else if (isOfPattern(s2Char, patternToCheck)) {
      return -1; // s2 bigger
    }
    return 0;
  }

  private boolean isOfPattern(char character, String regularExpress) {
    // implement to do
    return true;
  }
}
