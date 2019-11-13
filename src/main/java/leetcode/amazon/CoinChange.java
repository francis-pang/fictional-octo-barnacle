package leetcode.amazon;


public class CoinChange {
  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] coins = {1, 2, 5};
    System.out.println(solution.coinChange(coins, 10));
  }

  static class Solution {
    private int[] smallestCoinsForAmount;

    public int coinChange(int[] coins, int amount) {
      if (amount <= 0) {
        return 0;
      }
      smallestCoinsForAmount = new int[amount + 1];
      int leastCoinUsed = leastCoinUsed(coins, amount);
      if (leastCoinUsed == Integer.MAX_VALUE) {
        return -1;
      } else {
        return leastCoinUsed;
      }

    }

    private int leastCoinUsed(int[] coins, int amount) {
      int leastNumberOfCoinForAmount = Integer.MAX_VALUE;
      for (int coinDenomination : coins) {
        int numberOfCoinsUsed = 0;
        if (amount == coinDenomination) {
          smallestCoinsForAmount[amount] = 1;
          return 1;
        }
        int amountLessCoin = amount - coinDenomination;
        if (amountLessCoin < 0) {
          continue;
        }

        if (smallestCoinsForAmount[amountLessCoin] != 0) {
          numberOfCoinsUsed = smallestCoinsForAmount[amountLessCoin];
        } else {
          numberOfCoinsUsed = leastCoinUsed(coins, amountLessCoin);
        }
        if (numberOfCoinsUsed != Integer.MAX_VALUE) {
          numberOfCoinsUsed++;
          leastNumberOfCoinForAmount = Math.min(leastNumberOfCoinForAmount, numberOfCoinsUsed);
        }
      }
      smallestCoinsForAmount[amount] = leastNumberOfCoinForAmount;
      return leastNumberOfCoinForAmount;
    }
  }
}