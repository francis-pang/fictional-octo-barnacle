package interview.rakuten;

import java.util.HashSet;
import java.util.Set;

public class LongestDistinctPath {
  public int solution(Tree root) {
    return traverseForDistinctSet(root, new HashSet<>());
  }

  private int traverseForDistinctSet(Tree node, Set<Integer> visitedValues) {
    if (node == null) {
      return 0;
    }
    if (!visitedValues.add(node.x)) {
      return 0;
    }
    int leftSubTreeLength = traverseForDistinctSet(node.l, visitedValues);
    int rightSubTreeLength = traverseForDistinctSet(node.r, visitedValues);
    visitedValues.remove(node.x);
    return Math.max(leftSubTreeLength, rightSubTreeLength) + 1;
  }

  class Tree {
    public int x;
    public Tree l;
    public Tree r;
  }
}

