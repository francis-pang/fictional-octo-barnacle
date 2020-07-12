package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BinaryTreeZigzagLevelOrderTraversal {
  public static void main(String[] args) {
    BinaryTreeZigzagLevelOrderTraversal binaryTreeZigzagLevelOrderTraversal = new BinaryTreeZigzagLevelOrderTraversal();
//    TreeNode root = TreeNodeCreator.createTreeNode("[1,2,3,4,null,null,5]");
    TreeNode root = TreeNodeCreator.createTreeNode("[6,11,7,14,13,12,8]");
    List<List<Integer>> result = binaryTreeZigzagLevelOrderTraversal.zigzagLevelOrder(root);
    System.out.println(result);
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return Collections.EMPTY_LIST;
    }
    Stack<TreeNode> nextLevelQueue = new Stack<>();
    nextLevelQueue.add(root);
    boolean normalFlow = false;
    List<List<Integer>> zigZagNodeValues = new ArrayList<>();
    zigZagNodeValues.add(List.of(root.val));
    while (!nextLevelQueue.isEmpty()) {
      Stack<TreeNode> queue = nextLevelQueue;
      nextLevelQueue = new Stack<>();
      normalFlow = toggleSwitch(normalFlow);
      List<Integer> sameLevelNodes = new ArrayList<>();
      while (!queue.isEmpty()) {
        TreeNode node = queue.pop();
        if (normalFlow) {
          if (node.right != null) {
            sameLevelNodes.add(node.right.val);
            nextLevelQueue.add(node.right);
          }
          if (node.left != null) {
            sameLevelNodes.add(node.left.val);
            nextLevelQueue.add(node.left);
          }
        } else {
          if (node.left != null) {
            sameLevelNodes.add(node.left.val);
            nextLevelQueue.add(node.left);
          }
          if (node.right != null) {
            sameLevelNodes.add(node.right.val);
            nextLevelQueue.add(node.right);
          }
        }
      }
      if (!sameLevelNodes.isEmpty()) {
        zigZagNodeValues.add(sameLevelNodes);
      }
    }
    return zigZagNodeValues;
  }

  private boolean toggleSwitch(boolean bool) {
    return (bool) ? false : true;
  }
}