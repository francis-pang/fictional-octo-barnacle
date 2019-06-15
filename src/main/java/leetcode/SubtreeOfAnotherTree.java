package leetcode;

public class SubtreeOfAnotherTree {
  /**
   * Definition for a binary tree node.
   */
  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    TreeNode n4 = new TreeNode(4);
    TreeNode n5 = new TreeNode(5);

    n3.left = n4;
    n3.right = n5;

    n4.left = n1;
    n4.right = n2;

    TreeNode m4 = new TreeNode(4);
    TreeNode m1 = new TreeNode(1);
    TreeNode m2 = new TreeNode(3);

    m4.left = m1;
    m4.right = m2;

    Solution solution = new Solution();
    System.out.println(solution.isSubtree(n3, m4));
  }

  private static class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
      // Use pre-order DFS
      if (isTreesIdentical(s, t)) {
        return true;
      }
      if (s == null) {
        return false;
      } else {
        return isSubtree(s.left, t) || isSubtree(s.right, t);
      }
    }

    private boolean isTreesIdentical(TreeNode firstNode, TreeNode secondNode) {
      if (firstNode == null && secondNode == null) {
        return true;
      }
      // Use pre-order DFS
      if ((firstNode == null && secondNode != null) ||
          (secondNode == null && firstNode != null) ||
          (firstNode.val != secondNode.val)) {
        return false;
      }
      return isTreesIdentical(firstNode.left, secondNode.left) &&
          isTreesIdentical(firstNode.right, secondNode.right);
    }
  }
}
