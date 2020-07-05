package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
  public List<Integer> inorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    Set<TreeNode> leftProcessNodes = new HashSet<>();
    List<Integer> list = new ArrayList<>();
    if (root != null) {
      stack.push(root);
    }
    while(!stack.isEmpty()) {
      TreeNode parent = stack.peek();
      if (!leftProcessNodes.contains(parent) && parent.left != null) {
        leftProcessNodes.add(parent);
        stack.push(parent.left);
        continue;
      }
      stack.pop();
      list.add(parent.val);
      if (parent.right != null) {
        stack.push(parent.right);
      }
    }
    return list;
  }
}
