package leetcode;

import java.util.ArrayList;
import java.util.List;

class Permutations {

    public static void main(String[] args) {
        ArrayList<List<Integer>> permutations = new ArrayList<>();
        backTrack(new int[]{0, 1}, new ArrayList<>(), permutations);
    }

    static void backTrack(int[] nums, List<Integer> permutation, List<List<Integer>> permutations) {
        if (permutation.size() == nums.length) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!permutation.contains(nums[i])) {
                permutation.add(nums[i]);

                backTrack(nums, permutation, permutations);
                permutation.remove(permutation.size() - 1);
            }
        }
    }
}
