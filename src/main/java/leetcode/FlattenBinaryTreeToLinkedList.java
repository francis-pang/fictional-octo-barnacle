package leetcode;

public class FlattenBinaryTreeToLinkedList {
  public static void main(String[] args) {
    TreeNode root = TreeNodeCreator.createTreeNode("[1,2,5,3,4,null,6]");
    FlattenBinaryTreeToLinkedList flattenBinaryTreeToLinkedList = new FlattenBinaryTreeToLinkedList();
    flattenBinaryTreeToLinkedList.flatten(root);
  }

  public void flatten(TreeNode root) {
    root = preOrderTraverse(root, null);
  }

  private TreeNode preOrderTraverse(TreeNode node, TreeNode predecessor) {
    TreeNode newNode = new TreeNode(node.val);
    if (predecessor != null) {
      predecessor.right = newNode;
    }
    TreeNode newLeftNode = null;
    if (node.left != null) {
      newLeftNode = preOrderTraverse(node.left, newNode);
    }
    if (node.right != null) {
      preOrderTraverse(node.right, newLeftNode);
    }
    return newNode;
  }
}
