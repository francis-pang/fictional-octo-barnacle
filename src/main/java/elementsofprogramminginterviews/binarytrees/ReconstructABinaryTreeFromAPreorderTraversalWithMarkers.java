import java.util.HashMap;
import java.util.Map;

public class ReconstructABinaryTreeFromAPreorderTraversalWithMarkers {
  public Node reconstructTree(Character[] preOrder) {
    NodeWithNextPos rootWithPos = buildSubTree(preOrder, 0);
    return rootWithPos.node;
  }

  private NodeWithNextPos buildSubTree(Character[] preOrder, int index) {
    if (index == preOrder.length -1 || preOrder[index] == null) {
      return new NodeWithNextPos(null, index + 1);
    }
    Node root = new Node(preOrder[index]);
    if (preOrder[index + 1] == null) {
      if (preOrder[index + 2] == null) {
        return new NodeWithNextPos(root, index + 3);
      } else {
        NodeWithNextPos rightSubTree = buildSubTree(preOrder, index + 2);
        root.right = rightSubTree.node;
        return new NodeWithNextPos(root, rightSubTree.nextPosition);
      }
    } else {
      NodeWithNextPos leftSubTree = buildSubTree(preOrder, index + 1);
      root.left = leftSubTree.node;
      NodeWithNextPos rightSubTree = buildSubTree(preOrder, leftSubTree.nextPosition);
      root.right = rightSubTree.node;
      return new NodeWithNextPos(root, rightSubTree.nextPosition);
    }
  }

  class NodeWithNextPos {
    Node node;
    int nextPosition;

    public NodeWithNextPos(Node node, int nextPosition) {
      this.node = node;
      this.nextPosition = nextPosition;
    }
  }
  static class Node {
    public char val;
    public Node left;
    public Node right;

    public Node(char val) {
      this.val = val;
    }
  }

  public static void main(String[] args) {
    ReconstructABinaryTreeFromAPreorderTraversalWithMarkers reconstructABinaryTreeFromAPreorderTraversalWithMarkers = new ReconstructABinaryTreeFromAPreorderTraversalWithMarkers();
    Character[] preOrder = new Character[]{'H', 'B', 'F', null, null, 'E', 'A', null, null, null, 'C', null, 'D', null, 'G', 'I', null, null, null};
    Node root = reconstructABinaryTreeFromAPreorderTraversalWithMarkers.reconstructTree(preOrder);
    System.out.println("Done");
  }
}
