package elementsofprogramminginterviews.binarytrees;

import java.util.ArrayList;

public class FindARootToLeafPathWithSpecifiedSum {
  public boolean canSumToValue(Node root, int sum) {
    ArrayList<Node> nodes = new ArrayList<>();
    return findSum(nodes, root, sum);
  }

  private boolean findSum(ArrayList<Node> path, Node node, int sum) {
    path.add(node);
    int total = 0;
    for (int i = path.size() - 1; i >= 0; i--) {
      Node curr = path.get(i);
      total += curr.value;
      if (total == sum) {
        return true;
      }
    }
    boolean result = false;
    if (node.left != null) {
      result = findSum(path, node.left, sum);
    }
    if (result) {
      return result;
    }
    if (node.right != null) {
      result = findSum(path, node.right, sum);
    }
    if (result) {
      return true;
    } else {
      path.remove(path.size() - 1);
      return false;
    }
  }

  public class Node {
    public int value;
    public Node left;
    public Node right;
  }
}

