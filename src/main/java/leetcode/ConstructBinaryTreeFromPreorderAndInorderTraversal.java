package leetcode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
  public static void main(String[] args) {
    ConstructBinaryTreeFromPreorderAndInorderTraversal constructBinaryTreeFromPreorderAndInorderTraversal =
        new ConstructBinaryTreeFromPreorderAndInorderTraversal();
    TreeNode root = constructBinaryTreeFromPreorderAndInorderTraversal.buildTree(new int[]{3,9,20,15,7}, new int[]{9,
        3,15,20,7});
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> nodesValueByPosition = new HashMap<>();
    for (int position = 0; position < inorder.length; position++) {
      int value = inorder[position];
      nodesValueByPosition.put(value, position);
    }
    TreeNode root = findParentOfSubTree(0, preorder, nodesValueByPosition, 0, inorder.length - 1);
    return root;
  }

  private TreeNode findParentOfSubTree(int parentPositionInPreOder,
                                       int[] preorder,
                                       Map<Integer, Integer> nodesValueByPosition,
                                       int startPosition, int endPosition) {
    int parentNodeValue = preorder[parentPositionInPreOder];
    int parentInOrderPosition = nodesValueByPosition.get(parentNodeValue);
    TreeNode parentNode = new TreeNode(parentNodeValue);

    TreeNode leftChild = null;
    int leftChildPosition = parentPositionInPreOder + 1;
    if (parentInOrderPosition != startPosition && leftChildPosition < preorder.length) {
      // Need to determine how to find the placeholder position
      leftChild = findParentOfSubTree(leftChildPosition, preorder, nodesValueByPosition, startPosition,
          parentInOrderPosition - 1);
    }
    parentNode.left = leftChild;

    // Find the first position of the right subtree in pre-order traversal
    int rightChildPosition = -1;
    for (int i = parentPositionInPreOder + 1; i < preorder.length && rightChildPosition == -1;
         i++) {
      int value = preorder[i];
      int inOrderPosition = nodesValueByPosition.get(value);
      if (inOrderPosition > parentInOrderPosition) {
        rightChildPosition = i;
      }
    }
    TreeNode rightChild = null;
    if (rightChildPosition != -1) {
      rightChild = findParentOfSubTree(rightChildPosition, preorder, nodesValueByPosition, parentInOrderPosition + 1,
          endPosition);
    }
    parentNode.right = rightChild;
    return parentNode;
  }
}