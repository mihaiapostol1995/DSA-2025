package leetcode.array;

import java.util.HashMap;

class SubarraySumEqualsK {
    public static void main(String[] args) {
        subarraySum(new int[]{1,1,1}, 2);
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        frequencyMap.put(0, 1);

        for (var num : nums) {
            sum += num;

            if (frequencyMap.containsKey(sum - k)) {
                count += frequencyMap.get(sum - k);
            }

            frequencyMap.put(sum, frequencyMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
