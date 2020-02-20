package leetcode;

public class ConstructQuadTree {
  public Node construct(int[][] grid) {
    return build(grid, 0, 0, 0);
  }

  private Node build(int[][] grid, int depth, int row, int col) {
    int maxDepth = (int) (Math.log(grid.length) / Math.log(2));
    Node node = new Node(false, false);
    if (depth == maxDepth) {
      boolean val = grid[row][col] != 0;
      node = new Node(val, true);
      return node;
    }
    int addition = (int) Math.pow(2, maxDepth - 1 - depth);
    // Top left
    node.topLeft = build(grid, depth + 1, row, col);
    // Top Right
    node.topRight = build(grid, depth + 1, row, col + addition);
    // Bottom Left
    node.bottomLeft = build(grid, depth + 1, row + addition, col);
    // Bottom Right
    node.bottomRight = build(grid, depth + 1, row + addition, col + addition);

    if (node.topLeft.isLeaf && node.topRight.isLeaf && node.bottomLeft.isLeaf && node.bottomRight.isLeaf &&
        node.topLeft.val == node.topRight.val && node.topLeft.val == node.bottomLeft.val && node.topLeft.val == node.bottomRight.val) {
      node.val = node.topLeft.val;
      node.isLeaf = true;
      node.topLeft = null;
      node.topRight = null;
      node.bottomLeft = null;
      node.bottomRight = null;
    }
    return node;
  }

  static class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
      this.val = false;
      this.isLeaf = false;
      this.topLeft = null;
      this.topRight = null;
      this.bottomLeft = null;
      this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
      this.val = val;
      this.isLeaf = isLeaf;
      this.topLeft = null;
      this.topRight = null;
      this.bottomLeft = null;
      this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
      this.val = val;
      this.isLeaf = isLeaf;
      this.topLeft = topLeft;
      this.topRight = topRight;
      this.bottomLeft = bottomLeft;
      this.bottomRight = bottomRight;
    }
  }

  public static void main(String[] args) {
    ConstructQuadTree solution = new ConstructQuadTree();
    solution.construct(new int[][]{
        {1, 1, 1, 1, 0, 0, 0, 0},
        {1, 1, 1, 1, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 0, 0, 0},
        {1, 1, 1, 1, 0, 0, 0, 0},
        {1, 1, 1, 1, 0, 0, 0, 0},
        {1, 1, 1, 1, 0, 0, 0, 0}});
  }
}
