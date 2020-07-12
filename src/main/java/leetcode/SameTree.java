package leetcode;

public class SameTree {
  public boolean isSameTree(TreeNode p, TreeNode q) {
    // Use DFS for verification
    if ((p == null && q != null) || (q == null && q != null)) {
      return false;
    }
    if (p == null & q == null) {
      return true;
    }
    // By here, we can assert that both node is not null
    if (p.val != q.val) {
      return false;
    }
    return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
  }
}
