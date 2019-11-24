package crackingthecodinginterview.treesandgraphs;

import java.util.ArrayList;
import java.util.List;

public class BstSequences {
  public List<int[]> computeAllBstSequences(Node root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<int[]> leftSubTreeCombination = computeAllBstSequences(root.left);
    List<int[]> rightSubTreeCombination = computeAllBstSequences(root.right);
    List<int[]> combinedList = new ArrayList<>();
    int leftSubTreeSize = leftSubTreeCombination.size();
    int rightSubTreeSize = rightSubTreeCombination.size();
    if (leftSubTreeSize == 0 && rightSubTreeSize == 0) {
      combinedList.add(merge(root.value, null, null));
      return combinedList;
    }
    if (leftSubTreeSize == 0) {
      rightSubTreeCombination.forEach(rightPass -> combinedList.add(merge(root.value, rightPass)));
    }
    if (rightSubTreeSize == 0) {
      leftSubTreeCombination.forEach(leftPass -> combinedList.add(merge(root.value, leftPass)));
    }
    leftSubTreeCombination.forEach(leftPass ->
        rightSubTreeCombination.forEach(rightPass -> {
          combinedList.add(merge(root.value, leftPass, rightPass));
          combinedList.add(merge(root.value, rightPass, leftPass));
        })
    );
    return combinedList;
  }

  private int[] merge(int value, int[] pass) {
    int[] mergedArray = new int[1 + pass.length];
    mergedArray[0] = value;
    int index = 0;
    for (int i = 0; i < pass.length; i++) {
      index++;
      mergedArray[index] = pass[i];
    }
    return mergedArray;
  }

  private int[] merge(int value, int[] leftPass, int[] rightPass) {
    if (leftPass == null && rightPass == null) {
      return new int[]{value};
    }
    int[] mergedArray = new int[1 + leftPass.length + rightPass.length];
    mergedArray[0] = value;
    int index = 0;
    for (int i = 0; i < leftPass.length; i++) {
      index++;
      mergedArray[index] = leftPass[i];
    }
    for (int i = 0; i < rightPass.length; i++) {
      index++;
      mergedArray[index] = rightPass[i];
    }
    return mergedArray;
  }

  public static class Node {
    public int value;
    public Node left;
    public Node right;
  }
}
