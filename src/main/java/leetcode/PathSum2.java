package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PathSum2 {
  public static void main(String[] args) {
    PathSum2 pathSum2 = new PathSum2();
    TreeNode root = TreeNodeCreator.createTreeNode("[5,4,8,11,null,13,4,7,2,null,null,5,1]");
    List<List<Integer>> answer = pathSum2.pathSum(root, 22);
    System.out.println(answer);
  }

  private static List<List<Integer>> paths;
  private static int target;
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    if (root == null) {
      return Collections.EMPTY_LIST;
    }
    paths = new ArrayList<>();
    target = sum;
    LinkedList<Integer> curPath = new LinkedList<>();
    gatherSumPaths(curPath, root, 0);
    return paths;
  }

  private void gatherSumPaths(LinkedList<Integer> curPath, TreeNode node, int sum) {
    curPath.add(node.val);
    sum += node.val;
    // leaf node
    if (node.left == null && node.right == null) {
      if (sum == target) {
        paths.add((LinkedList<Integer>) curPath.clone());
      }
    }
    if (node.left != null) {
      gatherSumPaths(curPath, node.left, sum);
    }
    if (node.right != null) {
      gatherSumPaths(curPath, node.right, sum);
    }
    curPath.removeLast();
    sum -= node.val;
  }
}