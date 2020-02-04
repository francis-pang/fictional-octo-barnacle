package leetcode;

public class UniqueBinarySearchTrees {
  private int[] memoTable;

  public int numTrees(int n) {
    memoTable = new int[n + 1];
    memoTable[0] = 1; // Because zero element, null node is also a valid node
    memoTable[1] = 1;
    return totalNumberOfPermutationForNode(n);
  }

  private int totalNumberOfPermutationForNode(int n) {
    if (memoTable[n] != 0) {
      return memoTable[n];
    }
    int total = 0;
    for (int i = 1; i <= n; i++) {
      total += populateTree(n, i);
    }
    memoTable[n] = total;
    return total;
  }

  private int populateTree(int totalLength, int index) {
    return totalNumberOfPermutationForNode(index - 1) * totalNumberOfPermutationForNode(totalLength - index);
  }

  public static void main(String[] args) {
    UniqueBinarySearchTrees uniqueBinarySearchTrees = new UniqueBinarySearchTrees();
    int answer = uniqueBinarySearchTrees.numTrees(9);
    System.out.println(answer);
  }
}
