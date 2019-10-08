package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuessTheWord {
  static class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
      // Each word is 6 letters long, and they are all lowercase
      // So there are only 26 possible choices for each of the 6 letters
      // Convert wordList to ArrayList
      List<String> wordArrayList = new ArrayList<>();
      for (String string : wordlist) {
        wordArrayList.add(string);
      }

      String guess = wordlist[0]; // Seed
      int correctAnswer = master.guess(guess);
      if (correctAnswer == 6) {
        return;
      }
      findPossibleWords(wordArrayList, new HashSet<>(), correctAnswer, guess, master, 0);
    }

    private int findPossibleWords(List<String> wordList,
                                  Set<Character[]> tries,
                                  int exactMatch,
                                  String guess,
                                  Master master,
                                  int numberOfTry) {
      if (numberOfTry > 10) {
        System.exit(0);
      }
      int numberOfMatch = master.guess(guess);
      if (numberOfMatch == 0) {
        for (int i = 0; i < 6; i++) {
          char discardedCharacter = guess.charAt(i);
          // Remove all those with these discard characters
          for (int wordIndex = 0; wordIndex < wordList.size(); wordIndex++) {
            String word = wordList.get(wordIndex);
            if (word.charAt(i) == discardedCharacter) {
              wordList.remove(word);
            }
          }
        }
        String newGuess = wordList.get(0);
        return findPossibleWords(wordList, tries, 0, newGuess, master, numberOfTry + 1);
      }
      do {
        List<String> originalWordList = new ArrayList<>();
        originalWordList.addAll(wordList);
        List<String> nextGuesses = formNextPossibleWords(wordList, tries, numberOfMatch, guess);
        if (nextGuesses == null) {
          return -1;
        }
        String newGuess = wordList.get(0);
        numberOfMatch = findPossibleWords(nextGuesses, tries, numberOfMatch, newGuess, master, numberOfTry + 1);
        if (numberOfMatch < exactMatch) {
          wordList = originalWordList;
        }
      } while (numberOfMatch != 6);
      return 6;
    }

    private List<String> formNextPossibleWords(List<String> wordList,
                                               Set<Character[]> tries,
                                               int numberOfMatch,
                                               String guess) {
      Character[] combination = new Character[6];
      for (int i = 0; i < numberOfMatch; i++) {
        combination[i] = guess.charAt(i);
      }
      while (tries.contains(combination)) {
        // Move last possible character one step back
        for (int i = 4; i >= 0; i--) {
          if (combination[i + 1] == null && combination[i] != null) {
            combination[i] = null;
            combination[i + 1] = guess.charAt(i + 1);
            break;
          }
        }
        combination = null;
      }
      if (combination == null) {
        return null;
      }
      tries.add(combination);

      // Form the removed list
      List<String> nextGuesses = new ArrayList<>();
      for (String word : wordList) {
        boolean match = true;
        for (int i = 0; i < combination.length; i++) {
          if (combination[i] != null && combination[i] != word.charAt(i)) {
            match = false;
            break;
          }
        }
        if (match) {
          nextGuesses.add(word);
        }
      }
      return nextGuesses;
    }
  }

  // This is the Master's API interface.
  // You should not implement it, or speculate about its implementation
  interface Master {
    int guess(String word);
  }
}
