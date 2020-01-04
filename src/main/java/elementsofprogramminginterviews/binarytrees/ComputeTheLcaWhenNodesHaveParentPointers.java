package elementsofprogramminginterviews.binarytrees;

import java.util.HashSet;
import java.util.Set;

public class ComputeTheLcaWhenNodesHaveParentPointers {
  public Node lowestCommonAncester(Node root, Node nodeA, Node nodeB) {
    Set<Node> nodeAParentRoute = new HashSet<>();
    Node parent = nodeA.parent;
    while (parent != null) {
      if (parent.equals(nodeB)) {
        return nodeB;
      }
      nodeAParentRoute.add(parent);
      parent = parent.parent;
    }
    parent = nodeB.parent;
    while (parent != null) {
      if (nodeAParentRoute.contains(parent)) {
        return parent;
      }
      parent = parent.parent;
    }
    return null;
  }

  public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
  }
}
