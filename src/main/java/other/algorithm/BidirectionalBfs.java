package other.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BidirectionalBfs {
  private Queue<Node> nextIterationQueue;
  private Queue<Node> reverseNextIterationQueue;

  public List<Character> findShortestPath(Node source, Node target) {
    Map<Node, Node> predecessorTable = new HashMap<>();
    predecessorTable.put(source, null);
    Set<Node> visitedNodes = new HashSet<>();
    nextIterationQueue = new ArrayDeque<>();
    nextIterationQueue.add(source);

    Map<Node, Node> reversePredecessorTable = new HashMap<>();
    reversePredecessorTable.put(target, null);
    Set<Node> reverseVisitedNodes = new HashSet<>();
    reverseNextIterationQueue = new ArrayDeque<>();
    reverseNextIterationQueue.add(target);

    // If either of the queue is empty, it means that we have traversed all the nodes inside the graph, but we have
    // not found the target node
    Node intersectingNode = null;
    while (!nextIterationQueue.isEmpty() && !reverseNextIterationQueue.isEmpty()) {
      intersectingNode = findIntersectingNode(predecessorTable, visitedNodes, reverseVisitedNodes, false);
      if (intersectingNode != null) {
        break;
      }
      intersectingNode = findIntersectingNode(reversePredecessorTable, reverseVisitedNodes, visitedNodes, true);
      if (intersectingNode != null) {
        break;
      }
    }
    if (intersectingNode == null) {
      return new ArrayList<>();
    }
    // Build the backward path
    LinkedList<Character> path = new LinkedList<>();
    Node node = intersectingNode;
    while (node != null) {
      path.addFirst(node.val);
      node = predecessorTable.get(node);
    }
    node = reversePredecessorTable.get(intersectingNode);
    while (node != null) {
      path.addLast(node.val);
      node = reversePredecessorTable.get(node);
    }
    return path;
  }

  private Node findIntersectingNode(Map<Node, Node> predecessorTable,
                                    Set<Node> visitedNodes,
                                    Set<Node> reverseVisitedNodes,
                                    boolean traverseInReverseDirection) {
    Queue<Node> queue;
    if (!traverseInReverseDirection) {
      queue = nextIterationQueue;
    } else {
      queue = reverseNextIterationQueue;
    }
    Queue<Node> nextLevelQueue = new ArrayDeque<>();
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      if (!visitedNodes.add(node)) {
        continue;
      }
      for (Node neighbour : node.neighbours) {
        if (!predecessorTable.containsKey(neighbour)) {
          predecessorTable.put(neighbour, node);
        }
        nextLevelQueue.add(neighbour);
        if (reverseVisitedNodes.contains(neighbour)) {
          return neighbour;
        }
      }
    }
    if (!traverseInReverseDirection) {
      nextIterationQueue = nextLevelQueue;
    } else {
      reverseNextIterationQueue = nextLevelQueue;
    }
    return null;
  }

  public void printList(List<Character> list) {
    list.forEach(character -> System.out.printf("%s, ", character));
    System.out.println();
  }

  static class Node {
    public char val;
    public Set<Node> neighbours;

    public Node(char val) {
      this.val = val;
      neighbours = new HashSet<>();
    }

    @Override
    public String toString() {
      return Character.toString(val);
    }
  }

  public static void main(String[] args) {
    Node nodeA = new Node('a');
    Node nodeB = new Node('b');
    Node nodeC = new Node('c');
    Node nodeD = new Node('d');
    Node nodeE = new Node('e');
    Node nodeF = new Node('f');
    Node nodeG = new Node('g');
    nodeA.neighbours.add(nodeB);
    nodeB.neighbours.add(nodeA);
    nodeB.neighbours.add(nodeC);
    nodeB.neighbours.add(nodeD);
    nodeC.neighbours.add(nodeB);
    nodeC.neighbours.add(nodeE);
    nodeD.neighbours.add(nodeB);
    nodeD.neighbours.add(nodeF);
    nodeE.neighbours.add(nodeC);
    nodeE.neighbours.add(nodeF);
    nodeE.neighbours.add(nodeG);
    nodeG.neighbours.add(nodeE);

    BidirectionalBfs bidirectionalBfs = new BidirectionalBfs();
    List<Character> result = bidirectionalBfs.findShortestPath(nodeA, nodeG);
    bidirectionalBfs.printList(result);
  }
}