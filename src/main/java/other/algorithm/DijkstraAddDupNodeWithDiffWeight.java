package other.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class DijkstraAddDupNodeWithDiffWeight {
  public void findShortestPath(Graph graph, Node start) {
    Set<Node> graphNodes = graph.nodes;
    // Assume that node ID stem from 0 to (n - 1)
    Comparator<WeightedNode> comparator = (o1, o2) -> (o1.distance != o2.distance)
        ? Integer.compare(o1.distance, o2.distance)
        : Integer.compare(o1.node.id, o2.node.id);
    int[] weights = new int[graph.nodes.size() + 1];
    Arrays.fill(weights, Integer.MAX_VALUE); // Initial values
    int[] predecessors = findPathByDijkstra(start, new HashSet<>(), new PriorityQueue<>(comparator), weights);
    printShortestPath(predecessors, weights, start.id);
  }

  private int[] findPathByDijkstra(Node start, Set<Integer> processed,
                                  PriorityQueue<WeightedNode> pq, int[] weights) {
    int[] predecessors = new int[weights.length];
    Arrays.fill(predecessors, Integer.MAX_VALUE);
    predecessors[start.id] = -1;
    pq.add(new WeightedNode(start, 0));
    weights[start.id] = 0;
    while (!pq.isEmpty()) {
      WeightedNode weightedNode = pq.poll();
      Node node = weightedNode.node;
      if (processed.contains(weightedNode.node.id)) {
        continue;
      }
      for (Edge edge : node.outgoingEdges) {
        relaxation(weights, node.id, edge, pq, predecessors);
      }
    }
    return predecessors;
  }

  private void relaxation(int[] weights, int source, Edge edge, PriorityQueue<WeightedNode> pq, int[] predecessors) {
    int originalDistance = weights[edge.destination.id];
    int newDistance = weights[source] + edge.weight;
    if (newDistance < originalDistance) {
      predecessors[edge.destination.id] = source;
      weights[edge.destination.id] = newDistance;
      pq.add(new WeightedNode(edge.destination, newDistance));
    }
  }

  private void printShortestPath(int[] predecessors, int[] weights, int startId) {
    StringBuilder stringBuilder = new StringBuilder();
    String startPointString = String.format("Shortest path starting from %s is: \n", startId);
    stringBuilder.append(startPointString);
    Queue<Integer> idQueue = new ArrayDeque<>();
    idQueue.add(startId);
    while(!idQueue.isEmpty()) {
      int origin = idQueue.poll();
      for (int i = 0; i < predecessors.length; i++) {
        if (predecessors[i] == origin) {
          String currentShortestEdge = String.format("%d -> %d: %d distance\n", origin, i, weights[i]);
          stringBuilder.append(currentShortestEdge);
          idQueue.add(i);
        }
      }
    }
    for (int i = 0; i < weights.length; i++) {
      if (weights[i] == Integer.MAX_VALUE) {
        String unreachable = String.format("Node %d is not reachable\n", i);
        stringBuilder.append(unreachable);
      }
    }
    System.out.println(stringBuilder);
  }

  public static class Graph {
    public Set<Node> nodes;

    public Graph() {
      nodes = new HashSet<>();
    }
  }

  public static class WeightedNode {
    public Node node;
    public int distance;

    public WeightedNode(Node node, int distance) {
      this.node = node;
      this.distance = distance;
    }
  }

  public static class Node {
    public int id;
    public List<Edge> outgoingEdges;

    public Node(int id) {
      this.id = id;
      this.outgoingEdges = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Node node)) return false;
      return id == node.id;
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
    }
  }

  public static class Edge {
    public int weight;
    public Node destination;

    public Edge(Node destination, int weight) {
      this.weight = weight;
      this.destination = destination;
    }
  }

  public static void main(String[] args) {
    Graph graph = new Graph();
    Node node1 = new Node(1);
    graph.nodes.add(node1);
    Node node2 = new Node(2);
    graph.nodes.add(node2);
    Node node3 = new Node(3);
    graph.nodes.add(node3);
    Node node4 = new Node(4);
    graph.nodes.add(node4);
    Node node5 = new Node(5);
    graph.nodes.add(node5);
    Node node6 = new Node(6);
    graph.nodes.add(node6);
    Node node7 = new Node(7);
    graph.nodes.add(node7);
    Node node8 = new Node(8);
    graph.nodes.add(node8);
    Node node9 = new Node(9);
    graph.nodes.add(node9);

    node1.outgoingEdges.add(new Edge(node2, 1));
    node1.outgoingEdges.add(new Edge(node4, 22));
    node1.outgoingEdges.add(new Edge(node8, 45));
    node2.outgoingEdges.add(new Edge(node3, 2));
    node2.outgoingEdges.add(new Edge(node5, 22));
    node3.outgoingEdges.add(new Edge(node4, 1));
    node4.outgoingEdges.add(new Edge(node5, 2));
    node4.outgoingEdges.add(new Edge(node1, 2));
    node5.outgoingEdges.add(new Edge(node6, 1));
    node5.outgoingEdges.add(new Edge(node8, 9));
    node6.outgoingEdges.add(new Edge(node7, 2));
    node6.outgoingEdges.add(new Edge(node8, 5));
    node7.outgoingEdges.add(new Edge(node8, 1));
    node9.outgoingEdges.add(new Edge(node2, 5));

    DijkstraAddDupNodeWithDiffWeight disjkstra = new DijkstraAddDupNodeWithDiffWeight();
    disjkstra.findShortestPath(graph, node2);
  }
}
