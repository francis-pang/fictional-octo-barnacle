package facebook.abcs.trees;

import java.util.Scanner;

/**
 * You are given a pointer to the root of a binary search tree and values to be inserted into the tree. Insert
 * the values into their appropriate position in the binary search tree and return the root of the updated
 * binary tree. You just have to complete the function.
 * <b>Input Format</b>
 * You are given a function,
 * <pre>
 * Node * insert (Node * root ,int data) {
 * }
 * </pre>
 * <b>Constraints</b>
 * No. of nodes in the tree <= 500
 * <b>Output Format</b>
 * Return the root of the binary search tree after inserting the value into the tree.
 * <b>Sample Input</b>
 * 4
 * /  \
 * 2    7
 * / \
 * 1   3
 * The value to be inserted is 6.
 * <b>Sample Output</b>
 * 4
 * /  \
 * 2    7
 * / \  /
 * 1  3 6
 */
public class BinarySearchTreeInsertion {
  static class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
      this.data = data;
      left = null;
      right = null;
    }
  }

  static class Solution {

    public static void preOrder(Node root) {
      if (root == null)
        return;
      System.out.print(root.data + " ");
      preOrder(root.left);
      preOrder(root.right);
    }

    public static Node insert(Node root, int data) {
      if (root == null) {
        root = new Node(data);
        return root;
      }
      if (data <= root.data) {
        Node leftSubtree = insert(root.left, data);
        root.left = leftSubtree;
      } else {
        Node rightSubtree = insert(root.right, data);
        root.right = rightSubtree;
      }
      return root;
    }

    public static void main(String[] args) {
      Scanner scan = new Scanner("6\n4 2 3 1 7 6");
      int t = scan.nextInt();
      Node root = null;
      while (t-- > 0) {
        int data = scan.nextInt();
        root = insert(root, data);
      }
      scan.close();
      preOrder(root);
    }
  }
}
