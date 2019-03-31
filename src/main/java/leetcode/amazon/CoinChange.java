package leetcode.amazon;

import java.util.Arrays;

public class CoinChange {
    int[] minimumCoinFromThisAmount;

    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        minimumCoinFromThisAmount = new int[amount + 1];
        Arrays.fill(minimumCoinFromThisAmount, Integer.MAX_VALUE);
        // So the correct method is to use Dynamic Programming

        // We want to take the minimum of all the 3 options, and store a shortest path
        if (minimumCoinFromThisAmount[amount] != Integer.MAX_VALUE) {
            return minimumCoinFromThisAmount[amount];
        }
        for (int coinDenomination : coins) {
            // System.out.println("Computing remaining amount of " + amount + " after deducting from " + coinDenomination);
            if (amount == coinDenomination) {
                minimumCoinFromThisAmount[amount] = 1;
                break;
            } else if (amount - coinDenomination > 0) {
                minimumCoinFromThisAmount[amount] = Math.min(minimumCoinFromThisAmount[amount], coinChange(coins, amount - coinDenomination) + 1);
            }
        }
//        minimumCoinFromThisAmount[amount] = (minimumCoinFromThisAmount[amount] == Integer.MAX_VALUE)
//                ? -1
//                : minimumCoinFromThisAmount[amount];
        System.out.println("Minimum coins for amount of " + amount + " = " + minimumCoinFromThisAmount[amount]);
        return minimumCoinFromThisAmount[amount];
    }

    public static void main(String[] args) {
        int[] coins = {186, 419, 83, 408};
        int amount = 6249;
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(coins, amount));
    }
}