package leetcode;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RecoverBinarySearchTree {
  public void recoverTree(TreeNode root) {
    if (root == null) {
      return;
    }
    Set<TreeNode> treeNodeSet = collectAllNode(root);
    fixMisplacedNode(root, root, treeNodeSet, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private void fixMisplacedNode(TreeNode root, TreeNode node, Set<TreeNode> treeNodeSet, int min, int max) {
    if (node.left != null) {
      int leftChildVal = node.left.val;
      if (!withinRange(leftChildVal, min, node.val)) {
        // FOUND!
        if (!swapNode(node, root, treeNodeSet)) {
          swapNode(node.left, root, treeNodeSet);
        }
        return;
      } else {
        fixMisplacedNode(root, node.left, treeNodeSet, min, node.val);
      }
    }
    if (node.right != null) {
      int rightChildVal = node.right.val;
      if (!withinRange(rightChildVal, node.val, max)) {
        // FOUND
        // First, test if it is the parent node is the issue, then test if it is the child node is the issue
        if (!swapNode(node, root, treeNodeSet)) {
          swapNode(node.right, root, treeNodeSet);
        }
        return;
      } else {
        fixMisplacedNode(root, node.right, treeNodeSet, node.val, max);
      }
    }
  }

  private boolean swapNode(TreeNode nodeToSwap, TreeNode root, Set<TreeNode> treeNodeSet) {
    int nodeToSwapValue = nodeToSwap.val;
    for (TreeNode swappingNode : treeNodeSet) {
      int swappingNodeValue = swappingNode.val;
      nodeToSwap.val = swappingNodeValue;
      swappingNode.val = nodeToSwapValue;
      if (isValidBST(root)) {
        return true;
      }
      // Swap back
      nodeToSwap.val = nodeToSwapValue;
      swappingNode.val = swappingNodeValue;
    }
    return false;
  }

  private Set<TreeNode> collectAllNode(TreeNode root) {
    if (root == null) {
      return Collections.emptySet();
    }
    Set<TreeNode> treeNodeSet = new HashSet<>();
    collectSubTreeNodes(root, treeNodeSet);
    return treeNodeSet;
  }

  private void collectSubTreeNodes(TreeNode parent, Set<TreeNode> treeNodeSet) {
    if (parent == null) {
      return;
    }
    treeNodeSet.add(parent);
    if (parent.left != null) {
      collectSubTreeNodes(parent.left, treeNodeSet);
    }
    if (parent.right != null) {
      collectSubTreeNodes(parent.right, treeNodeSet);
    }
  }

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
        return false;
      }
      subTreeIsBst = isBstSubTree(node.right, node.val, max);
    }
    return subTreeIsBst;
  }

  private boolean withinRange(int value, long min, long max) {
    return value > min && value < max;
  }

  /**
   * Definition for a binary tree node.
   */
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

}
