package leetcode.array;

class MaximumProductSubarray {

    public static void main(String[] args) {
        maxProduct(new int[]{2,3,-2,4});
    }

    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = 1;
        int min = 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                var currentMin = min;
                var currentMax = max;
                min = Math.min(currentMin, currentMax * nums[i]);
                max = Math.max(currentMin * nums[i], currentMax);
            } else {
                max = Math.max(max * nums[i], nums[i]);
                min = Math.min(min, min * nums[i]);
            }
        }

        return max;
    }
}
