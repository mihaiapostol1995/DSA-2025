package leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class CoinChange {

    public static void main(String[] args) {
        HashMap<Integer, Integer> memo = new HashMap<>();
//        int[] coins = {1,2,5};
//        int amount = 11;
//
//        coinChange(coins, amount, 0, 0, memo);

//        coinChange2(new int[]{2}, 3, memo);
        coinChangeDp(new int[]{1, 2, 5}, 11);
    }

    public static void coinChange(int[] coins, int amount, int runningSum, int coinCount, Map<Integer, Integer> memo) {
        if (runningSum == amount) {
            var numberOfCoins = memo.get(amount);
            if (numberOfCoins != null && coinCount < numberOfCoins) {
                memo.put(amount, coinCount);
            }
        }

        if (runningSum > amount) {
            return;
        }

        var numberOfCoins = memo.get(amount);
        if (numberOfCoins != null && coinCount >= numberOfCoins) {
            return;
        }

        coinCount++;
        for (int coin : coins) {
            int sum = runningSum + coin;
            if (sum > amount) {
                continue;
            }

            Integer i1 = memo.get(amount);
            if (i1 == null || coinCount < i1) {
                memo.put(sum, coinCount);
                coinChange(coins, amount, sum, coinCount, memo);
            }
        }
    }

    public static int coinChange2(int[] coins, int amount, Map<Integer, Integer> memo) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }

        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }

        int coinsReturned = Integer.MAX_VALUE;
        for (int coin : coins) {
            int newAmount = amount - coin;
            int coinsNeeded = coinChange2(coins, newAmount, memo);

            if (coinsNeeded != Integer.MAX_VALUE) {
                coinsReturned = Math.min(coinsReturned, coinsNeeded + 1);
            }
        }

        memo.put(amount, coinsReturned);
        return coinsReturned;
    }

    public static int coinChangeDp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
