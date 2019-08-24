package crackingthecodinginterview.hard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BiNodeSolutionTest {
    private static BiNodeSolution biNodeSolution;

    @BeforeAll
    static void setUpOnce() {
        biNodeSolution = new BiNodeSolution();
    }

    @Test
    void convertToLinkedListFrom_null() {
        BiNodeSolution.BiNode root = null;
        biNodeSolution.convertToLinkedListFrom(root);
        assertNull(root);
    }

    @Test
    void convertToLinkedListFrom_singleNode() {
        BiNodeSolution.BiNode root = new BiNodeSolution.BiNode(null, null, 6);
        biNodeSolution.convertToLinkedListFrom(root);
        assertNull(root.node1);
        assertNull(root.node2);
    }

    @Test
    void convertToLinkedListFrom_oneLayerNode() {
        BiNodeSolution.BiNode root = new BiNodeSolution.BiNode(null, null, 6);
        BiNodeSolution.BiNode leftNode = new BiNodeSolution.BiNode(null, null, 5);
        BiNodeSolution.BiNode rightNode = new BiNodeSolution.BiNode(null, null, 7);
        root.node1 = leftNode;
        root.node2 = rightNode;

        biNodeSolution.convertToLinkedListFrom(root);
        assertEquals(leftNode, root.node1);
        assertEquals(rightNode, root.node2);
    }

    @Test
    void convertToLinkedListFrom_twoLayerNode() {
        BiNodeSolution.BiNode root = new BiNodeSolution.BiNode(null, null, 6);
        BiNodeSolution.BiNode node1 = new BiNodeSolution.BiNode(null, null, 1);
        BiNodeSolution.BiNode node2 = new BiNodeSolution.BiNode(null, null, 2);
        BiNodeSolution.BiNode node3 = new BiNodeSolution.BiNode(null, null, 3);
        BiNodeSolution.BiNode node4 = new BiNodeSolution.BiNode(null, null, 4);
        BiNodeSolution.BiNode node5 = new BiNodeSolution.BiNode(null, null, 5);
        BiNodeSolution.BiNode node7 = new BiNodeSolution.BiNode(null, null, 7);
        BiNodeSolution.BiNode node8 = new BiNodeSolution.BiNode(null, null, 8);
        BiNodeSolution.BiNode node9 = new BiNodeSolution.BiNode(null, null, 9);
        root.node1 = node4;
        root.node2 = node8;
        node2.node1 = node1;
        node2.node2 = node3;
        node4.node1 = node2;
        node4.node2 = node5;
        node8.node1 = node7;
        node8.node2 = node9;

        biNodeSolution.convertToLinkedListFrom(root);
        assertNull(node1.node1);
        assertEquals(node2, node1.node2);
        assertEquals(node1, node2.node1);
        assertEquals(node3, node2.node2);
        assertEquals(node2, node3.node1);
        assertEquals(node4, node3.node2);
        assertEquals(node3, node4.node1);
        assertEquals(node5, node4.node2);
        assertEquals(node4, node5.node1);
        assertEquals(root, node5.node2);
        assertEquals(node5, root.node1);
        assertEquals(node7, root.node2);
        assertNull(node9.node2);
    }
}