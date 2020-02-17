package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenerateParentheses {
  public List<String> generateParenthesis(int n) {
    Map<Integer, Set<String>> memoTable = new HashMap<>();
    Set<String> single = new HashSet<>();
    single.add("()");
    memoTable.put(1, single);
    Set<String> parenthesesSet = recursiveGenerateParentheses(n, memoTable);
    return new ArrayList<>(parenthesesSet);
  }

  private Set<String> recursiveGenerateParentheses(int n, Map<Integer, Set<String>> memoTable) {
    if (memoTable.containsKey(n)) {
      return memoTable.get(n);
    }
    Set<String> parenthesesSet = new HashSet<>();
    for (int i = 1; i < n; i++) {
      int leftLength = i;
      Set<String> leftParentheses = recursiveGenerateParentheses(leftLength, memoTable);
      int rightLength = n - i;
      Set<String> rightParentheses = recursiveGenerateParentheses(rightLength, memoTable);
      for (String left : leftParentheses) {
        rightParentheses.forEach(right -> parenthesesSet.add(left + right));
      }
    }
    Set<String> oneSmallerSet = recursiveGenerateParentheses(n - 1, memoTable);
    oneSmallerSet.forEach(e -> parenthesesSet.add("(" + e + ")"));
    memoTable.put(n, parenthesesSet);
    return parenthesesSet;
  }

  private static void printList(List<String> list) {
    System.out.println("Total=" + list.size());
    list.forEach(e -> System.out.printf(e + ","));
    System.out.println();
  }

  public static void main(String[] args) {
    GenerateParentheses generateParentheses = new GenerateParentheses();
    List<String> actual = generateParentheses.generateParenthesis(6);
    printList(actual);
  }
}
