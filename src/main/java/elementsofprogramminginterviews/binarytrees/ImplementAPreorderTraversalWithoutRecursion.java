package elementsofprogramminginterviews.binarytrees;

import java.util.Stack;

public class ImplementAPreorderTraversalWithoutRecursion {
  public void preorderTraversal(Node root) {
    Stack<Node> nodeStack = new Stack<>();
    Node node = root;
    do {
      while (node != null) {
        System.out.println(node.value + ", ");
        nodeStack.push(node);
        node = node.left;
      }
      node = nodeStack.pop();
      while (node.right == null && !nodeStack.isEmpty()) {
        node = nodeStack.pop();
      }
      if (node.right != null) {
        node = node.right;
      }
    } while (!nodeStack.isEmpty());
  }

  public class Node {
    public int value;
    public Node left;
    public Node right;
  }
}
