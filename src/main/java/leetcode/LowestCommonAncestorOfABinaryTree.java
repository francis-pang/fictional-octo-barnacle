package leetcode;

import java.util.StringJoiner;

public class LowestCommonAncestorOfABinaryTree {
  public static void main(String[] args) {
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    TreeNode n4 = new TreeNode(4);
    TreeNode n5 = new TreeNode(5);
    TreeNode n6 = new TreeNode(6);
    TreeNode n7 = new TreeNode(7);
    TreeNode n8 = new TreeNode(8);
    TreeNode n9 = new TreeNode(9);
    TreeNode n10 = new TreeNode(10);
    TreeNode n11 = new TreeNode(11);
    TreeNode n12 = new TreeNode(12);
    TreeNode n13 = new TreeNode(13);
    TreeNode n14 = new TreeNode(14);
    TreeNode n15 = new TreeNode(15);

    n1.left = n2;
    n1.right = n3;

    n2.left = n4;
    n2.right = n5;

    n3.left = n6;
    n3.right = n7;

    n4.left = n8;
    n4.right = n9;

    n5.left = n10;
    n5.right = n11;

    n6.left = n12;
    n6.right = n13;

    n7.left = n14;
    n7.right = n15;

    Solution solution = new Solution();
    System.out.println("Ancestor of 2,3 is " + solution.lowestCommonAncestor(n1, n2, n3));
    System.out.println("Ancestor of 2,5 is " + solution.lowestCommonAncestor(n1, n2, n5));
    System.out.println("Ancestor of 4,11 is " + solution.lowestCommonAncestor(n1, n4, n11));
    System.out.println("Ancestor of 4,5 is " + solution.lowestCommonAncestor(n1, n4, n5));
    System.out.println("Ancestor of 8,11 is " + solution.lowestCommonAncestor(n1, n8, n11));
    System.out.println("Ancestor of 8,10 is " + solution.lowestCommonAncestor(n1, n8, n10));
    System.out.println("Ancestor of 8,9 is " + solution.lowestCommonAncestor(n1, n8, n9));
    System.out.println("Ancestor of 4,6 is " + solution.lowestCommonAncestor(n1, n4, n6));
    System.out.println("Ancestor of 4,15 is " + solution.lowestCommonAncestor(n1, n4, n15));
    System.out.println("Ancestor of 6,14 is " + solution.lowestCommonAncestor(n1, n14, n6));
  }

  static class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      return processNode(root, p, q).node;
    }

    private AncestorSearchResult processNode(TreeNode node, TreeNode p, TreeNode q) {
      if (node == null) {
        return new AncestorSearchResult(false, false);
      }
      AncestorSearchResult leftResult = processNode(node.left, p, q);
      if (leftResult.foundBoth()) {
        return leftResult;
      }

      AncestorSearchResult rightResult = processNode(node.right, p, q);
      if (rightResult.foundBoth()) {
        return rightResult;
      }

      AncestorSearchResult combinedSearchResult = new AncestorSearchResult(false, false).mergePairs(leftResult, rightResult);
      if (combinedSearchResult.foundBoth()) {
        combinedSearchResult.node = node;
        return combinedSearchResult;
      } else if (node == p) {
        combinedSearchResult.foundP = true;
      } else if (node == q) {
        combinedSearchResult.foundQ = true;
      }

      if (combinedSearchResult.foundBoth()) {
        combinedSearchResult.node = node;
      }
      return combinedSearchResult;
    }

    class AncestorSearchResult {
      public boolean foundP;
      public boolean foundQ;
      public TreeNode node;

      public AncestorSearchResult(boolean foundP, boolean foundQ) {
        this.foundP = foundP;
        this.foundQ = foundQ;
      }

      AncestorSearchResult mergePairs(AncestorSearchResult firstAncestorSearchResult, AncestorSearchResult secondAncestorSearchResult) {
        AncestorSearchResult mergedAncestorSearchResult = new AncestorSearchResult(false, false);
        mergedAncestorSearchResult.foundP = firstAncestorSearchResult.foundP || secondAncestorSearchResult.foundP;
        mergedAncestorSearchResult.foundQ = firstAncestorSearchResult.foundQ || secondAncestorSearchResult.foundQ;
        return mergedAncestorSearchResult;
      }

      public boolean foundBoth() {
        return foundP && foundQ;
      }
    }
  }
}
