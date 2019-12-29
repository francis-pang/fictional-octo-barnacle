package elementsofprogramminginterviews.stacksandqueues;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ComputeBinaryTreeNodesInOrderOfIncreasingDepth {
  public List<List<TreeNode>> levelOrderTraversal(TreeNode root) {
    List<List<TreeNode>> treeNodes = new ArrayList<>();
    Queue<TreeNode> nextLevel = new ArrayDeque<>();
    Queue<TreeNode> currentLevel;
    nextLevel.add(root);
    while (!nextLevel.isEmpty()) {
      treeNodes.add(new ArrayList<>(nextLevel));
      currentLevel = nextLevel;
      while (!currentLevel.isEmpty()) {
        TreeNode parent = currentLevel.poll();
        if (parent.left != null) {
          nextLevel.add(parent.left);
        }
        if (parent.right != null) {
          nextLevel.add(parent.right);
        }
      }
    }
    return treeNodes;
  }

  public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;
  }
}
