package other;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BidirectionalBfs {
  private static final int UNREACHABLE = -1;

  public int distanceBetweenTwoNode(Graph graph, Node source, Node target) {
    return calculateShortestDistanceBetweenNodesBfs(graph, source, target);
  }

  private int calculateShortestDistanceBetweenNodesBfs(Graph graph, Node source, Node target) {
    boolean endOfSearch = false;
    boolean found = false;

    // Initialise normal flow
    Queue<Node> currentNormalFlowNodes;
    Queue<Node> nextDistanceNormalFlowNodes = new ArrayDeque<>();
    nextDistanceNormalFlowNodes.add(source);
    int normalFlowDistance = 0;
    Set<Node> normalFlowVisitedNodes = new HashSet<>();
    Set<Node> normalFlowSeenNodes = new HashSet<>();

    // Initialise reverse flow
    Queue<Node> currentReverseFlowNodes;
    Queue<Node> nextDistanceReverseFlowNodes = new ArrayDeque<>();
    nextDistanceReverseFlowNodes.add(target);
    int reverseFlowDistance = 0;
    Set<Node> reverseFlowVisitedNodes = new HashSet<>();
    Set<Node> reverseFlowSeenNodes = new HashSet<>();

    while (!endOfSearch) {
      if (!nextDistanceNormalFlowNodes.isEmpty()) {
        normalFlowDistance++;
        currentNormalFlowNodes = nextDistanceNormalFlowNodes;
        nextDistanceNormalFlowNodes = new ArrayDeque<>();
        while (!currentNormalFlowNodes.isEmpty()) {
          Node node = currentNormalFlowNodes.poll();
          normalFlowVisitedNodes.add(node);
          normalFlowSeenNodes.add(node);
          for (Node outNode : node.outgoingEdges) {
            if (normalFlowVisitedNodes.contains(outNode)) {
              continue;
            }
            if (reverseFlowSeenNodes.contains(outNode)) {
              endOfSearch = true;
              found = true;
              break;
            }
            nextDistanceNormalFlowNodes.add(outNode);
            normalFlowSeenNodes.add(outNode);
          }
        }
      } else {
        break;
      }

      if (!nextDistanceReverseFlowNodes.isEmpty()) {
        reverseFlowDistance++;
        currentReverseFlowNodes = nextDistanceReverseFlowNodes;
        nextDistanceReverseFlowNodes = new ArrayDeque<>();
        while (!currentReverseFlowNodes.isEmpty()) {
          Node node = currentReverseFlowNodes.poll();
          reverseFlowVisitedNodes.add(node);
          reverseFlowSeenNodes.add(node);
          for (Node outNode : node.outgoingEdges) {
            if (reverseFlowVisitedNodes.contains(outNode)) {
              continue;
            }
            if (normalFlowSeenNodes.contains(outNode)) {
              endOfSearch = true;
              found = true;
              break;
            }
            nextDistanceReverseFlowNodes.add(outNode);
            reverseFlowSeenNodes.add(outNode);
          }
        }
      } else {
        break;
      }
    }

    if (found) {
      int totalDistance = normalFlowDistance + reverseFlowDistance;
      return totalDistance;
    } else {
      return UNREACHABLE;
    }
  }

  static class Graph {
    public Set<Node> nodes;

    public Graph() {
      nodes = new HashSet<>();
    }
  }

  static class Node {
    public int label;
    public List<Node> outgoingEdges;

    public Node(int label, List<Node> outgoingEdges) {
      this.label = label;
      this.outgoingEdges = outgoingEdges;
    }
  }
}