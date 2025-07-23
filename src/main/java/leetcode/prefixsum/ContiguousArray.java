package leetcode.prefixsum;

import java.util.HashMap;
import java.util.Map;

class ContiguousArray {
    public static void main(String[] args) {
        var c  = new ContiguousArray();
        c.findMaxLength(new int[]{0,1,1,1,1,1,0,0,0});
    }

    public int findMaxLength(int[] nums) {
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;

        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);

        int count = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            var num = nums[i];
            if (num == 0) {
                sum[i + 1] = sum[i] - 1;
            } else {
                sum[i + 1] = sum[i] + 1;
            }

            if (sumToIndex.containsKey(sum[i + 1])) {
                count = Math.max(count, i - sumToIndex.get(sum[i + 1]));
            } else {
                sumToIndex.put(sum[i + 1], i);
            }
        }
        return count;
    }
}
