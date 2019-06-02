package leetcode;

public class FactorialTrailingZeroes {
  class Solution {
    public int trailingZeroes(int n) {
      int numberOfZero = 0;
      while (n >= 5) {
        numberOfZero += n / 5;
        n = n / 5;
      }
      return numberOfZero;
    }
  }
}
