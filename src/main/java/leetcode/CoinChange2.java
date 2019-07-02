package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CoinChange2 {
  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] coins = {1, 2, 5};
    System.out.println(solution.change(500, coins));
  }

  static class Solution {
    public int change(int amount, int[] coins) {
      if (amount == 0) {
        return 1;
      }
      if (coins.length == 0) {
        return 0;
      }
      Set<Map<Integer, Integer>> foundCoinsCombination = new HashSet<>();

      findCombinationOfCoinsForAmount(foundCoinsCombination, amount, coins, new HashMap<>());

      return foundCoinsCombination.size();
    }

    // Instead of storing the map itself, since it is storing by reference to the map and not the exact reference, I
    // will store the hashcode of it instead.
    private void findCombinationOfCoinsForAmount(Set<Map<Integer, Integer>> foundCoinsCombinations, int amount,
                                                 int[] coins, Map<Integer, Integer> coinsUsedThusFar) {
      // So the approach I want to take is similar as the one in the coin change 1, just a slight variation.
      // I will loop through all the coins

      for (int coin : coins) {
        // For each coins, I will do a check
        // If the value of the coin is bigger than the amount, then it means that there can a possibility of one more
        // coin, so let's repeat the process, but this time I need to note the coin that I have chosen previously
        if (amount >= coin) {
          coinsUsedThusFar.compute(coin, (key, value) -> (value == null) ? 1 : value + 1);
          // If the value of the coin is same as the coin, then viola, we have found ONE combination of coins that add up
          // to the amount. We will store the result
          if (amount == coin) {
            foundCoinsCombinations.add(deepCloneMap(coinsUsedThusFar));
          } else {
            findCombinationOfCoinsForAmount(foundCoinsCombinations, amount - coin, coins, coinsUsedThusFar);
          }
          // Revert the change done to the map, so that it can be reused
          coinsUsedThusFar.compute(coin, (key, value) -> (value == 1) ? null : value - 1);
        } else { // amount < coin
          // If the value of the coin is smaller than the amount, then discard
        }
      }
    }

    private Map<Integer, Integer> deepCloneMap(Map<Integer, Integer> clonee) {
      Map<Integer, Integer> clonedMap = new HashMap<>();
      clonee.forEach((key, value) -> {
        clonedMap.put(key, value);
      });
      return clonedMap;
    }
  }
}
