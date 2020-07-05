package leetcode;

public class BinaryTreeMaximumPathSum {
  public static void main(String[] args) {
    TreeNode root = TreeNodeCreator.createTreeNode("[1,2,3]");
    BinaryTreeMaximumPathSum binaryTreeMaximumPathSum = new BinaryTreeMaximumPathSum();
    int answer = binaryTreeMaximumPathSum.maxPathSum(root);
    System.out.println(answer);
  }

  private int max;

  public int maxPathSum(TreeNode root) {
    max = Integer.MIN_VALUE;
    if (root == null) {
      return max;
    }
    maxSumPath(root);
    return max;
  }

  private int maxSumPath(TreeNode node) {
    int leftMax = (node.left == null) ? 0 : maxSumPath(node.left);
    int rightMax = (node.right == null) ? 0 : maxSumPath(node.right);

    int sum = node.val;
    if (leftMax > 0 && rightMax > 0) {
      int combinedSum = node.val + leftMax + rightMax;
      if (max < combinedSum) {
        max = combinedSum;
      }
      sum += (leftMax > rightMax) ? leftMax : rightMax;
    } else if (leftMax > 0) {
      sum += leftMax;
    } else if (rightMax > 0) {
      sum += rightMax;
    }
    if (sum > max) {
      max = sum;
    }
    return sum;
  }
}
