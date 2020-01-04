package elementsofprogramminginterviews.binarytrees;

import java.util.ArrayList;

public class SumTheRootToLeafPathsInABinaryTree {
  private static boolean ONE_BIT = true;
  private static boolean ZERO_BIT = false;

  public int sumFromRoot(Node root) {
    return sumFromRoot(new ArrayList<Boolean>(), root);
  }

  private int sumFromRoot(ArrayList<Boolean> bits, Node node) {
    if (node.left == null && node.right == null) {
      bits.add(node.bit);
      int value = convertToInteger(bits);
      bits.remove(bits.size() - 1);
      return value;
    }
    int total = 0;
    bits.add(node.bit);
    if (node.left != null) {
      total += sumFromRoot(bits, node.left);
    }
    if (node.right != null) {
      total += sumFromRoot(bits, node.right);
    }
    bits.remove(bits.size() - 1);
    return total;
  }

  private int convertToInteger(ArrayList<Boolean> bits) {
    int value = 0;
    int bitLength = bits.size();
    for (int i = 0; i < bitLength; i++) {
      int power = bitLength - i - 1;
      boolean bit = bits.get(i);
      if (bit) {
        value += Math.pow(2, power);
      }
    }
    return value;
  }

  public class Node {
    public boolean bit;
    public Node left;
    public Node right;
  }
}
