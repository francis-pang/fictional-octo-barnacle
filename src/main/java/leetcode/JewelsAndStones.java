package leetcode;

import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {
  static class Solution {
    public int numJewelsInStones(String J, String S) {
      if (J == null || S == null || J.length() == 0 || S.length() == 0) {
        return 0;
      }
      if (J.equals(S)) {
        return J.length();
      }
      Set<Character> jewelSet = createSetFromString(J);
      return calculateNumberOfJewelInString(S, jewelSet);
    }

    private int calculateNumberOfJewelInString(String string, Set<Character> jewelSet) {
      int counter = 0;
      for (Character character : string.toCharArray()) {
        if (jewelSet.contains(character)) {
          counter++;
        }
      }
      return counter;
    }

    private Set<Character> createSetFromString(String string) {
      HashSet<Character> characterSet = new HashSet<>();
      for (Character character : string.toCharArray()) {
        characterSet.add(character);
      }
      return characterSet;
    }
  }
}
