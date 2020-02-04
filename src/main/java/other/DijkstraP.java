package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringJoiner;

public class DijkstraP {
  public List<Node> findSingleSourceShortestPath(Graph graph, Node source, Node target) {
    // Initialise an array to represent distance from the source to the other nodes, and also the last distance
    // All the initial distance should be infinity
    Map<Node, PathContext> shortestDistanceTable = new HashMap<>();
    for (Node node : graph.nodes) {
      shortestDistanceTable.put(node, new PathContext(Integer.MAX_VALUE));
    }
    PathContext sourceContext = shortestDistanceTable.get(source);
    sourceContext.distance = 0;

    Set<Node> visitedNodes = new HashSet<>();
    visitedNodes.add(target); // You won't process the target node

    // Starting from the source node
    Node node = source;
    // Calculate the shortest distance from the processing node to its neighbours
    do {
      visitedNodes.add(node);
      int shortestDistanceToNode = shortestDistanceTable.get(node).distance;
      for (Map.Entry<Node, Integer> neighbourEntry : node.neighbours.entrySet()) {
        Node neighbour = neighbourEntry.getKey();
        int edgeWeight = neighbourEntry.getValue();
        int currentShortestDistance = shortestDistanceTable.get(neighbour).distance;
        int computedDistanceToNeighbour = shortestDistanceToNode + edgeWeight;
        if (computedDistanceToNeighbour < currentShortestDistance) {
          shortestDistanceTable.put(neighbour, new PathContext(computedDistanceToNeighbour, node));
        }
      }
      // When all done, repeat for next closest unvisited neighbours
      node = null;
      for (Node nextClosestNode : graph.nodes) {
        if (visitedNodes.contains(nextClosestNode)) {
          continue;
        }
        if (node == null) {
          node = nextClosestNode;
        } else if (shortestDistanceTable.get(node).distance > shortestDistanceTable.get(nextClosestNode).distance) {
          node = nextClosestNode;
        }
      }
    } while (node != null && shortestDistanceTable.get(node).distance != Integer.MAX_VALUE); // Stop when all node in
    // the graph (or connected component in this case) is done.

    List<Node> path = constructShortestDistancePath(shortestDistanceTable, target);
    return path;
  }

  private List<Node> constructShortestDistancePath(Map<Node, PathContext> shortestDistanceTable, Node target) {
    // Construct the shortest path
    Stack<Node> reversePath = new Stack<>();
    Node node = target;
    do {
      reversePath.add(node);
      node = shortestDistanceTable.get(node).predecessor;
    } while (node != null);

    ArrayList<Node> path = new ArrayList<>();
    while (!reversePath.isEmpty()) {
      path.add(reversePath.pop());
    }
    return path;
  }

  public List<Node> findSingleSourceShortestPathWithoutGraph(Node source, Node target) {
    Map<Node, PathContext> shortDistanceTable = new HashMap<>();
    shortDistanceTable.put(source, new PathContext(0));

    Set<Node> visitedNodes = new HashSet<>();

    Node node = source;
    do {
      visitedNodes.add(node);
      int distanceToNode = shortDistanceTable.get(node).distance;
      for (Map.Entry<Node, Integer> neighbourEntry : node.neighbours.entrySet()) {
        Node neighbour = neighbourEntry.getKey();
        int edgeWeight = neighbourEntry.getValue();
        int computedDistance = distanceToNode + edgeWeight;
        if (!shortDistanceTable.containsKey(neighbour) ||
            shortDistanceTable.get(neighbour).distance > computedDistance) {
          shortDistanceTable.put(neighbour, new PathContext(computedDistance, node));
        }
      }
      node = null;
      for (Map.Entry<Node, PathContext> shortDistanceEntry : shortDistanceTable.entrySet()) {
        Node entryNode = shortDistanceEntry.getKey();
        if (visitedNodes.contains(entryNode)) {
          continue;
        }
        if (node == null || shortDistanceTable.get(node).distance > shortDistanceEntry.getValue().distance) {
          node = entryNode;
        }
      }
    } while (node != null && node != target && shortDistanceTable.get(node).distance != Integer.MAX_VALUE);

    List<Node> path = constructShortestDistancePath(shortDistanceTable, target);
    return path;
  }

  public static void printPath(List<Node> path) {
    for (Node node : path) {
      System.out.printf("%s,", node.value);
    }
    System.out.println();
  }

  /**
   * Used to store the information of the shortest path
   */
  private class PathContext {
    public int distance;
    public Node predecessor;

    public PathContext(int distance) {
      this.distance = distance;
    }

    public PathContext(int distance, Node predecessor) {
      this.distance = distance;
      this.predecessor = predecessor;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", PathContext.class.getSimpleName() + "[", "]")
          .add("distance=" + distance)
          .add("predecessor=" + predecessor)
          .toString();
    }
  }

  public static class Graph {
    public List<Node> nodes;

    public Graph() {
      nodes = new ArrayList<>();
    }
  }

  public static class Node {
    public char value;
    public Map<Node, Integer> neighbours;

    public Node(char value) {
      this.value = value;
      neighbours = new HashMap<>();
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
          .add("value=" + value)
          .toString();
    }
  }

  public static void main(String[] args) {
    Graph graph = new Graph();
    Node nodeA = new Node('A');
    graph.nodes.add(nodeA);
    Node nodeB = new Node('B');
    graph.nodes.add(nodeB);
    Node nodeC = new Node('C');
    graph.nodes.add(nodeC);
    Node nodeD = new Node('D');
    graph.nodes.add(nodeD);
    Node nodeE = new Node('E');
    graph.nodes.add(nodeE);
    Node nodeF = new Node('F');
    graph.nodes.add(nodeF);
    Node nodeG = new Node('G');
    graph.nodes.add(nodeG);
    Node nodeH = new Node('H');
    graph.nodes.add(nodeH);
    Node nodeI = new Node('I');
    graph.nodes.add(nodeI);

    nodeA.neighbours.put(nodeB, 1);
    nodeA.neighbours.put(nodeD, 22);
    nodeA.neighbours.put(nodeH, 45);
    nodeB.neighbours.put(nodeC, 2);
    nodeB.neighbours.put(nodeE, 22);
    nodeC.neighbours.put(nodeD, 1);
    nodeD.neighbours.put(nodeE, 2);
    nodeD.neighbours.put(nodeA, 2);
    nodeE.neighbours.put(nodeF, 1);
    nodeE.neighbours.put(nodeH, 9);
    nodeF.neighbours.put(nodeG, 2);
    nodeF.neighbours.put(nodeH, 5);
    nodeG.neighbours.put(nodeH, 1);
    nodeI.neighbours.put(nodeB, 5);

    DijkstraP dijkstraP = new DijkstraP();
    List<Node> shortestDistancePath = dijkstraP.findSingleSourceShortestPath(graph, nodeA, nodeH);
    printPath(shortestDistancePath);

    shortestDistancePath = dijkstraP.findSingleSourceShortestPathWithoutGraph(nodeA, nodeH);
    printPath(shortestDistancePath);
  }
}
