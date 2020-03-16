package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

public class BinaryTreeLevelOrderTraversal {
  static class Solution {
    Queue<TreeNode> sameLevelQueue = new ArrayDeque<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
      /*
       * This algorithm runs in O(n) time complexity, because it looks at each TreeNode once, and only once.
       * This algorithm runs in O(n + k) space complexity, because at the amount of extra space it needs is k to
       * temporarily store each node for the next level before it is being process. Of course, there is an extra O(n)
       * space for storing all the list of list of nodes.
       */
      if (root == null) {
        return new ArrayList<>();
      }
      Queue<TreeNode> queue = new ArrayDeque<>();
      queue.offer(root);
      List<List<Integer>> listOfList = new ArrayList<>();
      while (!queue.isEmpty()) {
        ListQueue listQueue = processOneLevelOfHeight(queue);
        listOfList.add(listQueue.integerList);
        queue = listQueue.treeNodeQueue;
      }
      return listOfList;
    }

    public ListQueue processOneLevelOfHeight(Queue<TreeNode> queue) {
      /*
       * This algorithm is run on O(n) because you go through each item in the queue
       */
      List<Integer> integerList = new ArrayList<>();
      Queue<TreeNode> nextLevelQueue = new ArrayDeque<>();
      while (!queue.isEmpty()) {
        TreeNode treeNode = queue.poll();
        integerList.add(treeNode.val);
        if (treeNode.left != null) {
          nextLevelQueue.offer(treeNode.left);
        }
        if (treeNode.right != null) {
          nextLevelQueue.offer(treeNode.right);
        }
      }
      ListQueue listQueue = new ListQueue();
      listQueue.integerList = integerList;
      listQueue.treeNodeQueue = nextLevelQueue;
      return listQueue;
    }

    public class ListQueue {
      public List<Integer> integerList;
      public Queue<TreeNode> treeNodeQueue;
    }
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    // Building test case
    // [3,9,20,null,null,15,7]
    TreeNode root = new TreeNode(3);
    // 1st layer
    TreeNode leaf1 = new TreeNode(9);
    TreeNode leaf2 = new TreeNode(20);
    root.left = leaf1;
    root.right = leaf2;

    // 2nd layer
    TreeNode leaf3 = new TreeNode(15);
    TreeNode leaf4 = new TreeNode(7);
    leaf2.left = leaf3;
    leaf2.right = leaf4;

    Solution solution = new Solution();
    List<List<Integer>> answerList = solution.levelOrder(root);
    System.out.println(answerList.toString());
  }
}
