package hackerearth;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * https://www.hackerearth.com/practice/algorithms/graphs/shortest-path-algorithms/tutorial/
 */
public class ShortestPathProblem {
  private static int UNREACHABLE = (int) Math.pow(10, 9);

  public static void main(String[] args) {
    String input = "5 5\n" +
        "1 2 5\n" +
        "1 3 2\n" +
        "3 4 1\n" +
        "1 4 6\n" +
        "3 5 5";

    int[] actualShortestPath = calculateShortestPath(input);

    String output = "5 2 3 7";
    int[] expectedShortestPath = parseOutput(output);
    IntStream.range(0, expectedShortestPath.length)
        .forEach(i -> {
          assert actualShortestPath[i + 2] == expectedShortestPath[i];
        });
  }

  private static int[] parseOutput(String output) {
    String[] splitOutput = output.split("\\s");
    int[] expectedShortestPath = new int[splitOutput.length];
    IntStream
        .range(0, splitOutput.length)
        .forEach(i -> expectedShortestPath[i] = Integer.parseInt(splitOutput[i]));
    return expectedShortestPath;
  }

  public static int[] calculateShortestPath(String input) {
    Graph graph = parseInput(input);
    Instant startTime = Instant.now();
    int[] topologicalSortShortestPath = shortestPathTopologicalSort(graph, 1);
    Instant endTime = Instant.now();

    Duration timeTaken = Duration.between(startTime, endTime);
    System.out.printf("Time taken for topological sort: %d ns\n", timeTaken.getNano());
    printArray(topologicalSortShortestPath);

    startTime = Instant.now();
    int[] dijkstraShortestPath = shortestPathDijkstra(graph, 1);
    endTime = Instant.now();
    timeTaken = Duration.between(startTime, endTime);
    System.out.printf("Time taken for dijkstra        : %d ns\n", timeTaken.getNano());
    printArray(dijkstraShortestPath);

    return dijkstraShortestPath;
  }

  private static void printArray(int[] paths) {
    System.out.printf("Shortest path: ");
    for (int shortestPath : paths) {
      System.out.printf("%d,", shortestPath);
    }
    System.out.println();
  }

  private static int[] shortestPathTopologicalSort(Graph graph, int startIndex) {
    List<Node> sortedNode = topologicalSortGraph(graph, startIndex);

    // Initialise graph
    int[] shortestDistances = new int[graph.nodes.size()];
    IntStream.range(0, graph.nodes.size())
        .forEach(i -> shortestDistances[i] = (i == startIndex) ? 0 : UNREACHABLE);

    // Initialise predecessor table
    Map<Node, Node> predecessorTable = new HashMap<>();
    graph.nodes.forEach(node -> {
      if (node.label != startIndex) {
        predecessorTable.put(node, null);
      }
    });

    // Traverse the sorted node
    sortedNode.forEach(node -> node.outgoingEdges.forEach(edge -> relaxEdge(edge, node, shortestDistances, predecessorTable)));

    return shortestDistances;
  }

  private static void relaxEdge(Edge outgoingEdge,
                                Node node,
                                int[] shortestDistances,
                                Map<Node, Node> predecessorTable) {
    int edgeWeight = outgoingEdge.weight;
    Node neighbourNode = outgoingEdge.destination;
    int neighbourNodeIndex = neighbourNode.label;
    int nodeIndex = node.label;

    if (shortestDistances[neighbourNodeIndex] > shortestDistances[nodeIndex] + edgeWeight) {
      shortestDistances[neighbourNodeIndex] = shortestDistances[nodeIndex] + edgeWeight;
      predecessorTable.put(neighbourNode, node);
    }
  }

  private static List<Node> topologicalSortGraph(Graph graph, int startIndex) {
    Set<Node> visitedNodes = new HashSet<>();
    Stack<Node> nodeStack = new Stack<>();

    Node startNode = findNodeInGraph(graph, startIndex);

    // Perform a in-order DFS
    traverseGraph(startNode, nodeStack, visitedNodes);
    List<Node> sortedNodes = gatherNodesFromStack(nodeStack);
    return sortedNodes;
  }

  private static List<Node> gatherNodesFromStack(Stack<Node> nodeStack) {
    List<Node> sortedNodes = new ArrayList<>();
    while (!nodeStack.isEmpty()) {
      sortedNodes.add(nodeStack.pop());
    }
    return sortedNodes;
  }

  private static void traverseGraph(Node node, Stack<Node> nodeStack, Set<Node> visitedNodes) {
    for (Edge outgoingEdge : node.outgoingEdges) {
      Node neighbour = outgoingEdge.destination;
      if (!visitedNodes.contains(neighbour)) {
        visitedNodes.add(neighbour);
        traverseGraph(neighbour, nodeStack, visitedNodes);
      }
    }
    nodeStack.push(node);
  }

  private static Node findNodeInGraph(Graph graph, int index) {
    for (Node node : graph.nodes) {
      if (node.label == index) {
        return node;
      }
    }
    return null; // Will not happen
  }

