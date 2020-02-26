package other.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

public class BellmanFord {
  private static int UNDEFINED_DISTANCE = -1;

  public void findShortestPath(Graph graph, Node start) {
    // Initialise graph
    PriorityQueue<Node> queue = new PriorityQueue<>();
    Map<Node, Node> predecessorMap = new HashMap<>();

    for (Node node : graph.nodes) {
      if (node.equals(start)) {
        node.distanceToSource = 0;
      } else {
        node.distanceToSource = Integer.MAX_VALUE;
      }
      predecessorMap.put(node, null);
      queue.add(node);
    }

    // Select node
    IntStream
        .range(1, graph.nodes.size())
        .forEach($ -> graph.nodes.forEach(node -> node.outgoingEdges.forEach(edgeNode -> {
          // Do relaxation
          relaxation(node, edgeNode, predecessorMap);
        })));

    // Double check
    checkForNegativeCycle(graph, predecessorMap);

    // return shortest path
    printShortestPath(graph, start, predecessorMap);
  }

  private void checkForNegativeCycle(Graph graph, Map<Node, Node> predecessorMap) {
    graph.nodes.forEach(node -> node.outgoingEdges.forEach(edge -> {
      Node outNode = edge.destination;
      if (outNode.distanceToSource > (node.distanceToSource + edge.weight)) {
        outNode.distanceToSource = UNDEFINED_DISTANCE;
        predecessorMap.put(outNode, null);
      }
    }));
  }

  private void printShortestPath(Graph graph, Node start, Map<Node, Node> predecessorMap) {
    for (Node node : graph.nodes) {
      int shortestDistance = node.distanceToSource;
      if (shortestDistance == Integer.MAX_VALUE) {
        System.out.printf("There is no shortest path from %c to %c.\n", node.label, start.label);
        continue;
      }
      if (shortestDistance == UNDEFINED_DISTANCE) {
        System.out.printf("There is a negative cycle from %c to %c.\n", node.label, start.label);
        continue;
      }
      if (node.equals(start)) {
        continue;
      }
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

  private void relaxation(Node node, Edge edge, Map<Node, Node> predecessorMap) {
    Node outNode = edge.destination;
    long checkingDistance = (long) node.distanceToSource + (long) edge.weight;
    if (outNode.distanceToSource > checkingDistance) {
      outNode.distanceToSource = (int) checkingDistance;
      predecessorMap.put(outNode, node);
    }
  }

  public static class Graph {
    public Set<Node> nodes;

    public Graph() {
      nodes = new HashSet<>();
    }
  }

  public static class Node implements Comparable<Node> {
    public char label;
    public List<Edge> outgoingEdges;
    public int distanceToSource;

    @Override
    public int compareTo(Node o) {
      return distanceToSource - o.distanceToSource;
    }

    public Node(char label, List<Edge> outgoingEdges) {
      this.label = label;
      this.outgoingEdges = outgoingEdges;
      distanceToSource = 0;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Node)) return false;
      Node node = (Node) o;
      return label == node.label;
    }

    @Override
    public int hashCode() {
      return Objects.hash(label);
    }
  }

  public static class Edge {
    public int weight;
    public Node destination;

    public Edge(int weight, Node destination) {
      this.weight = weight;
      this.destination = destination;
    }
  }
}
