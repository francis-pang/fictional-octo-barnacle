package leetcode.amazon;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

class MedianFinder {
    /*
     * Need to maintain a balanced binary search tree, so the root of the node is either the median node, or the left
     * side of
     * the median node.
     * In this case, every insertion will be O(log(n)), because re-balancing the tree
     * Retrieval will be O(1).
     */

    // Attribute
    private Node root = null;

    /** initialize your data structure here. */
    public MedianFinder() {
        root = null;
    }

    public void addNum(int num) {
        // Insertion
        if (root == null) {
            root = new Node(num);
        } else {
           root.insert(num);
        }

        // Balancing
        this.root = balanceTree(root);
        System.out.println(root.toString());
    }


    public double findMedian() {
        if (root.size == 0) {
            return 0;
        }
        if (root.size % 2 == 0) {
            int nextNumber = 0;
            if (root.leftChild == null) {
                nextNumber = root.rightChild.value;
            } else if (root.rightChild == null) {
                nextNumber = root.leftChild.value;
            } else if (root.leftChild.size > root.rightChild.size) {
                nextNumber = root.leftChild.getBiggest();
            } else {
                nextNumber = root.rightChild.getSmallest();
            }
            return (double)(root.value + nextNumber) / 2;
        } else {
            return root.value;
        }
    }

    private Node balanceTree(Node tree) {
        if (tree.leftChild == null && tree.rightChild == null) {
            return tree;
        }
        if (StrictMath.ceil(Math.log(tree.size)) < tree.height) {
            // In-order traversal
            int[] nodeValues = extractNodeValuesFrom(tree);
            tree = buildNewBalancedBinaryTree(nodeValues, 0, nodeValues.length - 1);
        }
        return tree;
    }

    private Node buildNewBalancedBinaryTree(int[] nodeVales, int start, int end) {
        if (start > end) {
            return null;
        }
        int midPoint = (start + end) / 2;
        Node root = new Node(nodeVales[midPoint]);
        root.leftChild = buildNewBalancedBinaryTree(nodeVales, start, midPoint - 1);
        if (root.leftChild != null) root.leftChild.setSize();
        root.rightChild = buildNewBalancedBinaryTree(nodeVales, midPoint + 1, end);
        if (root.rightChild != null) root.rightChild.setSize();
        root.height = Math.max((root.leftChild == null) ? 0 : root.leftChild.height,
                (root.rightChild == null) ? 0 : root.rightChild.height) + 1;
        root.setSize();
        return root;
    }

    private int[] extractNodeValuesFrom(Node tree) {
        // In-order traversal
        if (tree == null) {
            return new int[]{};
        }
        int[] nodeValues = extractNodeValuesFrom(tree.leftChild);
        nodeValues = Arrays.copyOf(nodeValues, nodeValues.length + 1);
        nodeValues[nodeValues.length - 1] = tree.value;
        int[] rightChildVales = extractNodeValuesFrom(tree.rightChild);
        nodeValues = Arrays.copyOf(nodeValues, nodeValues.length + rightChildVales.length);
        System.arraycopy(rightChildVales, 0, nodeValues, nodeValues.length - rightChildVales.length, rightChildVales.length);
        return nodeValues;
    }

    class Node {
        public int value;
        public Node leftChild;
        public Node rightChild;
        public int size;
        public int height;

        public Node() {
        }

        public Node(int value) {
            this.size = 1;
            this.value = value;
            this.height = 1;
            this.leftChild = null;
            this.rightChild = null;
        }

        public int setSize() {
            size = ((leftChild == null) ? 0 : leftChild.size) + ((rightChild == null) ? 0 : rightChild.size) + 1;
            return size;
        }

        // Return the height
        public int insert(int nodeValue) {
            size++;
            if (value > nodeValue) { // Insert at the left side
                if (leftChild == null) {
                    leftChild = new Node(nodeValue);
                    return 1;
                } else {
                    leftChild.height = Math.max(leftChild.height, leftChild.insert(nodeValue) + 1);
                }
            } else { // Insert at the right side
                if (rightChild == null) {
                    rightChild = new Node(nodeValue);
                    return 1;
                } else {
                    rightChild.height = Math.max(rightChild.height, rightChild.insert(nodeValue) +1 );
                }
            }
            height =
                    Math.max((leftChild == null) ? 0 : leftChild.height,
                            (rightChild == null) ? 0 : rightChild.height) + 1;
            return height;
        }

        public int getBiggest() {
            if (rightChild != null) {
                return rightChild.getBiggest();
            }
            // Right child is null
            return value;
        }

        public int getSmallest() {
            if (leftChild != null) {
                return leftChild.getSmallest();
            }
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return value == node.value &&
                    size == node.size &&
                    height == node.height &&
                    Objects.equals(leftChild, node.leftChild) &&
                    Objects.equals(rightChild, node.rightChild);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, leftChild, rightChild, size, height);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                    .add("value=" + value)
                    .add((leftChild == null )? "" : "leftChild=" + leftChild.toString())
                    .add((rightChild == null )? "" : "rightChild=" + rightChild.toString())
                    .add("size=" + size)
                    .add("height=" + height)
                    .toString();
        }
    }

    public static void main(String[] args) {
        //["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
        //[[],[1],[],[2],[],[3],[],[4],[],[5],[],[6],[],[7],[],[8],[],[9],[],[10],[]]
        MedianFinder medianFinder = new MedianFinder();
        for (int i = 1; i <= 10; i++) {
            medianFinder.addNum(i);
            System.out.println(medianFinder.findMedian());
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */