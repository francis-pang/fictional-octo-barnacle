package crackingthecodinginterview.hard;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordTransformer {
  private Map<String, LinkedList<String>> stringTransformationPathMap;

  public List<String> findShortestPathToTransformWord(List<String> dictionary, String start, String target) {
    Graph graph = constructGraph(dictionary);
    BfsDirectionContext forwardTraversalBfs = new BfsDirectionContext(graph.nodes.get(start));
    BfsDirectionContext backwardTraversalBfs = new BfsDirectionContext(graph.nodes.get(target));
    forwardTraversalBfs.setReverseVisitedNodes(backwardTraversalBfs.visitedNodes);
    backwardTraversalBfs.setReverseVisitedNodes(forwardTraversalBfs.visitedNodes);

    Node intersectingNode = null;
    while (!forwardTraversalBfs.nextLevelQueue.isEmpty() && !backwardTraversalBfs.nextLevelQueue.isEmpty()) {
      intersectingNode = findIntersectingNode(forwardTraversalBfs);
      if (intersectingNode != null) {
        break;
      }
      intersectingNode = findIntersectingNode(backwardTraversalBfs);
      if (intersectingNode != null) {
        break;
      }
    }
    if (intersectingNode == null) {
      throw new IllegalStateException("Cannot find a path");
    }
    LinkedList<String> path = new LinkedList<>();
    String word = intersectingNode.val;
    while (word != null) {
      path.addFirst(word);
      word = forwardTraversalBfs.predecessorTable.get(word);
    }
    word = backwardTraversalBfs.predecessorTable.get(intersectingNode.val);
    while (word != null) {
      path.addLast(word);
      word = backwardTraversalBfs.predecessorTable.get(word);
    }
    return path;
  }

  private Node findIntersectingNode(BfsDirectionContext bfsDirectionContext) {
    Queue<Node> queue = bfsDirectionContext.nextLevelQueue;
    bfsDirectionContext.nextLevelQueue = new ArrayDeque<>();
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      if (!bfsDirectionContext.visitedNodes.add(node)) {
        continue;
      }
      for (Node neighbour : node.neighbours) {
        if (!bfsDirectionContext.predecessorTable.containsKey(neighbour.val)) {
          bfsDirectionContext.predecessorTable.put(neighbour.val, node.val);
        }
        if (bfsDirectionContext.reverseVisitedNodes.contains(neighbour)) {
          return neighbour;
        }
        bfsDirectionContext.nextLevelQueue.add(neighbour);
      }
    }
    return null;
  }

  class BfsDirectionContext {
    private Map<String, String> predecessorTable;
    private Set<Node> visitedNodes;
    private Queue<Node> nextLevelQueue;
    private Set<Node> reverseVisitedNodes;

    public BfsDirectionContext(Node startingNode) {
      predecessorTable = new HashMap<>();
      predecessorTable.put(startingNode.val, null);
      visitedNodes = new HashSet<>();
      nextLevelQueue = new ArrayDeque<>();
      nextLevelQueue.add(startingNode);
    }

    public void setReverseVisitedNodes(Set<Node> reverseVisitedNodes) {
      this.reverseVisitedNodes = reverseVisitedNodes;
    }
  }

  private Graph constructGraph(List<String> dictionary) {
    Graph graph = new Graph();
    dictionary.forEach(word -> graph.nodes.put(word, new Node(word)));
    dictionary.forEach(word -> {
      Node node = graph.nodes.get(word);
      // This is with the assumption that the word is limited to lower case alphabet.
      for (int pos = 0; pos < word.length(); pos++) {
        for (int i = 'a'; i <= 'z'; i++) {
          String alteredWord = word.substring(0, pos) + Character.toString(i) + word.substring(pos + 1);
          if (alteredWord.equals(word)) {
            continue;
          }
          Node neighbour = graph.nodes.get(alteredWord);
          if (neighbour == null) {
            continue;
          }
          node.neighbours.add(neighbour);
          neighbour.neighbours.add(node);
        }
      }
    });
    return graph;
  }

  public void printList(List<String> list) {
    list.forEach(word -> System.out.printf("%s, ", word));
    System.out.println();
  }

  class Graph {
    public Map<String, Node> nodes;

    public Graph() {
      nodes = new HashMap<>();
    }
  }

  class Node {
    public String val;
    public Set<Node> neighbours;

    public Node(String val) {
      this.val = val;
      neighbours = new HashSet<>();
    }
  }

  public static void main(String[] args) {
    WordTransformer wordTransformer = new WordTransformer();
    List<String> dictionary = List.of("damp", "lamp", "limp", "lime", "like", "nut", "word", "transformer", "given", "two",
        "of", "equal", "length", "that", "are", "in", "a");
    List<String> result = wordTransformer.findShortestPathToTransformWord(dictionary, "damp", "like");
    wordTransformer.printList(result);
  }
}
