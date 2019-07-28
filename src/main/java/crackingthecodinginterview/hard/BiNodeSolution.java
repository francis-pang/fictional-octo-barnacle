package crackingthecodinginterview.hard;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Consider a simple data structure called BiNodeSolution, which has pointers to two other nodes.
 * The data structure BiNodeSolution could be used to represent both a binary tree (where node1 is the left node and node2 is
 * the right node) or a doubly linked list (where node1 is the previous node and node2 is the next node). Implement a
 * method to convert a binary search tree (implemented with BiNodeSolution) into a doubly linked list. The values should be kept
 * in order and the operation should be performed in place (that is, on the original data structure).
 */
public class BiNodeSolution {
    public void convertToLinkedListFrom(BiNode root) {
        convertBinarySearchTree(root);
    }

    private RangeNode convertBinarySearchTree(BiNode node) {
        if (node == null) {
            return null;
        }
        BiNode smallerNode = node.node1;
        RangeNode smallerRangeNode = convertBinarySearchTree(smallerNode);
        RangeNode currentRangeNode = new RangeNode(null, null);
        if (smallerRangeNode == null) {
            currentRangeNode.min = node;
            node.node1 = null;
        } else {
            node.node1 = smallerRangeNode.max;
            smallerRangeNode.max.node2 = node;
            currentRangeNode.min = smallerRangeNode.min;
        }
        BiNode biggerNode = node.node2;
        RangeNode biggerRangeNode = convertBinarySearchTree(biggerNode);
        if (biggerRangeNode == null) {
            currentRangeNode.max = node;
            node.node2 = null;
        } else {
            node.node2 = biggerRangeNode.min;
            biggerRangeNode.min.node1 = node;
            currentRangeNode.max = biggerRangeNode.max;
        }
        return currentRangeNode;
    }

    public static class BiNode {
        public BiNode node1, node2;
        public int data;

        public BiNode(BiNode node1, BiNode node2, int data) {
            this.node1 = node1;
            this.node2 = node2;
            this.data = data;
        }

        public BiNode() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BiNode)) return false;
            BiNode biNode = (BiNode) o;
            return data == biNode.data &&
                    Objects.equals(node1, biNode.node1) &&
                    Objects.equals(node2, biNode.node2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(node1, node2, data);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", BiNode.class.getSimpleName() + "[", "]")
                    .add("node1=" + node1)
                    .add("node2=" + node2)
                    .add("data=" + data)
                    .toString();
        }
    }

    public class RangeNode {
        public BiNode min;
        public BiNode max;

        public RangeNode(BiNode min, BiNode max) {
            this.min = min;
            this.max = max;
        }
    }
}
