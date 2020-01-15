public class ComputeTheExteriorOfABinaryTree {
	  public void printExterior(Node root) {
    printLeftSide(root, true);
    printChildNode(root, true);
    printRightSide(root, true);
    System.out.println();
  }

  private void printRightSide(Node node, boolean root) {
    if (node.right != null && node.right.right != null) {
      printRightSide(node.right, false);
    }
    if (!root) {
      printNode(node);
    }
  }

  private void printChildNode(Node node, boolean root) {
    if (node.left == null && node.right == null) {
      if (!root) {
        printNode(node);
      }
      return;
    }
    if (node.left != null) {
      printChildNode(node.left, false);
    }
    if (node.right != null) {
      printChildNode(node.right, false);
    }
  }

  private void printLeftSide(Node node, boolean root) {
    if (root) {
     printNode(node);
    }
    if (node.left == null) {
      return;
    }
    if (!root) {
      printNode(node);
    }
    printLeftSide(node.left, false);
  }

  private void printNode(Node node) {
    System.out.printf(node.val +  ", ");
  }

  static class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
      this.val = val;
    }
  }

  public static void main(String[] args) {
    // Single node case
    Node root = new Node(13);
    ComputeTheExteriorOfABinaryTree computeTheExteriorOfABinaryTree = new ComputeTheExteriorOfABinaryTree();
    computeTheExteriorOfABinaryTree.printExterior(root);

    // left skewed tree
    Node node3 = new Node(3);
    root.left = node3;
    Node node1 = new Node(1);
    node3.left = node1;
    computeTheExteriorOfABinaryTree.printExterior(root);

    // Full tree
    Node node2 = new Node(2);
    node1.right = node2;
    Node node9 = new Node(9);
    node3.right = node9;
    Node node6 = new Node(6);
    node9.left = node6;
    Node node16 = new Node(16);
    root.right = node16;
    computeTheExteriorOfABinaryTree.printExterior(root);
  }
}