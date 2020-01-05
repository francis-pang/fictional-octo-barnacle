package elementsofprogramminginterviews.binarytrees;

public class ComputeTheKthNodeInAnInorderTraversal {
  public int findKthNode(Node root, int k) {
    return findKthNode(root, 0, k);
  }

  private int findKthNode(Node node, int offset, int k) {
    int leftSize = 0;
    if (node.left != null) {
      leftSize = node.left.size;
    }
    if (leftSize + 1 + offset == k) {
      return node.value;
    } else if (leftSize + 1 + offset < k) {
      return findKthNode(node.right, leftSize + 1 + offset, k);
    } else { // less than k
      return findKthNode(node.left, offset, k);
    }
  }

  public static class Node {
    public int value;
    public int size;
    public Node left;
    public Node right;

    public Node(int value) {
      this.value = value;
    }
  }

  public static void main(String[] args) {
    Node nodeA = new Node(1);
    Node nodeB = new Node(2);
    Node nodeC = new Node(3);
    Node nodeD = new Node(4);
    Node nodeE = new Node(5);
    Node nodeF = new Node(6);
    Node nodeG = new Node(7);

    nodeA.left = nodeB;
    nodeA.right = nodeC;
    nodeB.left = nodeD;
    nodeB.right = nodeE;
    nodeE.left = nodeF;
    nodeE.right = nodeG;

    nodeA.size = 7;
    nodeB.size = 5;
    nodeC.size = 1;
    nodeD.size = 1;
    nodeE.size = 3;
    nodeF.size = 1;
    nodeG.size = 1;

    ComputeTheKthNodeInAnInorderTraversal computeTheKthNodeInAnInorderTraversal =
        new ComputeTheKthNodeInAnInorderTraversal();
    System.out.println(computeTheKthNodeInAnInorderTraversal.findKthNode(nodeA, 3));
  }
}