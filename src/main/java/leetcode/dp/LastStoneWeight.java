package leetcode.dp;

import java.util.Arrays;

class LastStoneWeight {

    public static void main(String[] args) {
        var l = new  LastStoneWeight();
        int i = l.lastStoneWeightII(new int[]{31,26,33,21,40});
        System.out.println(i);
    }

    public int lastStoneWeightII(int[] stones) {
        int total = Arrays.stream(stones).sum();

        boolean[] dp = new boolean[total];
        dp[0] = true;
        for (var num : stones) {
            for (int i = total - 1; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }

        int half = total / 2;
        for (int i = half; i >= 0; i--) {
            if (dp[i]) {
                return total - 2 * i;
            }
        }
        return -1;
    }
}
