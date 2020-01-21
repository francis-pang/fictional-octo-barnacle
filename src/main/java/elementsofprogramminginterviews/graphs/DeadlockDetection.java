package elementsofprogramminginterviews.graphs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeadlockDetection {
  public boolean containsCycle(Node node) {
    Set<Node> visited = new HashSet<>();
    return containsCycle(node, visited);
  }

  private boolean containsCycle(Node node, Set<Node> visited) {
    if (!visited.add(node)) {
      return true;
    }
    for(Node neighbour : node.neighbours) {
      if (containsCycle(neighbour, visited)) {
        return true;
      }
    }
    visited.remove(node);
    return false;
  }

  public class Node {
    public List<Node> neighbours;
  }
}