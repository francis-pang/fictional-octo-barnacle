package elementsofprogramminginterviews.graphs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class TransformOneStringToAnother {
  private static final int NOT_FOUND = -1;
  public int shortestProductionSequence(Set<String> dictionary, String start, String target) {
    if (!dictionary.contains(start) || !dictionary.contains(target)) {
      return NOT_FOUND;
    }
    Map<String, Node> nodeTable = buildGraph(dictionary);

    Queue<Node> nextLevelQueue = new ArrayDeque<>();
    int length = 0;
    Node startNode = nodeTable.get(start);
    nextLevelQueue.add(startNode);
    Set<Node> visited = new HashSet<>();
    boolean found = false;
    while (!found && !nextLevelQueue.isEmpty()) {
      Queue<Node> queue = nextLevelQueue;
      nextLevelQueue = new ArrayDeque<>();
      length++;
      while (!queue.isEmpty()) {
        Node node = queue.poll();
        if (node.value.equals(target)) {
          found = true;
          continue;
        }
        if (!visited.add(node)) {
          continue;
        }
        nextLevelQueue.addAll(node.neighbours);
      }
    }
    if (!found) {
      return NOT_FOUND;
    } else {
      return length;
    }
  }

  private Map<String, Node> buildGraph(Set<String> dictionary) {
    Map<String, Node> nodeTable = new HashMap<>();
    for (String word : dictionary) {
      Node node = nodeTable.computeIfAbsent(word, k -> new Node(k));
      dictionary.forEach(otherWord -> {
        Node otherNode = nodeTable.computeIfAbsent(otherWord, k -> new Node(k));
        if (!node.neighbours.contains(otherNode) && oneEditDistanceAway(word, otherWord)) {
          node.neighbours.add(otherNode);
          otherNode.neighbours.add(node);
        }
      });
    }
    return nodeTable;
  }

  private boolean oneEditDistanceAway(String first, String second) {
    if (first.length() != second.length()) {
      return false;
    }
    int edit = 0;
    for (int i = 0; i < first.length(); i++) {
      if (first.charAt(i) != second.charAt(i)) {
        edit++;
      }
      if (edit > 1) {
        return false;
      }
    }
    return true;
  }

  public class Node {
    public String value;
    public Set<Node> neighbours;

    public Node(String value) {
      this.value = value;
      neighbours = new HashSet<>();
    }
  }
}