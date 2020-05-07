package elementsofprogramminginterviews.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MakingWiredConnections {
  public boolean canSplitIntoHalf(int[] pins, int[][] wires) {
    Map<Integer, Node> nodeTable = new HashMap<>();
    Set<Node> unprocessedNodes = new HashSet<>();
    for (int[] wire : wires) {
      int source = wire[0];
      int dest = wire[0];
      Node sourceNode = nodeTable.compute(source, (key, value) -> (value == null) ? new Node(key) : value);
      Node destNode = nodeTable.compute(dest, (key, value) -> (value == null) ? new Node(key) : value);
      sourceNode.neighbours.add(destNode);
      destNode.neighbours.add(sourceNode);
      unprocessedNodes.add(sourceNode);
    }

    while (!unprocessedNodes.isEmpty()) {
      Set<Node> leftHalf = new HashSet<>();
      Set<Node> rightHalf = new HashSet<>();
      Iterator<Node> setIterator = unprocessedNodes.iterator();
      Node start = setIterator.next();
      leftHalf.add(start);
      setIterator.remove();
      checkForSplit(unprocessedNodes, start, leftHalf, rightHalf);
    }
    return true;
  }

  private boolean checkForSplit(Set<Node> unprocessedNodes, Node node, Set<Node> leftHalf, Set<Node> rightHalf) {
    if (!unprocessedNodes.contains(node)) {
      return true;
    }
    if (leftHalf.contains(node)) {
      for (Node neighbour : node.neighbours) {// Check that they are all not in left half.
        if (!leftHalf.contains(neighbour)) {
          rightHalf.add(neighbour);
          unprocessedNodes.remove(neighbour);
          if (!checkForSplit(unprocessedNodes, neighbour, leftHalf, rightHalf)) {
            return false;
          }
        } else {
          return false;
        }
      }
    } else { // In right half
      for (Node neighbour : node.neighbours) {
        if (!rightHalf.contains(neighbour)) {
          leftHalf.add(neighbour);
          unprocessedNodes.remove(neighbour);
          if (!checkForSplit(unprocessedNodes, neighbour, leftHalf, rightHalf)) {
            return false;
          }
        } else {
          return false;
        }
      }
    }
    return true;
  }

  public class Node {
    public int value;
    public Set<Node> neighbours;

    public Node(int value) {
      this.value = value;
    }
  }
}
