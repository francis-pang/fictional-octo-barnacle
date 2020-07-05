package leetcode;

public class BalancedBinaryTree {
  private static int NOT_BALANCED = -1;

  public boolean isBalanced(TreeNode root) {
    return calculateHeight(root) != NOT_BALANCED;
  }

  private int calculateHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftChildHeight = calculateHeight(root.left);
    int rightChildHeight = calculateHeight(root.right);
    if (leftChildHeight == NOT_BALANCED || rightChildHeight == NOT_BALANCED) {
      return NOT_BALANCED;
    }
    int heightDifference = leftChildHeight - rightChildHeight;
    if (Math.abs(heightDifference) > 1) {
      return NOT_BALANCED;
    }
    return Math.max(leftChildHeight, rightChildHeight) + 1;
  }
}
