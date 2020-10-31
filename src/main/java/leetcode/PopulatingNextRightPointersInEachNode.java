package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNode {
  static class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }

    @Override
    public String toString() {
      return "Node{" +
          "val=" + val +
          '}';
    }
  }

  public static void main(String[] args) {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    Node node7 = new Node(7);

    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;
    node3.right = node7;

    PopulatingNextRightPointersInEachNode populatingNextRightPointersInEachNode = new PopulatingNextRightPointersInEachNode();
    populatingNextRightPointersInEachNode.connect(node1);
  }

  public Node connect(Node root) {
    if (root == null) {
      return root;
    }
    // Use BFS
    Queue<Node> nextLevelQueue = new ArrayDeque<>();
    nextLevelQueue.add(root);

    while (!nextLevelQueue.isEmpty()) {
      Queue<Node> queue = nextLevelQueue;
      nextLevelQueue = new ArrayDeque<>();
      Node previousNode = null;

      while (!queue.isEmpty()) {
        Node node = queue.poll();
        if (previousNode != null) {
          previousNode.next = node;
        }
        if (node.left != null) {
          nextLevelQueue.add(node.left);
        }
        if (node.right != null) {
          nextLevelQueue.add(node.right);
        }
        previousNode = node;
      }
    }
    return root;
  }
}