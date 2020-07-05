package leetcode;

public class ValidateBinarySearchTree {
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isBstSubTree(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean isBstSubTree(TreeNode node, long min, long max) {
    boolean subTreeIsBst = true;
    if (node.left != null) {
      int leftChildVal = node.left.val;
      if (!withinRange(leftChildVal, min, node.val)) {
        System.out.println("Not a BST. Detected at node " + node.val + ", left child node " + node.left.val);
        return false;
      }
      subTreeIsBst = isBstSubTree(node.left, min, node.val);
    }
    if (!subTreeIsBst) {
      return false;
    }
    if (node.right != null) {
      int rightChildVal = node.right.val;
      if (!withinRange(rightChildVal, node.val, max)) {
        System.out.println("Not a BST. Detected at node " + node.val + ", right child node " + node.right.val);
        return false;
      }
      subTreeIsBst = isBstSubTree(node.right, node.val, max);
    }
    return subTreeIsBst;
  }

  private boolean withinRange(int value, long min, long max) {
    return value > min && value < max;
  }

  public static void main(String[] args) {
    // Layer 1
    TreeNode node6 = new TreeNode(6);

    //Layer 2
    TreeNode node2 = new TreeNode(2);
    node6.left = node2;
    TreeNode node10 = new TreeNode(10);
    node6.right = node10;

    // Layer 3
    TreeNode nodeN5 = new TreeNode(-5);
    node2.left = nodeN5;
    TreeNode node5 = new TreeNode(5);
    node2.right = node5;
    TreeNode node8 = new TreeNode(8);
    node10.left = node8;
    TreeNode node11 = new TreeNode(11);
    node10.right = node11;

    // Layer 4
    TreeNode nodeN17 = new TreeNode(-17);
    nodeN5.left = nodeN17;
    TreeNode nodeN3 = new TreeNode(-3);
    nodeN5.right = nodeN3;
    TreeNode node15 = new TreeNode(15);
    node11.right = node15;

    ValidateBinarySearchTree validateBinarySearchTree = new ValidateBinarySearchTree();
    System.out.println(validateBinarySearchTree.isValidBST(node6));
  }
}