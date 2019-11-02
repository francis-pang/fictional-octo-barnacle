package crackingthecodinginterview.linkedlists;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteMiddleNodeTest {
  private static DeleteMiddleNode deleteMiddleNode;

  @BeforeAll
  static void setUpOnce() {
    deleteMiddleNode = new DeleteMiddleNode();
  }

  @Test
  void deleteMidof3NodesList() {
    // Form a new linked list with element 1,3,5
    DeleteMiddleNode.Node node1 = new DeleteMiddleNode.Node(1);
    DeleteMiddleNode.Node node2 = new DeleteMiddleNode.Node(3);
    DeleteMiddleNode.Node node3 = new DeleteMiddleNode.Node(5);
    node1.next = node2;
    node2.next = node3;
    // Get the 2nd node
    deleteMiddleNode.deleteMiddleNode(node1);

    // Verification
    assertEquals(3, node1.value);
    assertEquals(5, node1.next.value);
    assertEquals(0, node1.next.next.value);
  }

  @Test
  void deleteMidOf4NodesList() {
    // Form a new linked list with element 1,3,5,7
    DeleteMiddleNode.Node node1 = new DeleteMiddleNode.Node(1);
    DeleteMiddleNode.Node node2 = new DeleteMiddleNode.Node(3);
    DeleteMiddleNode.Node node3 = new DeleteMiddleNode.Node(5);
    DeleteMiddleNode.Node node4 = new DeleteMiddleNode.Node(7);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;

    // Get the 3rd node
    deleteMiddleNode.deleteMiddleNode(node2);

    // Verification
    assertEquals(5, node2.value);
    assertEquals(7, node2.next.value);
    assertEquals(0, node2.next.next.value);
  }
}
