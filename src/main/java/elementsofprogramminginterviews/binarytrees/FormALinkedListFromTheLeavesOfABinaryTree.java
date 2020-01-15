public class FormALinkedListFromTheLeavesOfABinaryTree {
  public Node formLinkedListWithLeaves(TreeNode root) {
    Node linkedListRoot = new Node(Integer.MIN_VALUE);
    findNextNode(root, linkedListRoot);
    return linkedListRoot;
  }

  public Node findNextNode(TreeNode node, Node current) {
    if (node.left == null && node.right == null) {
      Node leaf = new Node(node.value);
      current.next = leaf;
      return leaf;
    }
    Node next = null;
    if (node.left != null) {
      next = findNextNode(node.left, current);
    }
    if (node.right != null) {
      if (next != null) {
        next = findNextNode(node.right, next);
      } else {
        next = findNextNode(node.right, current);
      }
    }
    if (next == null) {
      return current;
    } else {
      return next;
    }
  }

  static class Node {
    public int value;
    public Node next;

    public Node(int value) {
      this.value = value;
    }
  }

  static class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;
  }
}
