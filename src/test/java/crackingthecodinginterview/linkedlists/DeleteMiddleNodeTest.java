package crackingthecodinginterview.linkedlists;

import crackingthecodinginterview.linkedlists.DeleteMiddleNode.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteMiddleNodeTest {
  private static DeleteMiddleNode deleteMiddleNode;

  @BeforeAll
  static void setUpOnce() {
    deleteMiddleNode = new DeleteMiddleNode();
  }

  @Test
  void deleteMidof3NodesList() {
    // Form a new linked list with element 1,3,5
    SinglyLinkedList testLinkedList = new SinglyLinkedList();
    testLinkedList.add(1);
    testLinkedList.add(3);
    testLinkedList.add(5);

    // Get the 2nd node
    DeleteMiddleNode.Node root = testLinkedList.getFirst();
    Node middleNode = root.getNext();
    deleteMiddleNode.deleteMiddleNode(middleNode);

    // Verification
    Node node = testLinkedList.getFirst();
    assertEquals(1, (int) node.getElement());
    node = node.getNext();
    assertEquals(5, (int) node.getElement());
    node = node.getNext();
    assertNull(node);
  }

  @Test
  void deleteMidOf4NodesList() {
    // Form a new linked list with element 1,3,5,7
    SinglyLinkedList testLinkedList = new SinglyLinkedList();
    testLinkedList.add(1);
    testLinkedList.add(3);
    testLinkedList.add(5);
    testLinkedList.add(7);

    // Get the 3rd node
    Node root = testLinkedList.getFirst();
    Node secondNode = root.getNext();
    Node thirdNode = secondNode.getNext();
    deleteMiddleNode.deleteMiddleNode(thirdNode);

    // Verification
    Node node = testLinkedList.getFirst();
    assertEquals(1, (int) node.getElement());
    node = node.getNext();
    assertEquals(3, (int) node.getElement());
    node = node.getNext();
    assertEquals(7, (int) node.getElement());
    node = node.getNext();
    assertNull(node);
  }

  class SinglyLinkedList {
    Node head;
    Node last;

    public void add(Integer element) {
      if(head == null) {
        head = new Node(element, last);
      } else if(last == null){
        last = new Node(element, null);
        head.setNext(last);
      } else {
        Node newLast = new Node(element, null);
        last.setNext(newLast);
        last = newLast;
      }
    }

    public Node getFirst() {
      return head;
    }
  }
}