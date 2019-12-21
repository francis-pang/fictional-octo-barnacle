package leetcode;

public class LongestSubstringWithoutRepeatingCharacters {
  public int lengthOfLongestSubstring(String s) {
    if (s.length() == 1) {
      return 1;
    }

    int[] lastSeenPositions = new int[128];
    initialiseArray(lastSeenPositions);
    int longest = 0;
    int startPoint = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      int lastSeen = lastSeenPositions[calIndex(c)];
      if (lastSeen >= startPoint) {
        int length = i - startPoint;
        if (longest < length) {
          longest = length;
        }
        startPoint = lastSeen + 1;
      }
      lastSeenPositions[calIndex(c)] = i;
    }
    int length = s.length() - startPoint;
    if (longest < length) {
      return length;
    } else {
      return longest;
    }
  }

  private void initialiseArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      array[i] = -1;
    }
  }

  private int calIndex(char character) {
    return character;
  }
}
