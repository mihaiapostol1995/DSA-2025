package leetcode.prefixsum;

import java.util.Arrays;

class BestTimeToBuyAndSellStockUsingStrategy {

    public static void main(String[] args) {
        var b = new BestTimeToBuyAndSellStockUsingStrategy();
        long l = b.maxProfit(new int[]{4,2,8}, new int[]{-1, 0, 1}, 2);
        System.out.println(l);
    }

    // wrong approach

    public long maxProfit(int[] prices, int[] strategy, int k) {
        int profit = Integer.MIN_VALUE;
        int[] prefixSums = new int[prices.length];
        prefixSums[0] = prices[0] * strategy[0];

        for (int i = 1; i < prices.length; i++) {
            prefixSums[i] = prefixSums[i -1] + prices[i] * strategy[i];
        }
        profit = Math.max(profit, prefixSums[prices.length - 1]);

        int left = 0;
        int right = k;
        while (right <= strategy.length) {
            int[] newStrategy = Arrays.copyOf(strategy, strategy.length);

            for (int i = left; i < left + k / 2; i++) {
                newStrategy[i] = 0;
            }
            for (int i = left + k / 2; i < right; i++) {
                newStrategy[i] = 1;
            }

            int prefixProfit = 0;
            if (left > 0) {
                prefixProfit += prefixSums[left - 1];
            }
            if (right < strategy.length) {
                prefixProfit = prefixSums[right] - prefixSums[right - 1];
            }

            for (int i = left; i < right; i++) {
                prefixProfit += prices[i] * newStrategy[i];
            }

            profit = Math.max(profit, prefixProfit);
            System.out.println(prefixProfit);
            left++;
            right++;
        }
        return profit;
    }
}
