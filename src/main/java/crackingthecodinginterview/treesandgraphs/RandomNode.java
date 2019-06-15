package crackingthecodinginterview.treesandgraphs;

import java.util.ArrayList;

/**
 * You are implementing a binary tree class from scratch which, in addition to
 * insert, find, and delete, has a method getRandomNode() which returns a random node
 * from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm
 * for getRandomNode, and explain how you would implement the rest of the methods.
 */
public class RandomNode {
  public class RandomBinaryTree {
    private RandomBinaryTreeNode root;
    private ArrayList<RandomBinaryTreeNode> nodeList;

    public RandomBinaryTree() {
      this.nodeList = new ArrayList<>();
    }

    public void insert(RandomBinaryTreeNode node) {
      nodeList.add(node);
    }

    public RandomBinaryTreeNode find(int value) {
      return null;
    }

    public boolean delete(RandomBinaryTreeNode node) {
      return nodeList.remove(node);
    }

    public RandomBinaryTreeNode getRandomNode() {
      return nodeList.get((int) Math.round(Math.random() * nodeList.size()));
    }

    public class RandomBinaryTreeNode {
      public int value;
      public RandomBinaryTreeNode left;
      public RandomBinaryTreeNode right;
      public int height;

      public RandomBinaryTreeNode(int value) {
        this.value = value;
      }
    }
  }
}
