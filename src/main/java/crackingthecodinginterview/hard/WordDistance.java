package crackingthecodinginterview.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * You have a large text file containing words. Given any two words, find the shortest distance (in terms of number
 * of words) between them in the file. If the operation wi" be repeated many times for the same file (but different
 * pairs of words), can you optimize your solution?
 */
public class WordDistance {
  private Map<String, Map<String, Integer>> shortestDistanceMap;

  public int findShortestDistanceBetweenWords(String[] textFile, String firstWord, String secondWord) {
    int positionOfFirstWord = 0;
    int positionOfSecondWord = 0;
    int shortestDistanceFound = Integer.MAX_VALUE;

    for (int index = 0; index < textFile.length; index++) {
      if (textFile[index].equals(firstWord) ||
          textFile[index].equals(secondWord)) {
        if (textFile[index].equals(firstWord)) {
          if (positionOfFirstWord < positionOfSecondWord) {
            int distance = index - positionOfSecondWord;
            if (distance < shortestDistanceFound) {
              shortestDistanceFound = distance;
            }
          } else {
            positionOfSecondWord = 0;
          }
          positionOfFirstWord = index;
        }

        if (textFile[index].equals(secondWord)) {
          if (positionOfSecondWord < positionOfFirstWord) {
            int distance = index - positionOfFirstWord;
            if (distance < shortestDistanceFound) {
              shortestDistanceFound = distance;
            }
          } else {
            positionOfFirstWord = 0;
          }
          positionOfSecondWord = index;
        }
      }
    }
    return shortestDistanceFound;
  }

  public int repeatFindShortestDistanceBetweenWords(String[] textFile, String firstWord, String secondWord) {
    if (shortestDistanceMap == null) {
      prePopulateMap(textFile);
    }
    return shortestDistanceMap.get(firstWord).get(secondWord);
  }

  private void prePopulateMap(String[] textFile) {
    shortestDistanceMap = new HashMap<>();
    Map<String, Integer> positionOfWords = new HashMap<>();

    for (int index = 0; index < textFile.length; index++) {
      String word = textFile[index];
      if (positionOfWords.containsKey(word)) {
        int lastFoundPositionOfWord = positionOfWords.get(word);
        int startPosition = (int) Math.ceil((index - lastFoundPositionOfWord) / 2) + lastFoundPositionOfWord;
        Map<String, Integer> selfWordMap = shortestDistanceMap.get(word);
        for (int i = startPosition; i < index; i++) {
          int newShortestDistance = index - i;
          String targetWord = textFile[i];
          selfWordMap.put(targetWord, newShortestDistance);
          Map<String, Integer> targetWordMap = shortestDistanceMap.get(targetWord);
          targetWordMap.put(word, newShortestDistance);
        }
      } else {
        Map<String, Integer> mapForNewWord = new HashMap<>();
        for (Map.Entry<String, Integer> entry : positionOfWords.entrySet()) {
          String string = entry.getKey();
          int position = entry.getValue();
          mapForNewWord.put(string, index - position);
          Map<String, Integer> stringMap = shortestDistanceMap.get(string);
          stringMap.put(word, index - position);
        }
        shortestDistanceMap.put(word, mapForNewWord);
      }
      positionOfWords.put(word, index);
    }
  }
}
