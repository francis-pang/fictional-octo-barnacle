package crackingthecodinginterview.moderate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class T9 {
  public List<String> getPossibleWords(List<String> validWords, int number) {
    Map<String, List<String>> numberToWordsTable = new HashMap<>();
    buildNumberToWordsTable(numberToWordsTable, validWords);
    String numberString = Integer.toString(number);
    return numberToWordsTable.getOrDefault(numberString, new ArrayList<>());
  }

  private void buildNumberToWordsTable(Map<String, List<String>> numberToWordsTable, List<String> validWords) {
    Map<Character, Integer> characterToNumberMap = new HashMap<>();
    characterToNumberMap.put('a', 2);
    characterToNumberMap.put('b', 2);
    characterToNumberMap.put('c', 2);
    characterToNumberMap.put('d', 3);
    characterToNumberMap.put('e', 3);
    characterToNumberMap.put('f', 3);
    characterToNumberMap.put('g', 4);
    characterToNumberMap.put('h', 4);
    characterToNumberMap.put('i', 4);
    characterToNumberMap.put('j', 5);
    characterToNumberMap.put('k', 5);
    characterToNumberMap.put('l', 5);
    characterToNumberMap.put('m', 6);
    characterToNumberMap.put('n', 6);
    characterToNumberMap.put('o', 6);
    characterToNumberMap.put('p', 7);
    characterToNumberMap.put('q', 7);
    characterToNumberMap.put('r', 7);
    characterToNumberMap.put('s', 7);
    characterToNumberMap.put('t', 8);
    characterToNumberMap.put('u', 8);
    characterToNumberMap.put('v', 8);
    characterToNumberMap.put('w', 9);
    characterToNumberMap.put('x', 9);
    characterToNumberMap.put('y', 9);
    characterToNumberMap.put('z', 9);
    for (String word : validWords) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        int numericValue = characterToNumberMap.get(c);
        sb.append(numericValue);
      }
      String numberSequence = sb.toString();
      if (!numberToWordsTable.containsKey(numberSequence)) {
        numberToWordsTable.put(numberSequence, new ArrayList<>());
      }
      List<String> words = numberToWordsTable.get(numberSequence);
      words.add(word);
    }
  }

}
