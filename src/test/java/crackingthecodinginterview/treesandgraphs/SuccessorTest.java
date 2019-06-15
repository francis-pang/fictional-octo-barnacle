package crackingthecodinginterview.treesandgraphs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SuccessorTest {
  private static Successor successor;

  @BeforeAll
  static void setUpOnce() {
    successor = new Successor();
  }

  /*
   * Plan for a few test case
   * 1. Null node
   * 2. Tree with only 1 node
   * 3. Left side tree, pass in root
   * 4. Left side tree, pass in middle node
   * 5. Left side tree, pass in leaf
   * 6. Right side tree, pass in root
   * 7. Right side tree, pass in non root
   * 8. Complete tree, pass in root
   * 9. Complete tree, pass in middle layer
   * 10. Complete tree, pass in leaf in middle
   * 11. Complete tree, pass in leaf in extreme left leaf
   * 12. Complete tree, pass in leaf in extreme right leaf
   */

  @Test
  void findSuccessorOf_nullNode() {
    assertNull(successor.findSuccessorOf(null));
  }

  @Test
  void findSuccessorOf_1NodeTree() {
    Successor.BinaryTreeNode root = new Successor.BinaryTreeNode(1);
    assertNull(successor.findSuccessorOf(root));
  }

  @Test
  void findSuccessorOf_leftSideTree() {
    Successor.BinaryTreeNode node2 = new Successor.BinaryTreeNode(2);
    Successor.BinaryTreeNode node4 = new Successor.BinaryTreeNode(4);
    Successor.BinaryTreeNode node6 = new Successor.BinaryTreeNode(6);
    Successor.BinaryTreeNode node8 = new Successor.BinaryTreeNode(8);

    node8.left = node6;
    node6.parent = node8;
    node6.left = node4;
    node4.parent = node6;
    node4.left = node2;
    node2.parent = node4;

    assertNull(successor.findSuccessorOf(node8));
    assertEquals(node8, successor.findSuccessorOf(node6));
    assertEquals(node6, successor.findSuccessorOf(node4));
    assertEquals(node4, successor.findSuccessorOf(node2));
  }

  @Test
  void findSuccessorOf_rightSideTree() {
    Successor.BinaryTreeNode node2 = new Successor.BinaryTreeNode(2);
    Successor.BinaryTreeNode node4 = new Successor.BinaryTreeNode(4);
    Successor.BinaryTreeNode node6 = new Successor.BinaryTreeNode(6);
    Successor.BinaryTreeNode node8 = new Successor.BinaryTreeNode(8);

    node2.right = node4;
    node4.parent = node2;

    node4.right = node6;
    node6.parent = node4;

    node6.right = node8;
    node8.parent = node6;

    assertEquals(node4, successor.findSuccessorOf(node2));
    assertEquals(node6, successor.findSuccessorOf(node4));
    assertNull(successor.findSuccessorOf(node8));
  }

  @Test
  void findSuccessorOf_CompleteTree() {
    Successor.BinaryTreeNode node2 = new Successor.BinaryTreeNode(2);
    Successor.BinaryTreeNode node4 = new Successor.BinaryTreeNode(4);
    Successor.BinaryTreeNode node6 = new Successor.BinaryTreeNode(6);
    Successor.BinaryTreeNode node8 = new Successor.BinaryTreeNode(8);
    Successor.BinaryTreeNode node10 = new Successor.BinaryTreeNode(10);
    Successor.BinaryTreeNode node12 = new Successor.BinaryTreeNode(12);
    Successor.BinaryTreeNode node14 = new Successor.BinaryTreeNode(14);
    Successor.BinaryTreeNode node16 = new Successor.BinaryTreeNode(16);
    Successor.BinaryTreeNode node18 = new Successor.BinaryTreeNode(18);
    Successor.BinaryTreeNode node20 = new Successor.BinaryTreeNode(20);
    Successor.BinaryTreeNode node22 = new Successor.BinaryTreeNode(22);
    Successor.BinaryTreeNode node24 = new Successor.BinaryTreeNode(24);
    Successor.BinaryTreeNode node26 = new Successor.BinaryTreeNode(26);
    Successor.BinaryTreeNode node28 = new Successor.BinaryTreeNode(28);
    Successor.BinaryTreeNode node30 = new Successor.BinaryTreeNode(30);

    node2.setRelation(node4, null, null);
    node4.setRelation(node8, node2, node6);
    node6.setRelation(node4, null, null);
    node8.setRelation(node16, node4, node12);
    node10.setRelation(node12, null, null);
    node12.setRelation(node8, node10, node14);
    node14.setRelation(node12, null, null);
    node16.setRelation(null, node8, node24);
    node18.setRelation(node20, null, null);
    node20.setRelation(node24, node18, node22);
    node22.setRelation(node20, null, null);
    node24.setRelation(node16, node20, node28);
    node26.setRelation(node28, null, null);
    node28.setRelation(node24, node26, node30);
    node30.setRelation(node28, null, null);

    assertEquals(node18, successor.findSuccessorOf(node16));
    assertEquals(node4, successor.findSuccessorOf(node2));
    assertEquals(node16, successor.findSuccessorOf(node14));
    assertEquals(node16, successor.findSuccessorOf(node14));
    assertEquals(node20, successor.findSuccessorOf(node18));
    assertEquals(node30, successor.findSuccessorOf(node28));
    assertNull(successor.findSuccessorOf(node30));
  }
}