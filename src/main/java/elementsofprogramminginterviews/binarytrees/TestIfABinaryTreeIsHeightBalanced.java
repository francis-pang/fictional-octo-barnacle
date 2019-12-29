package elementsofprogramminginterviews.binarytrees;

public class TestIfABinaryTreeIsHeightBalanced {
  public boolean isHeightBalance(TreeNode root) {
    int heightDifference = calculateHeight(root);
    return heightDifference != -1;
  }

  private int calculateHeight(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int leftHeight = calculateHeight(node.left);
    if (leftHeight == -1) {
      return -1;
    }
    int rightHeight = calculateHeight(node.right);
    if (rightHeight == -1) {
      return -1;
    }
    int heightDifference = leftHeight - rightHeight;
    heightDifference = Math.abs(heightDifference);
    if (heightDifference > 1) {
      return -1;
    }
    return Math.max(leftHeight, rightHeight) + 1;
  }


  public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;
  }
}
