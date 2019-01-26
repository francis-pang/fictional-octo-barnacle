package cracking_the_coding_interview.trees_graphs.minimal_tree;

public class MinimalTree {
    public BinaryTreeNode createBinarySearchTreeFromAscendingArray(int[] array) {
        if (array == null) {
            return new BinaryTreeNode();
        }
        /*
         * Calculate the height of the tree so that we can straightaway build to the correct height
         */
        double treeHeightDouble = Math.log(array.length) / Math.log(2);
        int treeHeightInt = 0;
        if (treeHeightDouble % 1 == 0) {
            treeHeightInt = (int) treeHeightDouble + 1;
        } else {
            treeHeightInt = (int) Math.ceil(treeHeightDouble);
        }
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = array[0];
        for (int i = 1; i < array.length; i++) {
            if (root.leftChild == null) {
                BinaryTreeNode newRoot = new BinaryTreeNode();
                newRoot.leftChild = root;
                newRoot.value = array[i];
                root = newRoot;
            } else if (root.rightChild == null) {
                BinaryTreeNode rightChild = new BinaryTreeNode();
                rightChild.value = array[i];
                root.rightChild = rightChild;
            } else { // Both child is occupied

            }
        }
        return root; // stub
    }

    public static void main(final String args[]) {
        double number = Math.log(8) / Math.log(2);
        System.out.println("Is this whole number: " + ((number % 1) == 0));
    }
}
