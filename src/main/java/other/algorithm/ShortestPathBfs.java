package other.algorithm;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringJoiner;

public class ShortestPathBfs {
  public void findShortestPath(Graph graph, Node start) {
    shortestPathBfs(graph, start);
  }

  private void shortestPathBfs(Graph graph, Node start) {
    /**
     * Integer represent the distance from the source to the node
     */
    Map<Node, Integer> distanceByNodeTable = new HashMap<>();

    /**
     * A table to mark what should your predecessor node. Need to use this to trace back your shortest path
     */
    Map<Node, Node> predecessorTable = new HashMap<>();
    int distance = 0;
    Queue<Node> currentDistanceQueue;
    Queue<Node> nextDistanceQueue = new ArrayDeque<>();
    Set<Node> visitedNodes = new HashSet<>();

    nextDistanceQueue.add(start);
    while (!nextDistanceQueue.isEmpty()) {
      currentDistanceQueue = nextDistanceQueue;
      nextDistanceQueue = new ArrayDeque<>();
      distance++;
      while (!currentDistanceQueue.isEmpty()) {
        Node node = currentDistanceQueue.poll();
        if (visitedNodes.contains(node)) {
          continue;
        }
        visitedNodes.add(node);
        for (Node outNode : node.outgoingEdges) {
          if (!predecessorTable.containsKey(outNode)) {
            predecessorTable.put(outNode, node);
            distanceByNodeTable.put(outNode, distance);
          }
          nextDistanceQueue.add(outNode);
        }
      }
    }
    printShortestPath(graph, start, predecessorTable, distanceByNodeTable);
  }

  private void printShortestPath(Graph graph, Node start, Map<Node, Node> predecessorMap,
                                 Map<Node, Integer> distanceByNodeTable) {
    for (Node node : graph.nodes) {
      if (node.equals(start)) {
        continue;
      }
      if (!distanceByNodeTable.containsKey(node)) {
        System.out.printf("There is no shortest path from %c to %c.\n", start.label, node.label);
        continue;
      }
      int shortestDistance = distanceByNodeTable.get(node);
      System.out.printf("The shortest distance from %c to %c is %d unit away.\n", start.label, node.label,
          shortestDistance);
      Stack<Character> reversePath = new Stack<>();
      Node predecessor = node;
      reversePath.push(predecessor.label);
      do {
        predecessor = predecessorMap.get(predecessor);
        reversePath.push(predecessor.label);
      } while (!predecessor.equals(start));
      System.out.printf("Shortest path: ");
      while (!reversePath.isEmpty()) {
        System.out.printf("%c,", reversePath.pop());
      }
      System.out.println();
    }
  }

  public static class Graph {
    public Set<Node> nodes;

    public Graph() {
      nodes = new HashSet<>();
    }
  }

  public static class Node {
    public char label;
    public List<Node> outgoingEdges;

    public Node(char label, List<Node> outgoingEdges) {
      this.label = label;
      this.outgoingEdges = outgoingEdges;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
          .add("label=" + label)
          .toString();
    }
  }
}
