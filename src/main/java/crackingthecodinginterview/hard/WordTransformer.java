package crackingthecodinginterview.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordTransformer {
  public List<String> transformWord(List<String> dictionary, String start, String target) {
    // Assume that both start and target words are in dictionary.
    Map<Integer, Set<Node>> lengthToNodeSetTable = new HashMap<>();
    // Prepare node for the graph
    for (String word : dictionary) {
      int wordLength = word.length();
      if (!lengthToNodeSetTable.containsKey(wordLength)) {
        lengthToNodeSetTable.put(wordLength, new HashSet<>());
      }
      Set<Node> nodeSet = lengthToNodeSetTable.get(wordLength);
      Node node = new Node(word);
      nodeSet.add(node);
    }
    // Build the edge for each node
    Node startNode = null;
    Node targetNode = null;
    for (Set<Node> nodesOfSameLength : lengthToNodeSetTable.values()) {
      for (Node node : nodesOfSameLength) {
        String word = node.value;
        if (word.equals(start)) {
          startNode = node;
        }
        if (word.equals(target)) {
          targetNode = node;
        }
        for (Node iteratingNode : nodesOfSameLength) {
          if (iteratingNode.value.equals(word) || node.neighbours.contains(word)) {
            continue;
          }
          String iteratingWord = iteratingNode.value;
          if (hasOneEditDistance(word, iteratingWord)) {
            iteratingNode.neighbours.add(node);
            node.neighbours.add(iteratingNode);
          }
        }
      }
    }
    if (startNode == null || targetNode == null) {
      return new ArrayList<>();
    }
    List<String> transformationPath = new ArrayList<>();
    Set<Node> graph = lengthToNodeSetTable.get(start.length());
    Set<Node> visited = new HashSet<>();
    if (findTransformationPath(graph, visited, startNode, targetNode, transformationPath)) {
      return transformationPath;
    } else {
      return new ArrayList<>();
    }
  }

  private boolean findTransformationPath(Set<Node> graph, Set<Node> visitedNodes, Node node, Node targetNode, List<String> transformationPath) {
    if (node.equals(targetNode)) {
      transformationPath.add(targetNode.value);
      return true;
    }
    if (!visitedNodes.add(node)) {
      return false;
    }
    transformationPath.add(node.value);
    for (Node neighbour : node.neighbours) {
      if (findTransformationPath(graph, visitedNodes, neighbour, targetNode, transformationPath)) {
        return true;
      }
    }
    transformationPath.remove(transformationPath.size() - 1);
    return false;
  }

  private boolean hasOneEditDistance(String word, String iteratingWord) {
    boolean alreadyFoundOneEdit = false;
    for (int i = 0; i < word.length(); i++) {
      char character = word.charAt(i);
      char iteratingCharacter = iteratingWord.charAt(i);
      if (character != iteratingCharacter) {
        if (!alreadyFoundOneEdit) {
          alreadyFoundOneEdit = true;
        } else {
          return false;
        }
      }
    }
    return true;
  }

  class Node {
    String value;
    Set<Node> neighbours;

    public Node(String value) {
      this.value = value;
      neighbours = new HashSet<>();
    }
  }

  public static void main(String[] args) {
    WordTransformer wordTransformer = new WordTransformer();
    List<String> dictionary;
    String[] dictionaryArray = new String[]{"bash", "dash", "cash", "cbsh", "tbsh", "gash", "cbth", "thsh", "gahh", "chth", "thth"};
    dictionary = Arrays.asList(dictionaryArray);
    List<String> path = wordTransformer.transformWord(dictionary, "bash", "thsh");
    for (String word : path) {
      System.out.printf(word + ", ");
    }
    System.out.println();
  }
}
