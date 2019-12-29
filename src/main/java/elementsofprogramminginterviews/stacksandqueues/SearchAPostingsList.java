package elementsofprogramminginterviews.stacksandqueues;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchAPostingsList {
  public List<Node> jumpList(Node root) {
    int size = computeSize(root);
    List<Node> order = new ArrayList<>();
    Set<Node> visited = new HashSet<>();
    nextNode(root, order, size, visited);
    return order;
  }

  private int computeSize(Node root) {
    int total = 0;
    Node node = root;
    while (node != null) {
      total++;
      node = node.next;
    }
    return total;
  }

  boolean nextNode(Node node, List<Node> order, int size, Set<Node> visited) {
    order.add(node);
    if (order.size() == size) {
      return true;
    }
    visited.add(node);
    Node next = node.jump;
    boolean complete = false;
    if (!visited.contains(next)) {
      complete = nextNode(next, order, size, visited);
    }
    if (!complete) {
      next = node.next;
      if (!visited.contains(next)) {
        complete = nextNode(next, order, size, visited);
      }
    }
    if (!complete) {
      order.remove(order.size() - 1);
      visited.remove(node);
      return false;
    }
    return true;
  }

  public class Node {
    public int value;
    public Node next;
    public Node jump;
  }
}
