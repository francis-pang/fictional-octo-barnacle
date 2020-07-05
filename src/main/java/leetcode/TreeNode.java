package leetcode;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Definition for a binary tree node.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode {
  int val;
  public TreeNode left;
  public TreeNode right;

  TreeNode() {
  }

  public int getVal() {
    return val;
  }

  public TreeNode getLeft() {
    return left;
  }

  public TreeNode getRight() {
    return right;
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    try {
      return new com.fasterxml.jackson.databind.ObjectMapper()
          .writerWithDefaultPrettyPrinter()
          .writeValueAsString(this);
    } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
}