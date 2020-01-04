package elementsofprogramminginterviews.binarytrees;

import java.util.Stack;

public class ImplementAnInorderTraversalWithoutRecursion {
  public void inOrderTraversal(Node root) {
    Stack<Node> nodeStack = new Stack<>();
    nodeStack.push(root);
    Node node = root;
    while (node.left != null) {
      nodeStack.push(node.left);
      node = node.left;
    }
    while (!nodeStack.isEmpty()) {
      node = nodeStack.pop();
      System.out.println(node.value + ", ");
      if (node.right != null) {
        node = node.right;
        nodeStack.push(node);
      }
      while (node.left != null) {
        nodeStack.push(node.left);
        node = node.left;
      }
    }
  }

  public class Node {
    public int value;
    public Node left;
    public Node right;
  }
}
