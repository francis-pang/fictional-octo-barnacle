import java.util.HashMap;
import java.util.Map;

public class ReconstructABinaryTreeFromTraversalData {
  public Node constructTree(char[] inOrder, char[] preOrder) {
    Map<Character, Integer> inOrderNodePositionTable = buildPositionTable(inOrder);
    Node root = buildSubTree(inOrderNodePositionTable, inOrder, preOrder, 0, preOrder.length - 1, 0);
    return root;
  }

  private Node buildSubTree(Map<Character, Integer> inOrderNodePositionTable,
                            char[] inOrder,
                            char[] preOrder,
                            int leftBound,
                            int rightBound,
                            int preOrderRootPosition) {
    if (rightBound < leftBound) {
      return null;
    } else if (leftBound == rightBound) {
      return new Node(inOrder[leftBound]);
    }
    char rootValue = preOrder[preOrderRootPosition];
    Node root = new Node(rootValue);
    int rootPosition = inOrderNodePositionTable.get(rootValue);
    int leftEnd = rootPosition - 1;
    Node left = buildSubTree(inOrderNodePositionTable, inOrder, preOrder, leftBound, leftEnd, preOrderRootPosition + 1);
    root.left = left;
    int rightStart = rootPosition + 1;
    int leftTreeSize = rootPosition - leftBound;
    Node right = buildSubTree(inOrderNodePositionTable, inOrder, preOrder, rightStart, rightBound, preOrderRootPosition + leftTreeSize + 1);
    root.right = right;
    return root;
  }

  private Map<Character, Integer> buildPositionTable(char[] inOrder) {
    Map<Character, Integer> inOrderNodePositionTable = new HashMap<>();
    for (int i = 0; i < inOrder.length; i++) {
      inOrderNodePositionTable.put(inOrder[i], i);
    }
    return inOrderNodePositionTable;
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
    ReconstructABinaryTreeFromTraversalData reconstructABinaryTreeFromTraversalData = new ReconstructABinaryTreeFromTraversalData();
    char[] inOrder = new char[]{'B', 'A', 'C'};
    char[] preOrder = new char[]{'A', 'B', 'C'};

    inOrder = new char[]{'F', 'D', 'B', 'A', 'E', 'H', 'G', 'I', 'C'};
    preOrder = new char[]{'A', 'B', 'D', 'F', 'C', 'E', 'G', 'H', 'I'};
    inOrder = new char[]{'B', 'A'};
    preOrder = new char[]{'A', 'B'};
    Node root = reconstructABinaryTreeFromTraversalData.constructTree(inOrder, preOrder);
    System.out.println("Done");
  }
}
