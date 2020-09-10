package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class SymmetricTree {
  public static void main(String[] args) {
    TreeNode root = TreeNodeCreator.createTreeNode("[1,2,2,null,3,null,3]");
    SymmetricTree symmetricTree = new SymmetricTree();
    System.out.println(symmetricTree.isSymmetric(root));
  }

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isSymmetricByBfs(root);
  }

  /**
   * Use Breadth First Search to determine if a root is symmetric
   *
   */
  private boolean isSymmetricByBfs(TreeNode root) {
    Queue<TreeNode> leftNextLevelQueue = new ArrayDeque<>();
    if (root.left != null) {
      leftNextLevelQueue.add(root.left);
    }

    Queue<TreeNode> rightNextLevelQueue = new ArrayDeque<>();
    if (root.right != null) {
      rightNextLevelQueue.add(root.right);
    }

    while (!leftNextLevelQueue.isEmpty() && !rightNextLevelQueue.isEmpty()) {
      Queue<TreeNode> currentLeftQueue = leftNextLevelQueue;
      leftNextLevelQueue = new ArrayDeque<>();

      Queue<TreeNode> currentRightQueue = rightNextLevelQueue;
      rightNextLevelQueue = new ArrayDeque<>();

      while (!currentLeftQueue.isEmpty() && !currentRightQueue.isEmpty()) {
        TreeNode leftNode = currentLeftQueue.poll();
        TreeNode rightNode = currentRightQueue.poll();
        if (leftNode.val != rightNode.val) {
          return false;
        }
        // first child
        if (!haveSameExistance(leftNode.left, rightNode.right)) {
          return false;
        }
        if (leftNode.left != null) {
          leftNextLevelQueue.add(leftNode.left);
          rightNextLevelQueue.add(rightNode.right);
        }
        if (!haveSameExistance(leftNode.right, rightNode.left)) {
          return false;
        }
        if (leftNode.right != null) {
          leftNextLevelQueue.add(leftNode.right);
          rightNextLevelQueue.add(rightNode.left);
        }
      }
      if (!currentLeftQueue.isEmpty() || !currentRightQueue.isEmpty()) {
        return false;
      }
    }
    return leftNextLevelQueue.isEmpty() && rightNextLevelQueue.isEmpty();
  }

  private boolean haveSameExistance(TreeNode node1, TreeNode node2) {
    return (node1 == null && node2 == null) || (node1 != null && node2 != null);
  }
}