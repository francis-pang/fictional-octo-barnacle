package leetcode;

public class BalancedBinaryTree {
  /**
   * Definition for a binary tree node.
   * public class TreeNode {
   * int val;
   * TreeNode left;
   * TreeNode right;
   * TreeNode(int x) { val = x; }
   * }
   */
  class Solution {
    public boolean isBalanced(TreeNode root) {
      return (calculateHeight(root) != -2);
    }

    public int calculateHeight(TreeNode node) {
      if (node == null) {
        return -1;
      }

      int leftSubTreeHeight = calculateHeight(node.left);
      if (leftSubTreeHeight == -2) {
        return -2;
      }

      int rightSubTreeHeight = calculateHeight(node.right);
      if (rightSubTreeHeight == -2) {
        return -2;
      }

      if (Math.abs(leftSubTreeHeight - rightSubTreeHeight) > 1) {
        return -2;
      }

      return Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1;
    }

    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;

      TreeNode(int x) {
        val = x;
      }
    }
  }
}
