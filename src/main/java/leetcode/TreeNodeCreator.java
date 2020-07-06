package leetcode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNodeCreator {
  public static TreeNode createTreeNode(String nodeString) {
    String trimmedString = nodeString.substring(1, nodeString.length() - 1);
    String[] splitNodeValues = trimmedString.split(",");
    LinkedList<Integer> integerList = new LinkedList<>();
    for (String value : splitNodeValues) {
      String trimmedValue = value.trim();
      if ("null".equals(trimmedValue)) {
        integerList.add(null);
      } else {
        int i = Integer.valueOf(trimmedValue);
        integerList.add(i);
      }
    }
    return createTreeNodeFromIntegerLists(integerList);
  }

  private static TreeNode createTreeNodeFromIntegerLists(LinkedList<Integer> integerList) {
    int headValue = integerList.removeFirst();
    TreeNode root = new TreeNode(headValue);
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);
    int counter = 0;
    for (Integer val : integerList) {
      TreeNode parentNode = queue.peek();
      TreeNode newNode = (val != null) ? new TreeNode(val) : null;
      counter++;
      if (counter == 1) {
        parentNode.left = newNode;
      } else if (counter == 2) {
        parentNode.right = newNode;
      }
      if (val != null) {
        queue.add(newNode);
      }
      if (counter == 2) {
        queue.poll();
        counter = 0;
      }
    }
    return root;
  }
}
