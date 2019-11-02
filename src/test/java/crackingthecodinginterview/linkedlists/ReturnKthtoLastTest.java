package crackingthecodinginterview.linkedlists;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturnKthtoLastTest {
  private static ReturnKthtoLast returnKthtoLast;
  private static ReturnKthtoLast.Node node1;
  private static ReturnKthtoLast.Node node2;
  private static ReturnKthtoLast.Node node3;
  private static ReturnKthtoLast.Node node4;
  private static ReturnKthtoLast.Node node5;
  private static ReturnKthtoLast.Node node6;
  private static ReturnKthtoLast.Node node7;
  private static ReturnKthtoLast.Node node8;
  private static ReturnKthtoLast.Node node9;
  private static ReturnKthtoLast.Node node10;
  private static ReturnKthtoLast.Node node11;

  @BeforeAll
  static void setUpOnce() {
    returnKthtoLast = new ReturnKthtoLast();
    node1 = new ReturnKthtoLast.Node(1);
    node2 = new ReturnKthtoLast.Node(2);
    node3 = new ReturnKthtoLast.Node(3);
    node4 = new ReturnKthtoLast.Node(4);
    node5 = new ReturnKthtoLast.Node(5);
    node6 = new ReturnKthtoLast.Node(6);
    node7 = new ReturnKthtoLast.Node(7);
    node8 = new ReturnKthtoLast.Node(8);
    node9 = new ReturnKthtoLast.Node(9);
    node10 = new ReturnKthtoLast.Node(10);
    node11 = new ReturnKthtoLast.Node(11);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;
    node5.next = node6;
    node6.next = node7;
    node7.next = node8;
    node8.next = node9;
    node9.next = node10;
    node10.next = node11;
  }

  @Test
  void testNormalCase() {
    assertEquals(node9, returnKthtoLast.getKToLastElement(node1, 3));
  }

  @Test
  void testFirstIsLastKCase() {
    assertEquals(node1, returnKthtoLast.getKToLastElement(node1, 11));
  }

  @Test
  void testLastCase() {
    assertEquals(node11, returnKthtoLast.getKToLastElement(node1, 1));
  }
}