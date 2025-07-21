package leetcode.dp;

public class PredictTheWinner {
    public static void main(String[] args) {
        var p = new PredictTheWinner();
//        boolean b = p.predictTheWinnerSlow(new int[]{1, 5, 233, 7});
        boolean b = p.predictTheWinner(new int[]{1, 5, 2});
        System.out.println(b);
    }

    public boolean predictTheWinnerSlow(int[] nums) {
       return helper(nums, 0, nums.length - 1) > 0;
    }

    public int helper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        return Math.max(nums[left] - helper(nums, left + 1, right),
                nums[right] - helper(nums, left, right - 1));
    }
    // this array stores the DIFFERENCES
    public boolean predictTheWinner(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            dp[i][i] = arr[i];
        }

        for (int len = 1; len < arr.length; len++)
            for (int i = 0; i < arr.length - len; i++) {
                int j = i + len;
                dp[i][j] = Math.max(arr[i] - dp[i + 1][j],
                        arr[j] - dp[i][j - 1]);
            }
        return dp[0][arr.length - 1] >= 0;
    }
}
