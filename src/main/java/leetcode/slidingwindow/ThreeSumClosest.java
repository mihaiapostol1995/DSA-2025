package leetcode.slidingwindow;

import java.util.Arrays;

class ThreeSumClosest {
    public static void main(String[] args) {
        var t = new  ThreeSumClosest();
        t.threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int  left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    return target;
                } else if (sum < target) {
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    left++;
                } else {
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    right--;
                }
                closest = Math.abs(sum - target) < Math.abs(closest - target) ? sum: closest;
            }
        }
        return closest;
    }
}
