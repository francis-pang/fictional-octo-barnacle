package crackingthecodinginterview.sortingandsearching;

public class RankfromStream {
  private TreeNode root;

  public void track(int x) {
    if (root == null) {
      root = new TreeNode(x);
    }
    root.insert(x);
  }

  public int getRankOfNumber(int x) {
    return searchWithAccumulator(root, x, 0);
  }

  private int searchWithAccumulator(TreeNode node, int value, int accumulator) {
    if (node == null) {
      return -1;
    }
    if (node.value == value) {
      return node.leftSubTreeSize + accumulator;
    } else if (node.value < value) {
      return searchWithAccumulator(node.right, value, node.leftSubTreeSize + 1);
    } else {
      return searchWithAccumulator(node.left, value, accumulator);
    }
  }

  class TreeNode {
    public int leftSubTreeSize;
    public int rightSubTreeSize;
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
      this.value = value;
      leftSubTreeSize = 0;
      rightSubTreeSize = 0;
    }

    public int insert(int value) {
      if (value <= this.value) {
        if (left == null) {
          left = new TreeNode(value);
          leftSubTreeSize = 1;
        } else {
          leftSubTreeSize = left.insert(value);
        }
      } else {
        if (right == null) {
          right = new TreeNode(value);
          rightSubTreeSize = 1;
        } else {
          rightSubTreeSize = right.insert(value);
        }
      }
      return leftSubTreeSize + rightSubTreeSize + 1;
    }
  }

  public static void main(String[] args) {
    RankfromStream rankfromStream = new RankfromStream();
    rankfromStream.track(3);
    rankfromStream.track(1);
    rankfromStream.track(1);
    rankfromStream.track(1);
    rankfromStream.track(2);
    System.out.println(rankfromStream.getRankOfNumber(1));
  }
}
