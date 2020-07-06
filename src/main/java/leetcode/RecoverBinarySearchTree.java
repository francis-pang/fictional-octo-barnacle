package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class RecoverBinarySearchTree {
  public static void main(String[] args) {
//    TreeNode root = TreeNodeCreator.createTreeNode("[2,1,null,null,3,4]");
    TreeNode root = TreeNodeCreator.createTreeNode("[1,3,null,null,2]");

    RecoverBinarySearchTree recoverBinarySearchTree = new RecoverBinarySearchTree();
    recoverBinarySearchTree.recoverTree(root);
    System.out.println(root);
  }

  public void recoverTree(TreeNode root) {
    if (root == null) {
      return;
    }
    recoverTreeByIterativeInorderThenSort(root);
  }

  private void recoverTreeByIterativeInorderThenSort(TreeNode root) {
    TreeNode firstMisplacedNode = null;
    TreeNode secondMisplacedNode = null;
    findMisplacedNodes(root, firstMisplacedNode, secondMisplacedNode);
    swap(firstMisplacedNode, secondMisplacedNode);
  }

  private void swap(TreeNode firstMisplacedNode, TreeNode secondMisplacedNode) {
    int temp = firstMisplacedNode.val;
    firstMisplacedNode.val = secondMisplacedNode.val;
    secondMisplacedNode.val = firstMisplacedNode.val;
  }

  private void findMisplacedNodes(TreeNode node, TreeNode firstMisplacedNode, TreeNode secondMisplacedNode) {
    Stack<TreeNode> stack = new Stack();
    TreeNode predecessorNode = null;
    while (!stack.isEmpty() || node != null) {
      while (node != null) {
        stack.add(node);
        node = node.left;
      }
      node = stack.pop();
      if (predecessorNode != null && node.val < predecessorNode.val) {
        secondMisplacedNode = node;
        if (firstMisplacedNode == null) {
          // This is the first node found to be misplaced, out of order.
          // If it is the only misplaced case found, then just need to swap these 2 adjacent nodes
          firstMisplacedNode = predecessorNode;
        } else {
          // We have already found both misplaced nodes, so we will break off the search and do an early exit
          return;
        }
      }
      predecessorNode = node;
      node = node.right;
    }
  }

  private void recoverTreeByRecursiveInorderThenSort(TreeNode root) {
    // Collect all node
    ArrayList<TreeNode> nodeValues = collectAllNodesValues(root);
    // Sort the node
    sortNodesValues(nodeValues);
  }

  private void sortNodesValues(ArrayList<TreeNode> nodeValues) {
    for (int i = 1; i < nodeValues.size(); i++) {
      boolean sorted = false;
      int comparisonPosition = i;
      while (!sorted && comparisonPosition > 0) {
        int smallerNodeValue = nodeValues.get(comparisonPosition - 1).val;
        int biggerNodeValue = nodeValues.get(comparisonPosition).val;
        if (smallerNodeValue < biggerNodeValue) {
          sorted = true;
        } else {
          swap(nodeValues, comparisonPosition - 1, comparisonPosition);
          comparisonPosition--;
        }
      }
    }
  }

  private void swap(ArrayList<TreeNode> nodeValues, int firstPosition, int secondPosition) {
    int firstValue = nodeValues.get(firstPosition).val;
    int secondValue = nodeValues.get(secondPosition).val;
    nodeValues.get(firstPosition).val = secondValue;
    nodeValues.get(secondPosition).val = firstValue;
  }

  private ArrayList<TreeNode> collectAllNodesValues(TreeNode root) {
    ArrayList<TreeNode> nodesValues = new ArrayList<>();
    // Collect from in-order traversal
    fillUpArrayList(root, nodesValues);
    return nodesValues;
  }

  private void fillUpArrayList(TreeNode node, List<TreeNode> nodesValues) {
    if (node == null) {
      return;
    }
    if (node.left != null) {
      fillUpArrayList(node.left, nodesValues);
    }
    nodesValues.add(node);
    if (node.right != null) {
      fillUpArrayList(node.right, nodesValues);
    }
  }
}
