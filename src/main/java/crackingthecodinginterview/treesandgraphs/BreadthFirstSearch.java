package crackingthecodinginterview.treesandgraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringJoiner;

/**
 * This is a practice for Breadth first search of a tree
 */
public class BreadthFirstSearch {
  Queue<Node> yetToBeProcessQueue = new LinkedList<>();

  public String printTree(Node root) {
    if (root == null) {
      return "";
    }

    StringJoiner stringJoiner = new StringJoiner(",");
    stringJoiner.add(Integer.toString(root.value));

    for(Node node : root.children) {
      yetToBeProcessQueue.offer(node);
    }
    do {
      Node processingNode = yetToBeProcessQueue.poll();
      stringJoiner.add(printTree(processingNode));
    } while(!yetToBeProcessQueue.isEmpty());

    return stringJoiner.toString();
  }

  public Node locateNode(Node root, int searchingValue) {
    return locateNodePostOrderProcessing(root, searchingValue); //stub
  }

  public Node locateNodePostOrderProcessing(Node root, int searchingValue) {
    if (root == null) {
      return null;
    }
    if (root.value == searchingValue) {
      return root;
    }
    for(Node node : root.children) {
      yetToBeProcessQueue.offer(node);
    }
    do {
      Node processingNode = yetToBeProcessQueue.poll();
      Node foundNode = locateNodePostOrderProcessing(processingNode, searchingValue);
      if (foundNode != null) {
        return foundNode;
      }
    } while(!yetToBeProcessQueue.isEmpty());
    return null;
  }

  public static class Node {
    int value;
    List<Node> children;

    public Node(int value) {
      this.value = value;
      this.children = new ArrayList<>();
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
          .add("value=" + value)
          .add("children=" + children)
          .toString();
    }
  }
}
