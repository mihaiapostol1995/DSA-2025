package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// this is another example of frequency arrays..
class KdiffPairsInArray {

    public static void main(String[] args) {

    }

    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        Set<Points> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            var first = nums[i];
            var search = nums[i] + k;
            if (Arrays.binarySearch(nums, i, nums.length, search) >= 0) {
                set.add(new Points(first, search));
            }
        }
        return set.size();
    }


    record Points(int first, int second) {}
}
