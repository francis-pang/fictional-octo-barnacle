package elementsofprogramminginterviews.graphs;

import other.DijkstraP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static other.DijkstraP.Graph;
import static other.DijkstraP.Node;

public class ComputeAShortestPathWithFewestEdges {
  private static final int INFINITY = Integer.MAX_VALUE;

  public List<Node> shortestPathWithLeastNode(Graph graph, Node source, Node target) {
    Map<Node, PathContext> distanceTable = new HashMap<>();
    for (Node node : graph.nodes) {
      distanceTable.put(node, new PathContext(INFINITY, 0));
    }
    distanceTable.put(source, new PathContext(0, 0));
    Set<Node> visitedNodes = new HashSet<>();

    Node node = source;
    do {
      visitedNodes.add(node);
      PathContext pathContext = distanceTable.get(node);
      int lengthToNode = pathContext.length;
      int distanceToNode = pathContext.distance;
      for (Map.Entry<Node, Integer> neighbourEntry : node.neighbours.entrySet()) {
        Node neighbour = neighbourEntry.getKey();
        int edgeWeight = neighbourEntry.getValue();
        int computedDistance = distanceToNode + edgeWeight;
        int computedLength = lengthToNode + 1;

        PathContext currentPathContext = distanceTable.get(neighbour);
        if (currentPathContext.distance > computedDistance) {
          distanceTable.put(neighbour, new PathContext(node, computedDistance, computedLength));
        } else if (currentPathContext.distance == computedDistance &&
            currentPathContext.length > computedLength) {
          distanceTable.put(neighbour, new PathContext(node, computedDistance, computedLength));
        }
      }
      node = null;
      for (Map.Entry<Node, PathContext> distanceEntry : distanceTable.entrySet()) {
        Node entryNode = distanceEntry.getKey();
        if (visitedNodes.contains(entryNode)) {
          continue;
        }
        int entryDistance = distanceEntry.getValue().distance;
        if (entryDistance == INFINITY) {
          continue;
        }
        if (node == null || distanceTable.get(node).distance > entryDistance) {
          node = entryNode;
        }
      }
    } while (node != null && node != target);

    List<Node> path = computeShortestPath(distanceTable, target);
    return path;
  }

  private List<Node> computeShortestPath(Map<Node, PathContext> distanceTable, Node target) {
    ArrayList<Node> path = new ArrayList<>();
    Node node = target;
    System.out.println("Minimum cost=" + distanceTable.get(target).distance);
    while (node != null) {
      path.add(node);
      node = distanceTable.get(node).predecessor;
    }
    return path;
  }

  public class PathContext {
    public Node predecessor;
    public int distance;
    public int length;

    public PathContext(int distance, int length) {
      this.distance = distance;
      this.length = length;
    }

    public PathContext(Node predecessor, int distance, int length) {
      this.predecessor = predecessor;
      this.distance = distance;
      this.length = length;
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
    nodeB.neighbours.put(nodeI, 3);
    nodeC.neighbours.put(nodeD, 1);
    nodeD.neighbours.put(nodeE, 2);
    nodeD.neighbours.put(nodeA, 2);
    nodeE.neighbours.put(nodeF, 1);
    nodeE.neighbours.put(nodeH, 9);
    nodeF.neighbours.put(nodeG, 2);
    nodeF.neighbours.put(nodeH, 5);
    nodeG.neighbours.put(nodeH, 1);
    nodeI.neighbours.put(nodeB, 1);
    nodeI.neighbours.put(nodeH, 6);

    ComputeAShortestPathWithFewestEdges computeAShortestPathWithFewestEdges = new ComputeAShortestPathWithFewestEdges();
    List<Node> shortestDistancePath = computeAShortestPathWithFewestEdges.shortestPathWithLeastNode(graph,
        nodeA, nodeH);
    DijkstraP.printPath(shortestDistancePath);
  }
}
