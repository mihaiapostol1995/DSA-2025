package leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ThreeSum {

    public static void main(String[] args) {
        // -1,0,1,2,-1,-4
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));

        threeSumInterview(new int[]{-2,0,1,1,2});

        // second alternative: SORT the array, FIX one number, then work with 2 POINTERS on the rest
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        // num[i] + num[j] + x = 0
        // num[i] + num[j] = -x
        // -num[i] + -num[j] = x
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int number = nums[i];
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                int remaining = -nums[j] - number;
                if (map.containsKey(remaining)) {
                    int[] triplet = new int[]{nums[i], nums[j], remaining};
                    Arrays.sort(triplet);
                    result.add(List.of(triplet[0], triplet[1], triplet[2]));
                }
                map.put(nums[j], j);
            }
        }
        return new ArrayList<>(result);
    }

    public static List<List<Integer>> threeSumInterview(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int  left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    res.add(List.of(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}
