package leetcode;

import java.util.HashMap;
import java.util.Map;

public class BinarySearchTreeIterator {
  /**
   * Definition for a binary tree node.
   **/
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  class BSTIterator {
    private TreeNode current;
    private Map<TreeNode, TreeNode> parentMap;

    public BSTIterator(TreeNode root) {
      parentMap = new HashMap<>();
      constructParentMap(root);
    }

    private void constructParentMap(TreeNode node) {
      if (node == null) {
        return;
      }
      current = node;
      if (node.left != null) {
        current = node;
        parentMap.put(node.left, node);
        constructParentMap(node.left);
      }
      if (node.right != null) {
        parentMap.put(node.right, node);
        constructParentMap(node.right);
      }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
      if (current == null) {
        return 0;
      }
      int value = current.val;
      current = getNext(current);
      return value;
    }

    TreeNode getNext(TreeNode node) {
      TreeNode parent = parentMap.get(node);
      while (parent != null) {
        if (parent.val > node.val) { // node is left child
          return parent;
        } else { // node is right child
          parent = parentMap.get(parent);
        }
      }
      // parent == null
      if (node.right != null) {
        return smallest(node.right);
      } else {
        return null;
      }
    }

    TreeNode smallest(TreeNode node) {
      if (node.left != null) {
        return smallest(node.left);
      } else {
        return node;
      }
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
      return current == null;
    }
  }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
