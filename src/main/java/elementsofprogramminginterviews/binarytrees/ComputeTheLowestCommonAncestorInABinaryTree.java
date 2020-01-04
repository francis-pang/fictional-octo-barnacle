package elementsofprogramminginterviews.binarytrees;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class ComputeTheLowestCommonAncestorInABinaryTree {
  public Node commonAncestor(Node root, Node nodeA, Node nodeB) {
    Map<Node, NodeContext> parentTable;
    parentTable = buildParentTable(root);
    return lowestAncestor(parentTable, nodeA, nodeB);
  }

  private Map<Node, NodeContext> buildParentTable(Node root) {
    Map<Node, NodeContext> parentTable = new HashMap<>();
    Queue<Node> nextLevelNodes = new ArrayDeque<>();
    Queue<Node> currentLevelNodes;
    nextLevelNodes.add(root);
    int depth = 0;
    Node parent = null;
    NodeContext rootContext = new NodeContext(depth, parent);
    parentTable.put(root, rootContext);
    while (!nextLevelNodes.isEmpty()) {
      depth++;
      currentLevelNodes = nextLevelNodes;
      while (!currentLevelNodes.isEmpty()) {
        Node node = currentLevelNodes.poll();
        NodeContext nodeContext = new NodeContext(depth, node);
        if (node.left != null) {
          parentTable.put(node.left, nodeContext);
          nextLevelNodes.add(node.left);
        }
        if (node.right != null) {
          parentTable.put(node.right, nodeContext);
        }
      }
    }
    return parentTable;
  }

  private Node lowestAncestor(Map<Node, NodeContext> parentMap, Node nodeA, Node nodeB) {
    NodeContext nodeAContext = parentMap.get(nodeA);
    int nodeADepth = nodeAContext.depth;
    NodeContext nodeBContext = parentMap.get(nodeB);
    int nodeBDepth = nodeBContext.depth;
    int commonDepth = Math.min(nodeADepth, nodeBDepth);
    while (commonDepth >= 0) {
      while (nodeADepth > commonDepth) {
        nodeA = nodeAContext.parent;
        nodeAContext = parentMap.get(nodeA);
        nodeADepth = nodeAContext.depth;
      }
      while (nodeBDepth > commonDepth) {
        nodeB = nodeBContext.parent;
        nodeBContext = parentMap.get(nodeB);
        nodeBDepth = nodeBContext.depth;
      }
      if (nodeA.equals(nodeB)) {
        return nodeA;
      }
      commonDepth--;
    }
    return null;
  }

  public class Node {
    public int val;
    public Node left;
    public Node right;
  }

  public class NodeContext {
    public int depth;
    public Node parent;

    public NodeContext(int depth, Node parent) {
      this.depth = depth;
      this.parent = parent;
    }
  }

  public class AncestorNode {
    public boolean foundA;
    public boolean foundB;
    public Node ancestor;

    public AncestorNode(boolean foundA, boolean foundB) {
      this.foundA = foundA;
      this.foundB = foundB;
    }

    public AncestorNode(boolean foundA, boolean foundB, Node ancestor) {
      this.foundA = foundA;
      this.foundB = foundB;
      this.ancestor = ancestor;
    }
  }

  public Node commonAncestorRecursive(Node root, Node nodeA, Node nodeB) {
    return recursiveLowestAncestor(root, nodeA, nodeB).ancestor;
  }

  private AncestorNode recursiveLowestAncestor(Node node, Node nodeA, Node nodeB) {
    if (node == null) {
      return new AncestorNode(false, false);
    }
    boolean nodeIsA = node.equals(nodeA);
    boolean nodeIsB = node.equals(nodeB);
    AncestorNode leftAncestorNode = recursiveLowestAncestor(node.left, nodeA, nodeB);
    if (leftAncestorNode.foundA && leftAncestorNode.foundB) {
      return leftAncestorNode;
    } else if ((nodeIsA && leftAncestorNode.foundB) || // If nodeB is a child of nodeA
        (nodeIsB && leftAncestorNode.foundA)) { // If nodeA is a child of nodeB
      return new AncestorNode(true, true, node);
    }

    AncestorNode rightAncestorNode = recursiveLowestAncestor(node.right, nodeA, nodeB);
    if (rightAncestorNode.foundA && rightAncestorNode.foundB) {
      return rightAncestorNode;
    } else if ((nodeIsA && rightAncestorNode.foundB) || (leftAncestorNode.foundA && rightAncestorNode.foundB) ||
        (nodeIsB && rightAncestorNode.foundA) || (leftAncestorNode.foundB && rightAncestorNode.foundA)) {
      return new AncestorNode(true, true, node);
    }
    AncestorNode ancestorNode = new AncestorNode(nodeIsA, nodeIsB);
    if (leftAncestorNode.foundA || rightAncestorNode.foundA) {
      ancestorNode.foundA = true;
    }
    if (leftAncestorNode.foundB || rightAncestorNode.foundB) {
      ancestorNode.foundB = true;
    }
    return ancestorNode;
  }
}
