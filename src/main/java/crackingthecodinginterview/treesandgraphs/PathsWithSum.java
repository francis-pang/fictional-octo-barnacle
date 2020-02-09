package crackingthecodinginterview.treesandgraphs;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a binary tree in which each node contains an integer value (which
 * might be positive or negative). Design an algorithm to count the number of paths that sum to a
 * given value. The path does not need to start or end at the root or a leaf, but it must go downwards
 * (traveling only from parent nodes to child nodes).
 */
public class PathsWithSum {
  private static Map<Integer, Integer> sumToOccurrencesTable;

  public int countPathSumToTarget(Node root, int target) {
    sumToOccurrencesTable = new HashMap<>();
    return countPathFromNodeSumToTarget(root, target, 0);
  }

  private int countPathFromNodeSumToTarget(Node node, int target, int runningSum) {
    if (node == null) {
      return 0;
    }
    runningSum += node.val;
    int targetSum = runningSum - target;
    int numberOfPath = sumToOccurrencesTable.getOrDefault(targetSum, 0);
    // Handle this edge case. If the running sum is the target sum already, which means that there is a path from root
    // to this node
    if (runningSum == target) {
      numberOfPath++;
    }
    sumToOccurrencesTable.compute(runningSum, (sum, occurrences) -> occurrences == null ? 1 : occurrences + 1);
    numberOfPath += countPathFromNodeSumToTarget(node.left, target, runningSum);
    numberOfPath += countPathFromNodeSumToTarget(node.right, target, runningSum);
    sumToOccurrencesTable.compute(runningSum, (sum, occurrences) -> occurrences == null ? 0 : occurrences - 1);
    return numberOfPath;
  }

  static class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
      this.val = val;
    }
  }

  public static void main(String[] args) {
    Node root0 = new Node(1);
    Node node11 = new Node(-3);
    root0.left = node11;
    Node node12 = new Node(0);
    root0.right = node12;
    Node node21 = new Node(7);
    node11.left = node21;
    Node node22 = new Node(2);
    node11.right = node22;
    Node node23 = new Node(5);
    node12.right = node23;
    Node node31 = new Node(3);
    node22.left = node31;
    Node node41 = new Node(3);
    node31.left = node41;
    Node node42 = new Node(9);
    node31.right = node42;

    PathsWithSum pathsWithSum = new PathsWithSum();
    System.out.println(pathsWithSum.countPathSumToTarget(root0, 5));
  }
}
