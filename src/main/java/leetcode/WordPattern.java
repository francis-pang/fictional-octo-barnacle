package leetcode;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
  public boolean wordPattern(String pattern, String str) {
    Map<Character, Integer> patternMap = new HashMap<>();
    int[] patternSequence = new int[pattern.length()];
    int patternIndex = 0;
    // Digest pattern
    for (int i = 0; i < pattern.length(); i++) {
      char c = pattern.charAt(i);
      if (!patternMap.containsKey(c)) {
        patternMap.put(c, patternIndex);
        patternIndex++;
      }
      patternSequence[i] = patternMap.get(c);
    }

    // Parse str
    String[] strArray = str.split(" ");
    Map<String, Integer> stringMap = new HashMap<>();
    patternIndex = 0;
    if (strArray.length != patternSequence.length) {
      return false;
    }
    for (int i = 0; i < patternSequence.length; i++) {
      int expectedId = patternSequence[i];
      String word = strArray[i];
      if (!stringMap.containsKey(word)) {
        stringMap.put(word, patternIndex);
        patternIndex++;
      }
      int wordId = stringMap.get(word);
      if (expectedId != wordId) {
        return false;
      }
    }
    return true;
  }
}
