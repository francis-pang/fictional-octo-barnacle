package crackingthecodinginterview.treesandgraphs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidateBstTest {
  private static ValidateBst validateBst;

  @BeforeAll
  static void setUpOnce() {
    validateBst = new ValidateBst();
  }

  @Test
  void isTreeBinarySearchTree_null() {
    assertFalse(validateBst.isTreeBinarySearchTree(null));
  }

  @Test
  void isTreeBinarySearchTree_singleNode() {
    ValidateBst.BinaryTreeNode root = new ValidateBst.BinaryTreeNode(6);
    assertTrue(validateBst.isTreeBinarySearchTree(root));
  }

  @Test
  void isTreeBinarySearchTree_multiNodesFalse() {
    ValidateBst.BinaryTreeNode node10 = new ValidateBst.BinaryTreeNode(10);
    ValidateBst.BinaryTreeNode node9 = new ValidateBst.BinaryTreeNode(9);
    ValidateBst.BinaryTreeNode node12 = new ValidateBst.BinaryTreeNode(12);
    ValidateBst.BinaryTreeNode node7 = new ValidateBst.BinaryTreeNode(7);
    ValidateBst.BinaryTreeNode node11 = new ValidateBst.BinaryTreeNode(11);
    ValidateBst.BinaryTreeNode node13 = new ValidateBst.BinaryTreeNode(13);

    node10.left = node9;
    node10.right = node12;
    node9.left = node7;
    node9.right = node11;
    node12.left = node13;

    assertFalse(validateBst.isTreeBinarySearchTree(node10));
  }

  @Test
  void isTreeBinarySearchTree_multiNodesTrue() {
    ValidateBst.BinaryTreeNode node10 = new ValidateBst.BinaryTreeNode(10);
    ValidateBst.BinaryTreeNode node9 = new ValidateBst.BinaryTreeNode(9);
    ValidateBst.BinaryTreeNode node12 = new ValidateBst.BinaryTreeNode(12);
    ValidateBst.BinaryTreeNode node7 = new ValidateBst.BinaryTreeNode(7);
    ValidateBst.BinaryTreeNode node11 = new ValidateBst.BinaryTreeNode(11);
    ValidateBst.BinaryTreeNode node8 = new ValidateBst.BinaryTreeNode(8);

    node10.left = node8;
    node10.right = node12;
    node8.left = node7;
    node8.right = node9;
    node12.left = node11;

    assertTrue(validateBst.isTreeBinarySearchTree(node10));
  }

  @Test
  void isTreeBinarySearchTree_singleSidesNodesTrue() {
    ValidateBst.BinaryTreeNode node51 = new ValidateBst.BinaryTreeNode(5);
    ValidateBst.BinaryTreeNode node52 = new ValidateBst.BinaryTreeNode(5);
    ValidateBst.BinaryTreeNode node53 = new ValidateBst.BinaryTreeNode(5);
    ValidateBst.BinaryTreeNode node54 = new ValidateBst.BinaryTreeNode(5);

    node51.right = node52;
    node52.right = node53;
    node53.right = node54;

    assertTrue(validateBst.isTreeBinarySearchTree(node51));
  }

  @Test
  void isTreeBinarySearchTree_singleSidesNodesFalse() {
    ValidateBst.BinaryTreeNode node51 = new ValidateBst.BinaryTreeNode(5);
    ValidateBst.BinaryTreeNode node52 = new ValidateBst.BinaryTreeNode(5);
    ValidateBst.BinaryTreeNode node53 = new ValidateBst.BinaryTreeNode(5);
    ValidateBst.BinaryTreeNode node6 = new ValidateBst.BinaryTreeNode(6);

    node51.left = node52;
    node52.left = node53;
    node53.left = node6;

    assertFalse(validateBst.isTreeBinarySearchTree(node51));
  }

  @Test
  void isTreeBinarySearchTree_completeTreeTrue() {
    ValidateBst.BinaryTreeNode node10 = new ValidateBst.BinaryTreeNode(10);
    ValidateBst.BinaryTreeNode node9 = new ValidateBst.BinaryTreeNode(9);
    ValidateBst.BinaryTreeNode node12 = new ValidateBst.BinaryTreeNode(12);
    ValidateBst.BinaryTreeNode node7 = new ValidateBst.BinaryTreeNode(7);
    ValidateBst.BinaryTreeNode node11 = new ValidateBst.BinaryTreeNode(11);
    ValidateBst.BinaryTreeNode node8 = new ValidateBst.BinaryTreeNode(8);
    ValidateBst.BinaryTreeNode node13 = new ValidateBst.BinaryTreeNode(13);

    node10.left = node8;
    node10.right = node12;
    node8.left = node7;
    node8.right = node9;
    node12.left = node11;
    node12.right = node13;

    assertTrue(validateBst.isTreeBinarySearchTree(node10));
  }

  @Test
  void isTreeBinarySearchTree_completeTreeFalse() {
    ValidateBst.BinaryTreeNode node10 = new ValidateBst.BinaryTreeNode(10);
    ValidateBst.BinaryTreeNode node9 = new ValidateBst.BinaryTreeNode(9);
    ValidateBst.BinaryTreeNode node12 = new ValidateBst.BinaryTreeNode(12);
    ValidateBst.BinaryTreeNode node7 = new ValidateBst.BinaryTreeNode(7);
    ValidateBst.BinaryTreeNode node11Left = new ValidateBst.BinaryTreeNode(11);
    ValidateBst.BinaryTreeNode node11Right = new ValidateBst.BinaryTreeNode(11);
    ValidateBst.BinaryTreeNode node13 = new ValidateBst.BinaryTreeNode(13);
    ValidateBst.BinaryTreeNode node14 = new ValidateBst.BinaryTreeNode(14);

    node10.left = node9;
    node10.right = node12;
    node9.left = node7;
    node9.right = node11Left;
    node12.left = node11Right;
    node12.right = node14;

    assertFalse(validateBst.isTreeBinarySearchTree(node10));
  }

  @Test
  void isTreeBinarySearchTree_4LayerTree() {
    ValidateBst.BinaryTreeNode node2 = new ValidateBst.BinaryTreeNode(2);
    ValidateBst.BinaryTreeNode node4 = new ValidateBst.BinaryTreeNode(4);
    ValidateBst.BinaryTreeNode node6 = new ValidateBst.BinaryTreeNode(6);
    ValidateBst.BinaryTreeNode node8 = new ValidateBst.BinaryTreeNode(8);
    ValidateBst.BinaryTreeNode node10 = new ValidateBst.BinaryTreeNode(10);
    ValidateBst.BinaryTreeNode node12 = new ValidateBst.BinaryTreeNode(12);
    ValidateBst.BinaryTreeNode node14 = new ValidateBst.BinaryTreeNode(14);
    ValidateBst.BinaryTreeNode node16 = new ValidateBst.BinaryTreeNode(16);
    ValidateBst.BinaryTreeNode node17 = new ValidateBst.BinaryTreeNode(17);
    ValidateBst.BinaryTreeNode node18 = new ValidateBst.BinaryTreeNode(18);
    ValidateBst.BinaryTreeNode node20 = new ValidateBst.BinaryTreeNode(20);
    ValidateBst.BinaryTreeNode node22 = new ValidateBst.BinaryTreeNode(22);
    ValidateBst.BinaryTreeNode node24 = new ValidateBst.BinaryTreeNode(24);
    ValidateBst.BinaryTreeNode node26 = new ValidateBst.BinaryTreeNode(26);
    ValidateBst.BinaryTreeNode node28 = new ValidateBst.BinaryTreeNode(28);
    ValidateBst.BinaryTreeNode node30 = new ValidateBst.BinaryTreeNode(30);

    node16.left = node8;
    node16.right = node24;
    node8.left = node4;
    node8.right = node12;
    node4.left = node2;
    node4.right = node6;
    node12.left = node10;
    //node12.right = node14;
    node12.right = node17;

    node24.left = node20;
    node24.right = node28;
    node20.left = node18;
    node20.right = node22;
    node28.left = node26;
    node28.right = node30;

    //assertTrue(validateBst.isTreeBinarySearchTree(node16));
    assertFalse(validateBst.isTreeBinarySearchTree(node16));
  }
}