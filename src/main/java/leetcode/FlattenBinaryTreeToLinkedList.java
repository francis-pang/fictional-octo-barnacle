package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class FlattenBinaryTreeToLinkedList {
  public static void main(String[] args) {
    TreeNode root = TreeNodeCreator.createTreeNode("[1,2,5,3,4,null,6]");
    FlattenBinaryTreeToLinkedList flattenBinaryTreeToLinkedList = new FlattenBinaryTreeToLinkedList();
    flattenBinaryTreeToLinkedList.flatten(root);
    System.out.println(root);
  }

  public void flatten(TreeNode root) {
    if (root == null) {
      return;
    }
    Queue<TreeNode> reversedOrderedNodes = new ArrayDeque<>();
    preOrderTraverse(reversedOrderedNodes, root);
    TreeNode predecessor = null;
    while (!reversedOrderedNodes.isEmpty()) {
      TreeNode node = reversedOrderedNodes.poll();
      node.left = null;
      node.right = null;
      if (predecessor != null) {
        predecessor.right = node;
      }
      predecessor = node;
    }
  }

  private void preOrderTraverse(Queue<TreeNode> reversedOrderedNodes, TreeNode node) {
    reversedOrderedNodes.add(node);
    if (node.left != null) {
      preOrderTraverse(reversedOrderedNodes, node.left);
    }
    if (node.right != null) {
      preOrderTraverse(reversedOrderedNodes, node.right);
    }
  }
}
