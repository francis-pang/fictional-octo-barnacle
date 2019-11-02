package crackingthecodinginterview.linkedlists;

public class DeleteMiddleNode {
  public void deleteMiddleNode(Node nodeToDelete) {
    // return when delN == null
    if (nodeToDelete == null) {
      return;
    }
    Node currentNode = nodeToDelete;
    while (currentNode.next != null) {
      currentNode.value = currentNode.next.value;
      currentNode = currentNode.next;
    }
    currentNode.value = 0;
  }

  public static class Node {
    public int value;
    public Node next;

    public Node(int value) {
      this.value = value;
    }
  }
}
