package facebook.abcs.hashtables;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KMostFrequentWordsInAString {
  public static void main(String[] args) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    Scanner scanner = new Scanner(System.in);
    String string = scanner.nextLine();
    // empty
    if (string.length() == 0) {
      return;
    }
    int kMostFrequentWords = scanner.nextInt();
    // 0, negative
    if (kMostFrequentWords <= 0) {
      return;
    }
    scanner.close();

    printKMostFrequentWord(string, kMostFrequentWords);
  }

  private static void printKMostFrequentWord(String string, int kMostFrequentWords) {
    Map<String, Integer> wordFrequencyMap = buildWordFrequencyMap(string);
    PriorityQueue<WordFrequency> priorityQueue = new PriorityQueue<>((o1, o2) -> {
      if (o1.frequency != o2.frequency) {
        return o2.frequency - o1.frequency;
      } else {
        return compareString(o1.word, o2.word);
      }
    });
    wordFrequencyMap.forEach((word, frequency) -> priorityQueue.add(new WordFrequency(word, frequency)));
    printKMostFrequentWords(priorityQueue, kMostFrequentWords);
  }

  private static int compareString(String word1, String word2) {
    if (word1.equals(word2)) {
      return 0;
    }
    for (int i = 0; i < word1.length(); i++) {
      if (word2.length() == i) {
        return -1;
      }
      if (word1.charAt(i) == word2.charAt(i)) {
        continue;
      } else {
        return Character.compare(word1.charAt(i), word2.charAt(i));
      }
    }
    if (word2.length() > word1.length()) {
      return 1;
    }
    return 0;
  }

  private static void printKMostFrequentWords(PriorityQueue<WordFrequency> priorityQueue, int kMostFrequentWords) {
    for (int i = 0; i < kMostFrequentWords; i++) {
      WordFrequency wordFrequency = priorityQueue.poll();
      System.out.println(wordFrequency.word + " " + wordFrequency.frequency);
    }
  }

  public static class WordFrequency {
    public String word;
    public int frequency;

    public WordFrequency(String word, int frequency) {
      this.word = word;
      this.frequency = frequency;
    }
  }

  private static Map<String, Integer> buildWordFrequencyMap(String string) {
    Map<String, Integer> wordFrequency = new HashMap<>();
    String[] words = string.split("^[A-Za-z0-9]");
    for (String word : words) {
      if (word.length() == 0) continue;
      wordFrequency.compute(word.toLowerCase(), (key, value) -> (value == null) ? 1 : value + 1);
    }
    return wordFrequency;
  }
}
