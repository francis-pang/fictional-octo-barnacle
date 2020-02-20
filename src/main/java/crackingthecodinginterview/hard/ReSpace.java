package crackingthecodinginterview.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Oh, no! You have accidentally removed all spaces, punctuation, and capitalization in a lengthy document. A
 * sentence Iike"I reset the computer. It still didn't boot!" became"iresetthecomputeri tstilldidntboot': You'll deal
 * with the punctuation and capitalization later; right now you need to re-insert the spaces. Most of the words are
 * in a dictionary but a few are not. Given a dictionary (a list of strings) and the document (a string)' design an
 * algorithm to unconcatenate the document in a way that minimizes the number of unrecognized characters.
 * EXAMPLE:
 * Input jesslookedjustliketimherbrother
 * Output: jess looked just like tim her brother (7 unrecognized characters)
 */
public class ReSpace {
  public int minimumUnrecognisedCharacters(String s, List<String> dictionary) {
    TrieNode trieRoot = constructTrieTree(dictionary);
    Map<Range, Integer> memoTable = new HashMap<>();
    return findMinimumUnrecognisedCharacters(s, new Range(0, s.length() - 1), trieRoot, memoTable);
  }

  private TrieNode constructTrieTree(List<String> dictionary) {
    TrieNode root = new TrieNode();
    for (String word : dictionary) {
      TrieNode node = root;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        node = node.children.compute(c, (k, v) -> v == null ? new TrieNode() : v);
      }
      node.isAWord = true;
    }
    return root;
  }

  private int findMinimumUnrecognisedCharacters(String s,
                                                Range range,
                                                TrieNode root,
                                                Map<Range, Integer> memoTable) {
    if (memoTable.containsKey(range)) {
      return memoTable.get(range);
    }
    if (range.left >= s.length() - 1 || range.right < 0) {
      return 0;
    }
    int minimumLength = range.right - range.left + 1;

    for (int i = range.left; i <= range.right; i++) {
      int iteratorIndex = i;
      TrieNode node = root;
      while (iteratorIndex <= range.right && node != null) {
        char c = s.charAt(iteratorIndex);
        node = node.children.get(c);
        if (node != null && node.isAWord) {
          int length = findMinimumUnrecognisedCharacters(s, new Range(range.left, i - 1), root, memoTable) +
              findMinimumUnrecognisedCharacters(s, new Range(iteratorIndex + 1, range.right), root, memoTable);
          if (length < minimumLength) {
            minimumLength = length;
          }
        }
        iteratorIndex++;
      }
    }
    memoTable.put(range, minimumLength);
    return minimumLength;
  }

  class Range {
    public int left;
    public int right;

    public Range(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }

  class TrieNode {
    public boolean isAWord;
    public Map<Character, TrieNode> children;

    public TrieNode() {
      children = new HashMap<>();
      isAWord = false;
    }
  }

  public static void main(String[] args) {
    List<String> dictionary = new ArrayList<>();
    dictionary.add("i");
    dictionary.add("the");
    dictionary.add("computer");
    dictionary.add("compute");
    dictionary.add("he");
    ReSpace respace = new ReSpace();
    System.out.println(respace.minimumUnrecognisedCharacters("iresetthecomputer", dictionary));
  }
}