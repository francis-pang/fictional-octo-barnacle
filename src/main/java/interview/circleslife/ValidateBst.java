package interview.circleslife;

public class ValidateBst {
  public static void main(String[] args) throws Exception {
    System.out.println("Hello World");
    /**
     10
     5        15
     6    11
     */
  }

  private class TreeNode {
    public int value;
    public TreeNode left, right;
  }

  public static boolean isValidBST(TreeNode root) {
    return isValid(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
  }

  public static boolean isValid(TreeNode root, int maxValue, int minValue) {
    if (root == null) {
      return true;
    }
    int val = root.value;
    boolean result = true;
    if (root.left != null) {
      int leftV = root.left.value;
      if (leftV > maxValue && leftV > root.value) {
        return false;
      } else {
        result = isValid(root.left, root.value, Integer.MIN_VALUE);
      }
    }
    if (!result) {
      return false;
    }
    if (root.right != null) {
      int rightV = root.right.value;
      if (rightV < minValue && rightV < root.value) {
        return false;
      } else {
        result = isValid(root.right, Integer.MAX_VALUE, root.value);
      }
    }
    return result;
  }
}
