package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PerfectSquares {
  public int numSquares(int num) {
    List<Integer> pSquares = findAllPerfectSq(num);
    int[] memo = new int[num + 1];
    int answer = minSqSum(pSquares, num, memo);
    return answer;
  }

  private int minSqSum(List<Integer> pSq, int num, int[] memo) {
    if (memo[num] != 0) {
      return memo[num];
    }
    int minFound = Integer.MAX_VALUE;
    for (Integer square : pSq) {
      int remaining = num - square;
      if (remaining < 0) {
        continue;
      } else if (remaining == 0) {
        minFound = 1;
        memo[num] = 1;
        continue;
      } else {
        int minNoSum = minSqSum(pSq, remaining, memo) + 1;
        if (minFound > minNoSum) {
          minFound = minNoSum;
        }
      }
    }
    memo[num] = minFound;
    return minFound;
  }

  private List<Integer> findAllPerfectSq(int num) {
    double count = 1;
    List<Integer> list = new ArrayList<>();
    int square;
    do {
      square = (int) Math.pow(count, 2);
      list.add(square);
      count++;
    } while (square <= num);
    list.remove(list.size() - 1);
    return list;
  }
}
