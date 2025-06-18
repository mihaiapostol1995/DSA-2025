package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SubsetsWithDuplicates {

    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        int[] ints = {1, 2, 2};
        Arrays.sort(ints);
        subsetsWithDupFast(ints, result, new ArrayList<>(), 0);
        System.out.println(result);
    }

    static void subsetsWithDup(int[] nums, List<List<Integer>> lists, List<Integer> subset, int start) {
        if (start == nums.length) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
//            if (!lists.contains(subset)) {
                lists.add(new ArrayList<>(subset));
//            }
            subsetsWithDup(nums, lists, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }

    static void subsetsWithDupFast(int[] nums, List<List<Integer>> lists, List<Integer> subset, int start) {
        if (start == nums.length) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            subset.add(nums[i]);
            lists.add(new ArrayList<>(subset));
            subsetsWithDupFast(nums, lists, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }
}
