package crackingthecodinginterview.hard;

import java.util.Map;
import java.util.Set;

/**
 * Given a list of words, write a program to find the longest word made of other words in the list.
 * EXAMPLE
 * Input cat, banana, dog, nana, walk, walker, dogwalker
 * Output: dogwalker
 */
public class LongestWord {
  private Map<String, Boolean> canWordBeMadeOfOtherWordsMap;

  public String longestWordMadeOfOtherWords(Set<String> words) {
    String longestString = "";
    int longestStringLength = 0;
    for (String word : words) {
      if (isStringMadeOfWords(word, words) &&
          word.length() > longestStringLength) {
        longestStringLength = word.length();
        longestString = word;
      }
    }
    return longestString;
  }

  private boolean isStringMadeOfWords(String word, Set<String> words) {
    if (canWordBeMadeOfOtherWordsMap.containsKey(word)) {
      return canWordBeMadeOfOtherWordsMap.get(word);
    }
    for (int index = 1; index <= word.length(); index++) {
      String subword = word.substring(0, index);
      if (words.contains(subword)) {
        String restOfSubString = word.substring(index);
        if (isStringMadeOfWords(restOfSubString, words)) {
          canWordBeMadeOfOtherWordsMap.put(restOfSubString, true);
          canWordBeMadeOfOtherWordsMap.put(word, true);
          return true;
        } else {
          canWordBeMadeOfOtherWordsMap.put(restOfSubString, false);
        }
      }
    }
    canWordBeMadeOfOtherWordsMap.put(word, false);
    return false;
  }
}
