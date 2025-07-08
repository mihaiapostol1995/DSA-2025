package leetcode.dp;

import java.util.Arrays;

class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        var pr = new  PartitionEqualSubsetSum();
        pr.canPartition(new int[]{2,2,1,1});
    }

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        int target = sum / 2;

        Arrays.sort(nums);
        int currentSum = 0;

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (currentSum == target) {
                return true;
            } else if (currentSum > target) {
                currentSum -= nums[i];
            }
        }

        return false;
    }
}