  private static int[] shortestPathDijkstra(Graph graph, int startIndex) {
    int[] shortestDistanceByIndex = new int[graph.nodes.size()];
    PriorityQueue<NodeWithDistance> queue = initialiseQueue(graph, startIndex, shortestDistanceByIndex);

    Map<Node, Node> predecessorTable = initialisePredecessorTable(graph);

    while (!queue.isEmpty()) {
      NodeWithDistance nodeWithDistance = queue.poll();
      Node node = nodeWithDistance.node;
      node.outgoingEdges.forEach(edge -> relaxEdge(queue, predecessorTable, edge, nodeWithDistance, shortestDistanceByIndex));
    }
    return shortestDistanceByIndex;
  }

  private static void relaxEdge(PriorityQueue<NodeWithDistance> queue,
                                Map<Node, Node> predecessorTable,
                                Edge edge,
                                NodeWithDistance nodeWithDistance,
                                int[] shortestDistanceByIndex) {
    int nodeShortDistanceWithWeight = nodeWithDistance.distance + edge.weight;
    Node neighbourNode = edge.destination;
    int shortestDistanceFromSourceToNeighbourNode = shortestDistanceByIndex[neighbourNode.label];
    if (nodeShortDistanceWithWeight < shortestDistanceFromSourceToNeighbourNode) {
      shortestDistanceByIndex[neighbourNode.label] = nodeShortDistanceWithWeight;
      NodeWithDistance neighbourNodeWithDistance = getNodeWithDistance(queue, neighbourNode);
      if (neighbourNodeWithDistance != null) {
        neighbourNodeWithDistance.distance = nodeShortDistanceWithWeight;
      }
      predecessorTable.put(neighbourNode, nodeWithDistance.node);
    }
  }

  private static NodeWithDistance getNodeWithDistance(PriorityQueue<NodeWithDistance> queue, Node neighbourNode) {
    Iterator<NodeWithDistance> nodeWithDistanceIterable = queue.iterator();
    while (nodeWithDistanceIterable.hasNext()) {
      NodeWithDistance nodeWithDistance = nodeWithDistanceIterable.next();
      Node node = nodeWithDistance.node;
      if (node.equals(neighbourNode)) {
        return nodeWithDistance;
      }
    }
    return null;
  }

  private static PriorityQueue<NodeWithDistance> initialiseQueue(Graph graph,
                                                                 int startIndex,
                                                                 int[] shortDistanceByIndex) {
    PriorityQueue<NodeWithDistance> queue = new PriorityQueue<>();
    // Initialisation queue
    graph.nodes.forEach(node -> {
      int startDistance = (node.label == startIndex) ? 0 : UNREACHABLE;
      NodeWithDistance nodeWithDistance = new NodeWithDistance(startDistance, node);
      queue.add(nodeWithDistance);
      shortDistanceByIndex[node.label] = startDistance;
    });
    return queue;
  }

  private static Map<Node, Node> initialisePredecessorTable(Graph graph) {
    Map<Node, Node> predecessorTable = new HashMap<>();
    graph.nodes.forEach(node -> predecessorTable.put(node, null));
    return predecessorTable;
  }

  private static Graph parseInput(String input) {
    Scanner scanner = new Scanner(input);
    int vertexCount = scanner.nextInt();
    int edgeCount = scanner.nextInt();

    Graph graph = initialiseGraph(vertexCount + 1);
    IntStream.range(0, edgeCount)
        .forEach($ -> {
          int fromNodeIndex = scanner.nextInt();
          int toNodeIndex = scanner.nextInt();
          int weight = scanner.nextInt();

          Node fromNode = graph.nodes.get(fromNodeIndex);
          Node toNode = graph.nodes.get(toNodeIndex);
          fromNode.outgoingEdges.add(new Edge(weight, toNode));
        });

    return graph;
  }

  private static Graph initialiseGraph(int count) {
    Graph graph = new Graph();
    IntStream.range(0, count)
        .forEach(i -> graph.nodes.add(new Node(i)));
    return graph;
  }

  static class NodeWithDistance implements Comparable<NodeWithDistance> {
    public int distance;
    public Node node;

    public NodeWithDistance(int distance, Node node) {
      this.distance = distance;
      this.node = node;
    }

    @Override
    public int compareTo(NodeWithDistance o) {
      return distance - o.distance;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof NodeWithDistance)) return false;
      NodeWithDistance that = (NodeWithDistance) o;
      return node.equals(that.node);
    }

    public boolean equals(Node node) {
      return this.node.equals(node);
    }

    @Override
    public int hashCode() {
      return Objects.hash(node);
    }
  }

  static class Graph {
    public List<Node> nodes;

    public Graph() {
      nodes = new ArrayList<>();
    }
  }

  static class Node {
    public int label;
    public List<Edge> outgoingEdges;

    public Node(int label) {
      this.label = label;
      this.outgoingEdges = new ArrayList<>();
    }
  }

  static class Edge {
    public int weight;
    public Node destination;

    public Edge(int weight, Node destination) {
      this.weight = weight;
      this.destination = destination;
    }
  }
}
