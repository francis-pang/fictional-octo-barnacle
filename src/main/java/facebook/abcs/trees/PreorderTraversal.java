package facebook.abcs.trees;

import java.util.Scanner;

/**
 * Complete the preOrder function in your editor below, which has parameter: a pointer to the root of a
 * binary tree. It must print the values in the tree's preorder traversal as a single line of space-separated
 * values.
 * <b>Input Format</b>
 * Our hidden tester code passes the root node of a binary tree to your preOrder function.
 * <b>Constraints</b>
 * 1 <= Nodes in the tree <= 500
 * <b>Output Format</b>
 * Print the tree's preorder traversal as a single line of space-separated values.
 * Sample Input
 * 1
 * \
 * 2
 * \
 * 5
 * / \
 * 3   6
 * \
 * 4
 * <b>Sample Output</b>
 * 1 2 5 3 4 6
 */
public class PreorderTraversal {
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
      // Use recursion to solve
      // Base case
      if (root == null) {
        return;
      }

      // Induction step
      System.out.print(root.data + " ");
      preOrder(root.left);
      preOrder(root.right);
    }

    public static Node insert(Node root, int data) {
      if (root == null) {
        return new Node(data);
      } else {
        Node cur;
        if (data <= root.data) {
          cur = insert(root.left, data);
          root.left = cur;
        } else {
          cur = insert(root.right, data);
          root.right = cur;
        }
        return root;
      }
    }

    public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
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
