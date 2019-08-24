package crackingthecodinginterview.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordRectangle {
  public List<String> buildLargestRectangle(ArrayList<String> dictionary) {
    // Edge case:
    // Null

    // Empty size

    // Build a trie for each String, while building the word set as well


    Map<Integer, Set<String>> wordCollectionByWordLength = null;
    Map<Integer, TrieNode> trieTableByWordLength = null;
    // The structure is n number of tries, one for each length, so it's a map
    constructTriesForDictionary(dictionary, trieTableByWordLength, wordCollectionByWordLength);

    // The actual building logic is here
    return buildLargestRectangle(trieTableByWordLength, wordCollectionByWordLength);
  }

  private List<String> buildLargestRectangle(Map<Integer, TrieNode> trieTableByWordLength,
                                             Map<Integer, Set<String>> wordCollectionByWordLength) {
    // Keep a record of the biggest surface area ever calculated
    int largestRectangleSurfaceArea = 0;
    // Keep a record of the biggest rectangle record thus far
    List<String> biggestRectangle = null;
    // Iterate through each string in the list and try to use it as the base of the rectangle
    // TODO: Refactor for lambda later
    for (Set<String> wordSet : wordCollectionByWordLength.values()) {
      for (String baseLayerWord : wordSet) {
        // Find matching Trie now
        Set<TrieNode> matchingTries = findMatchingTrieNode(trieTableByWordLength, baseLayerWord);
        for (TrieNode trieNode : matchingTries) {
          List<String> wordRectangle = findMatchingTrieNode(trieNode,
              trieTableByWordLength.get(baseLayerWord.length()));
          int areaOfWordRectangle = calculateAreaOfWordRectangle(wordRectangle);
          if (areaOfWordRectangle > largestRectangleSurfaceArea) {
            largestRectangleSurfaceArea = areaOfWordRectangle;
            biggestRectangle = wordRectangle;
          }
        }
      }
    }
    // Then build on top of this
    return biggestRectangle;
  }

  private int calculateAreaOfWordRectangle(List<String> wordRectangle) {
    int length = wordRectangle.size();
    int width = wordRectangle.get(0).length();
    return length * width;
  }

  private Set<TrieNode> findMatchingTrieNode(Map<Integer, TrieNode> trieTableByWordLength, String string) {
    Set<Character> uniqueCharacters = extractUniqueCharactersFrom(string);
    Set<Integer> integerWithAllUniqueCharacters = trieTableByWordLength.keySet();
    Set<Integer> remainingTrieNode;
    for (char character : uniqueCharacters) {
      remainingTrieNode = new HashSet<>();
      for (Integer wordLength : integerWithAllUniqueCharacters) {
        if (wordLength < uniqueCharacters.size()) {
          continue;
        }
        TrieNode root = trieTableByWordLength.get(wordLength);
        if (root.childrenTableByCharacter.containsKey(character)) {
          remainingTrieNode.add(wordLength);
        }
      }
      integerWithAllUniqueCharacters = remainingTrieNode;
    }
    Set<TrieNode> trieNodesWithAllCharacters = new HashSet<>();
    for (int wordLength : integerWithAllUniqueCharacters) {
      trieNodesWithAllCharacters.add(trieTableByWordLength.get(wordLength));
    }
    return trieNodesWithAllCharacters;
  }

  private Set<Character> extractUniqueCharactersFrom(String string) {
    Set<Character> uniqueCharacters = new HashSet<>();
    for (char character : string.toCharArray()) {
      uniqueCharacters.add(character);
    }
    return uniqueCharacters;
  }

  private List<String> findMatchingTrieNode(TrieNode comparatorNode, TrieNode comparingNode) {
    char value = comparatorNode.value;

    //TODO: Change to lambda
    List<String> suffixStringList = new ArrayList<>();
    if (comparatorNode.childrenTableByCharacter.containsKey(value)) {
      for (TrieNode childNode : comparatorNode.childrenTableByCharacter.get(value).childrenTableByCharacter.values()) {
        suffixStringList.addAll(findMatchingTrieNode(childNode, comparatorNode));
      }
    }
    for (String string : suffixStringList) {
      string = Character.toString(value).concat(string);
    }
    return suffixStringList;
  }

  private void constructTriesForDictionary(ArrayList<String> dictionary, Map<Integer, TrieNode> trieTableByWordLength,
                                           Map<Integer, Set<String>> wordCollectionByWordLength) {
    // Remember that I want an reverse ordered sorted map, so I need to use a custom comparator
    //TODO
  }

  public class TrieNode {
    public char value;
    public Map<Character, TrieNode> childrenTableByCharacter;

    public TrieNode(char value) {
      this.value = value;
      childrenTableByCharacter = new HashMap<>();
    }
  }
}
