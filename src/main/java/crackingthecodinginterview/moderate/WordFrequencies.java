package crackingthecodinginterview.moderate;

import java.util.HashMap;
import java.util.Map;

/**
 * Word Frequencies: Design a method to find the frequency of occurrences of any given word in a book. What if we
 * were running this algorithm multiple times?
 */
public class WordFrequencies {
  public int findFrequencyOfOccurrencesOneTimeUse(String[] book, String word) {
    int count = 0;
    for (String wordInBook : book) {
      if (word.equals(wordInBook)) {
        count++;
      }
    }
    return count;
  }

  Map<String, Integer> frequencyMap = new HashMap<>();

  public void init(String[] book) {
    for (String word : book) {
      frequencyMap.compute(word, (key, value) ->
        (value == null)
            ? 1
            : value++
      );
    }
  }

  public int frequencyOf(String word) {
    return frequencyMap.getOrDefault(word, 0); // stub
  }
}
