package leetcode;

import java.util.*;

public class BinarySearchTreeIterator {
  /**
   * Definition for a binary tree node.
   **/
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  static class BSTIterator {
    private Stack<TreeNode> treeStack;

    public BSTIterator(TreeNode root) {
      treeStack = new Stack<>();
      if (root != null) {
        pushLeftNodesToStack(root);
      }
    }

    private void pushLeftNodesToStack(TreeNode node) {
      treeStack.add(node);
      if (node.left != null) {
        pushLeftNodesToStack(node.left);
      }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
      if (treeStack.isEmpty()) {
        return 0;
      }
      TreeNode poppedNode = treeStack.pop();
      if (poppedNode.right != null) {
        pushLeftNodesToStack(poppedNode.right);
      }
      return poppedNode.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
      return !treeStack.isEmpty();
    }
  }

  public static void main(String[] args) {
    TreeNode node7 = new TreeNode(7);
    TreeNode node3 = new TreeNode(3);
    TreeNode node15 = new TreeNode(15);
    node7.left = node3;
    node7.right = node15;

    BSTIterator bstIterator = new BSTIterator(node7);
    System.out.println("head has next: " + bstIterator.hasNext());
    System.out.println("1st node: " + bstIterator.next());
    System.out.println("1st node has next: " + bstIterator.hasNext());
    System.out.println("2nd node: " + bstIterator.next());
    System.out.println("2nd node has next: " + bstIterator.next());
    System.out.println("3rd node: " + bstIterator.next());
    System.out.println("3rd node has next: " + bstIterator.hasNext());
  }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
