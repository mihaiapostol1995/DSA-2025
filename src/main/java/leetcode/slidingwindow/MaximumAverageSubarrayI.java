package leetcode.slidingwindow;

class MaximumAverageSubarrayI {
    public static void main(String[] args) {
        var m = new MaximumAverageSubarrayI();
        double maxAverage = m.findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4);
        System.out.println(maxAverage);
    }

    public double findMaxAverage(int[] nums, int k) {
        int windowSum = 0;
        double maxSum = 0;

        // Compute sum of first window
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        maxSum = (double) windowSum / k;

        // Slide the window
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, (double) windowSum / k);
        }

        return maxSum;
    }
}
