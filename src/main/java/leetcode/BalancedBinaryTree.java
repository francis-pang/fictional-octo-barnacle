package leetcode;

import java.util.HashMap;
import java.util.Map;

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
    private Map<TreeNode, Integer> treeNodeHeightMap;

    public boolean isBalanced(TreeNode root) {
      treeNodeHeightMap = new HashMap<>();
      return isSubTreeBalanced(root); // stub
    }

    private boolean isSubTreeBalanced(TreeNode treeNode) {
      if (treeNode == null) {
        return true;
      }

      int leftSubTreeHeight = 0;
      if (treeNode.left != null) {
        if (!isSubTreeBalanced(treeNode.left)) {
          return false;
        }
        leftSubTreeHeight = treeNodeHeightMap.get(treeNode.left);
      }

      int rightSubTreeHeight = 0;
      if (treeNode.right != null) {
        if (!isSubTreeBalanced(treeNode.right)) {
          return false;
        }
        rightSubTreeHeight = treeNodeHeightMap.get(treeNode.right);
      }

      if (Math.abs(leftSubTreeHeight - rightSubTreeHeight) > 1) {
        return false;
      }

      int height = Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1;
      treeNodeHeightMap.put(treeNode, height);
      return true;
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
