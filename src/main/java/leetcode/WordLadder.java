package leetcode;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class WordLadder {
  private static int UNREACHABLE = 0;

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    // Pre-optimisation: Check that end word exists in word list
    int endWordIndex = getEndWordPositionInWordList(wordList, endWord);
    if (endWordIndex == -1) {
      return UNREACHABLE;
    }

    wordList.add(beginWord);
    // Initialise normal flow
    boolean[] processedNormalWords = new boolean[wordList.size()];
    boolean[] seenNormalWords = new boolean[wordList.size()];
    Queue<Integer> nextDistanceNormalQueue = new ArrayDeque<>();
    Queue<Integer> currentNormalQueue;
    int normalDistance = 0;
    nextDistanceNormalQueue.add(wordList.size() - 1);
    seenNormalWords[wordList.size() - 1] = true;

    // Initialise reverse flow
    boolean[] processReverseWords = new boolean[wordList.size()];
    boolean[] seenReverseWords = new boolean[wordList.size()];
    Queue<Integer> nextDistanceReverseQueue = new ArrayDeque<>();
    Queue<Integer> currentReverseQueue;
    int reverseDistance = 0;
    nextDistanceReverseQueue.add(endWordIndex);
    seenReverseWords[endWordIndex] = true;

    boolean endSearch = false;
    boolean found = false;
    while (!endSearch) {
      if (!nextDistanceNormalQueue.isEmpty()) {
        normalDistance++;
        currentNormalQueue = nextDistanceNormalQueue;
        nextDistanceNormalQueue = new ArrayDeque<>();
        while (!currentNormalQueue.isEmpty() && !endSearch) {
          Integer currentWordIndex = currentNormalQueue.poll();
          if (processedNormalWords[currentWordIndex]) {
            continue;
          }
          for (int index = 0; index < wordList.size(); index++) {
            String word = wordList.get(index);
            String currentWord = wordList.get(currentWordIndex);
            // Can be make faster by looping through 'a' to 'z' and checking if the amended word is in set or not.
            if (!isOneEditDistanceAway(word, currentWord)) {
              continue;
            }
            if (seenReverseWords[index]) {
              found = true;
              endSearch = true;
              break;
            }
            nextDistanceNormalQueue.add(index);
            seenNormalWords[index] = true;
          }
          processedNormalWords[currentWordIndex] = true;
        }
      } else {
        break;
      }

      if (!nextDistanceReverseQueue.isEmpty() && !endSearch) {
        reverseDistance++;
        currentReverseQueue = nextDistanceReverseQueue;
        nextDistanceReverseQueue = new ArrayDeque<>();
        while (!currentReverseQueue.isEmpty()) {
          int currentWordIndex = currentReverseQueue.poll();
          if (processReverseWords[currentWordIndex]) {
            continue;
          }
          for (int index = 0; index < wordList.size(); index++) {
            String word = wordList.get(index);
            String currentWord = wordList.get(currentWordIndex);
            if (!isOneEditDistanceAway(word, currentWord)) {
              continue;
            }
            if (seenNormalWords[index]) {
              found = true;
              endSearch = true;
              break;
            }
            nextDistanceReverseQueue.add(index);
            seenReverseWords[index] = true;
          }
          processReverseWords[currentWordIndex] = true;
        }
      }
    }

    if (found) {
      int totalDistance = normalDistance + reverseDistance;
      return totalDistance + 1;
    } else {
      return UNREACHABLE;
    }
  }

  private int getEndWordPositionInWordList(List<String> wordList, String endWord) {
    for (int index = 0; index < wordList.size(); index++) {
      String word = wordList.get(index);
      if (word.equals(endWord)) {
        return index;
      }
    }
    return -1;
  }

  private boolean isOneEditDistanceAway(String firstWord, String secondWord) {
    if (firstWord.equals(secondWord)) {
      return false;
    }
    if (firstWord.length() != secondWord.length()) {
      return false;
    }
    boolean foundOneEdit = false;
    for (int i = 0; i < firstWord.length(); i++) {
      char firstWordChar = firstWord.charAt(i);
      char secondWordChar = secondWord.charAt(i);
      if (firstWordChar != secondWordChar) {
        if (!foundOneEdit) {
          foundOneEdit = true;
        } else {
          return false;
        }
      }
    }
    return foundOneEdit;
  }
}
