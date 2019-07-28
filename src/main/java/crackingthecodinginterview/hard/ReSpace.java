package crackingthecodinginterview.hard;

import java.util.*;

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
  private TriesNode root;

  public String unconcatenate(List<String> dictionary, String document) {
    populateTries(dictionary);

    return putSpaceToDocument(document);
  }

  private String putSpaceToDocument(String document) {
    char[] documentArray = document.toCharArray();
    StringJoiner answerStringJoiner = new StringJoiner(" ");

    int index = 0;
    String unrecognisedWord = "";
    while (index < documentArray.length) {
      int endIndex = locateWordFromDictionary(documentArray, index, root);
      if (endIndex == -1) { // Not such word
        unrecognisedWord = unrecognisedWord + documentArray[index];
        index++;
      } else {
        if (unrecognisedWord.length() >= 0) {
          answerStringJoiner.add(unrecognisedWord);
        }
        answerStringJoiner.add(Arrays.copyOfRange(documentArray, index, endIndex).toString());
        index = endIndex + 1;
      }
    }
    if (unrecognisedWord.length() >= 0) {
      answerStringJoiner.add(unrecognisedWord);
    }
    return answerStringJoiner.toString();
  }

  private int locateWordFromDictionary(char[] documentArray, int index, TriesNode triesNode) {
    if (triesNode.children.containsKey(documentArray[index])) {
      return locateWordFromDictionary(documentArray, index + 1,
          triesNode.children.get(documentArray[index]));
    } else {
      if (triesNode.isCompleteWord) {
        return index;
      } else {
        return -1;
      }
    }
  }

  private void populateTries(List<String> dictionary) {
    root = new TriesNode();
    for (String word : dictionary) {
      TriesNode triesNode = root;
      for (Character character : word.toCharArray()) {
        if (triesNode.children.containsKey(character)) {
          triesNode = triesNode.children.get(character);
        } else {
          TriesNode newTriesNode = new TriesNode();
          triesNode.children.put(character, newTriesNode);
          triesNode = newTriesNode;
        }
      }
      triesNode.isCompleteWord = true;
    }
  }

  public class TriesNode {
    Map<Character, TriesNode> children;
    boolean isCompleteWord;

    public TriesNode() {
    }

    public TriesNode(char value, boolean isCompleteWord) {
      this.isCompleteWord = isCompleteWord;
      children = new HashMap<>();
    }
  }
}
