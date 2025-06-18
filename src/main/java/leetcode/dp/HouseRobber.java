package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

class HouseRobber {

    public static void main(String[] args) {
//        rob(new int[]{1, 2, 3, 1});
//        rob(new int[]{2, 1, 1, 2});

        HashMap<Integer, Integer> map = new HashMap<>();
        robMemo(new int[]{2, 1, 1, 2}, 0, map);
    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[nums.length - 1];
    }

    public static int robMemo(int[] nums, int house, Map<Integer, Integer> memo) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        if (nums.length == 1) {
//            return nums[0];
//        }
        if (house >= nums.length) {
            return 0;
        }

        if (memo.containsKey(house)) {
            return memo.get(house);
        }

        int max = Math.max(robMemo(nums, house + 1, memo),
                    robMemo(nums, house + 2 , memo) + nums[house]);


        memo.put(house, max);
        return max;
    }
}
