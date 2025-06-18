package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, List<Integer>> valueToIndexList = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            valueToIndexList.computeIfAbsent(nums[i], k -> new LinkedList<>()).add(i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (valueToIndexList.containsKey(complement)) {
                List<Integer> indices = valueToIndexList.get(complement);
                for (int index : indices) {
                    if (index != i) {
                        return new int[]{i, index};
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }
}
