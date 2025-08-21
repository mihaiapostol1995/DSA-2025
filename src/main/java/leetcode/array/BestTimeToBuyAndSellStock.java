package leetcode.array;

class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        var b = new BestTimeToBuyAndSellStock();
        int i = b.maxProfit(new int[]{2, 4, 1});
        System.out.println(i);
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                // reset the stock
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }
}
