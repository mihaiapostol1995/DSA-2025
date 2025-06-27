package leetcode.math;

class UglyNumberII {
    public static void main(String[] args) {
        UglyNumberII uglyNumberII = new UglyNumberII();
        int i = uglyNumberII.nthUglyNumber(11);
        System.out.println(i);
    }

    // three pointers with dp...
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];

        dp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < n; i++) {
            int nextUglyI2 = dp[i2] * 2;
            int nextUglyI3 = dp[i3] * 3;
            int nextUglyI5 = dp[i5] * 5;

            int nextUgly = Math.min(nextUglyI2, Math.min(nextUglyI3, nextUglyI5));
            dp[i] = nextUgly;

            if (nextUgly == nextUglyI2) i2++;
            if (nextUgly == nextUglyI3) i3++;
            if (nextUgly == nextUglyI5) i5++;
        }

        return dp[n - 1];
    }
}
