package crackingthecodinginterview.treesandgraphs;

/**
 * Successor: Write an algorithm to find the "next" node (i .e., in-order successor) of a given node in a
 * binary search tree. You may assume that each node has a link to its parent.
 */
public class Successor {
  public BinaryTreeNode findSuccessorOf(BinaryTreeNode binaryTreeNode) {
    if (binaryTreeNode == null) {
      return null;
    }


    // Check is it a left or right child
    if (binaryTreeNode.parent == null) { //root
      // Return the leftmost leaf
      return findMostLeftLeafOfTree(binaryTreeNode.right);
      //Write the core algorithm first
    } else if (binaryTreeNode == binaryTreeNode.parent.left) { // non-root left child
      return binaryTreeNode.parent;
    } else if (binaryTreeNode == binaryTreeNode.parent.right) { // non-root right child
      if (binaryTreeNode.right != null) {
        return binaryTreeNode.right;
      } else {
        // Recursively look for the immediately left parent
        return findRightParent(binaryTreeNode.parent);
      }

    } else {
      // Throw error here
    }
    return null; //Stub
  }

  private BinaryTreeNode findMostLeftLeafOfTree(BinaryTreeNode root) {
    if (root == null) {
      return null;
    }
    BinaryTreeNode binaryTreeNode = root;
    BinaryTreeNode leftChild = binaryTreeNode.left;
    while (leftChild != null) {
      binaryTreeNode = leftChild;
      leftChild = binaryTreeNode.left;
    }
    return binaryTreeNode; // Return null if this root node is a single node tree
  }

  private BinaryTreeNode findRightParent(BinaryTreeNode binaryTreeNode) {
    if (binaryTreeNode == null) {
      return null;
    } else if (binaryTreeNode.parent == null) { // Take care of edge case of a rightmost leaf
      return null;
    } else if (binaryTreeNode == binaryTreeNode.parent.left) {
      return binaryTreeNode.parent;
    } else {
      return findRightParent(binaryTreeNode.parent);
    }
  }

  public static class BinaryTreeNode {
    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public BinaryTreeNode parent;

    public BinaryTreeNode(int value) {
      this.value = value;
    }

    public void setRelation(BinaryTreeNode parent, BinaryTreeNode left, BinaryTreeNode right) {
      this.parent = parent;
      this.left = left;
      this.right = right;
    }
  }
}
