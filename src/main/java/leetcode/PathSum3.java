package leetcode;

import java.util.HashMap;
import java.util.Map;

public class PathSum3 {
  public static void main(String[] args) {
    TreeNode n1 = new TreeNode(0);
    TreeNode n2 = new TreeNode(1);
    TreeNode n3 = new TreeNode(1);
    TreeNode n4 = new TreeNode(3);
    TreeNode n5 = new TreeNode(2);
    TreeNode n6 = new TreeNode(11);
    TreeNode n7 = new TreeNode(3);
    TreeNode n8 = new TreeNode(-2);
    TreeNode n9 = new TreeNode(1);

    n1.left = n2;
    n1.right = n3;

    Solution solution = new Solution();
    System.out.println(solution.pathSum(n1, 1));
  }

  /**
   * Definition for a binary tree node.
   */
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  static class Solution {
    public int pathSum(TreeNode root, int sum) {
      Map<Integer, Integer> sumMap = new HashMap<>();
      return numberOfPathSumToNumber(root, sumMap, sum, root.val);
    }

    private int numberOfPathSumToNumber(TreeNode node, Map<Integer, Integer> sumMap, int number, int runningSum) {
      if (node == null) {
        return 0;
      }
      sumMap.compute(runningSum + node.val, (key, value) -> (value == null) ? 1 : (value + 1));
      int difference = runningSum + node.val - number;
      return sumMap.getOrDefault(difference, 0) + numberOfPathSumToNumber(node.left, sumMap, number,
          runningSum + node.val) + numberOfPathSumToNumber(node.right, sumMap, number, runningSum + node.val);
    }
  }
}
