package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class UniqueBinarySearchTreesII {
  public List<TreeNode> generateTrees(int n) {
    if (n <= 0) {
      return Collections.emptyList();
    }
    Map<Integer, List<TreeNode>> treeMap = new HashMap<>();
    treeMap.put(0, Collections.emptyList());
    for (int i = 1; i <= n; i++) {
      generateBinaryTrees(treeMap, i);
    }
    List<TreeNode> treeList = treeMap.get(n);
    fillUpTree(treeList);
    return treeList;
  }

  private void fillUpTree(List<TreeNode> treeList) {
    for (TreeNode root : treeList) {
      inOrderTraversalFillUpTree(root, 1);
    }
  }

  private int inOrderTraversalFillUpTree(TreeNode root, int number) {
    if (root == null) {
      return number;
    }
    int nextUnusedNumber = inOrderTraversalFillUpTree(root.left, number);
    root.val = nextUnusedNumber;
    nextUnusedNumber = inOrderTraversalFillUpTree(root.right, nextUnusedNumber + 1);
    return nextUnusedNumber;
  }

  private void generateBinaryTrees(Map<Integer, List<TreeNode>> treeMap, int nodeCount) {
    List<TreeNode> treeNodeList = new ArrayList<>();
    int childNodeCount = nodeCount - 1;
    for (int leftChildCount = 0; leftChildCount <= childNodeCount; leftChildCount++) {
      int rightChildCount = childNodeCount - leftChildCount;

      List<TreeNode> leftSubTreeList = treeMap.get(leftChildCount);
      List<TreeNode> rightSubTreeList = treeMap.get(rightChildCount);
      if (leftSubTreeList.isEmpty() && rightSubTreeList.isEmpty()) {
        treeNodeList.add(new TreeNode());
        treeMap.put(nodeCount, treeNodeList);
        return;
      }
      if (leftSubTreeList.isEmpty()) {
        for (TreeNode rightChild : rightSubTreeList) {
          TreeNode clonedRightChild = cloneTree(rightChild);
          TreeNode root = new TreeNode();
          root.right = clonedRightChild;
          treeNodeList.add(root);
        }
      }
      if (rightSubTreeList.isEmpty()) {
        for (TreeNode leftChild : leftSubTreeList) {
          TreeNode clonedLeftChild = cloneTree(leftChild);
          TreeNode root = new TreeNode();
          root.left = clonedLeftChild;
          treeNodeList.add(root);
        }
      }
      for (TreeNode leftChild : leftSubTreeList) {
        for (TreeNode rightChild : rightSubTreeList) {
          TreeNode clonedLeftChild = cloneTree(leftChild);
          TreeNode clonedRightChild = cloneTree(rightChild);
          TreeNode root = new TreeNode();
          root.left = clonedLeftChild;
          root.right = clonedRightChild;
          treeNodeList.add(root);
        }
      }
    }
    treeMap.put(nodeCount, treeNodeList);
  }

  private TreeNode cloneTree(TreeNode node) {
    if (node == null) {
      return null;
    }
    TreeNode clonedNode = new TreeNode();
    clonedNode.left = cloneTree(node.left);
    clonedNode.right = cloneTree(node.right);
    return clonedNode;
  }

  public static void main(String[] args) {
    UniqueBinarySearchTreesII uniqueBinarySearchTreesII = new UniqueBinarySearchTreesII();
    List<TreeNode> generatedTrees = uniqueBinarySearchTreesII.generateTrees(4);
    int index = 1;
    for (TreeNode tree : generatedTrees) {
      System.out.println("Tree " + index++);
      System.out.println(tree);
    }
  }

  /**
   * Definition for a binary tree node.
   */
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", TreeNode.class.getSimpleName() + "[", "]")
          .add("val=" + val)
          .add("left=" + left)
          .add("right=" + right)
          .toString();
    }
  }
}