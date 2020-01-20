package elementsofprogramminginterviews.binarytrees;

public class ComputeTheRightSiblingTree {
  public void addNextSibling(Node root) {
      ArrayList<Node> siblings = new ArrayList<>();
      addRightAdjacentNode(root, 1, siblings);
  }

  public void addRightAdjacentNode(Node node, int depth, ArrayList<Node> siblings) {
    if (node == null) {
      return;
    }
    if (siblings.size() > depth) {
      node.levelNext = siblings.get(depth - 1);
    }
    siblings.set(depth - 1, node);
    addRightAdjacentNode(node.right, depth + 1, siblings);
    addRightAdjacentNode(node.left, depth + 1, siblings);
  }

  public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node levelNext;
  }
}