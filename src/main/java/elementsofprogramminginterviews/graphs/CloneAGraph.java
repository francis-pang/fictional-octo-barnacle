package elementsofprogramminginterviews.graphs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CloneAGraph {
  public Node cloneGraph(Node start) {
    Set<Node> visited = new HashSet<>();
    Queue<Node> queue = new ArrayDeque<>();
    Map<Node, Node> cloneTable = new HashMap<>();
    queue.add(start);
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      if (!visited.add(node)) {
        continue;
      }
      Node clonedNode = cloneTable.compute(node, (original, clone) -> (clone == null) ? new Node(node.value) : clone);
      for (Node neighbour : node.neighbours) {
        Node cloneNeighbour = cloneTable.compute(neighbour, (original, clone) -> (clone == null) ? new Node(neighbour.value) : clone);
        clonedNode.neighbours.add(cloneNeighbour);
      }
    }
    return cloneTable.get(start);
  }

  public class Node {
    public int value;
    public List<Node> neighbours;

    public Node(int value) {
      this.value = value;
    }
  }
}