package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KillProcess {
  public class Node {
    public int value;
    public List<Node> children;

    public Node(int value) {
      this.value = value;
      children = new ArrayList<>();
    }
  }

  public List<Integer> killProcess(List<Integer> childrenProcessIds, List<Integer> parentProcessIds, int kill) {
    Map<Integer, Node> nodeTable = buildNodeTable(childrenProcessIds, parentProcessIds);
    Node root = nodeTable.get(kill);
    List<Integer> kills = new ArrayList<>();
    processKilled(root, kills);
    return kills;
  }

  private void processKilled(Node node, List<Integer> deletedProcessIds) {
    deletedProcessIds.add(node.value);
    for (Node child : node.children) {
      processKilled(child, deletedProcessIds);
    }
  }

  private Map<Integer, Node> buildNodeTable(List<Integer> children, List<Integer> parent) {
    Map<Integer, Node> table = new HashMap<>();
    for (int i = 0; i < children.size(); i++) {
      int childId = children.get(i);
      int parentId = parent.get(i);
      Node childNode = table.compute(childId, (k, v) -> v == null ? new Node(childId) : v);
      Node parentNode = table.compute(parentId, (k, v) -> v == null ? new Node(parentId) : v);
      parentNode.children.add(childNode);
    }
    return table;
  }
}
