package crackingthecodinginterview.treesandgraphs;

public class ValidateBst {
  public boolean isTreeBinarySearchTree(BinaryTreeNode root) {
    if (root == null) {
      return false;
    }
    return isSubtreeBinarySearchTree(root.left, Direction.LEFT, root.val) && isSubtreeBinarySearchTree(root.right,
        Direction.RIGHT, root.val);
  }

  public enum Direction {
    LEFT, RIGHT
  }

  public boolean isSubtreeBinarySearchTree(BinaryTreeNode node, Direction direction, int parentNodeValue) {
    if (node == null) {
      return true;
    }

    // Use DFS to traversal the node
    boolean isLeftChildSmaller = true;
    if (node.left != null) {
      // Post-order processing
      if (!isSubtreeBinarySearchTree(node.left, direction, parentNodeValue)) {
        return false;
      }
      if (direction.equals(Direction.LEFT)) {
        isLeftChildSmaller = node.left.val <= node.val;
      } else {
        isLeftChildSmaller = node.left.val <= node.val && node.left.val >= parentNodeValue;
      }
    }

    boolean isRightChildBigger = true;
    if (node.right != null) {
      if (!isSubtreeBinarySearchTree(node.right, direction, parentNodeValue)) {
        return false;
      }
      if (direction.equals(Direction.LEFT)) {
        isRightChildBigger = node.right.val >= node.val && node.right.val <= parentNodeValue;
      } else {
        isRightChildBigger = node.right.val >= node.val;
      }
    }

    return isLeftChildSmaller && isRightChildBigger;
  }

  static class BinaryTreeNode {
    int val;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode(int x) {
      val = x;
    }
  }
}
