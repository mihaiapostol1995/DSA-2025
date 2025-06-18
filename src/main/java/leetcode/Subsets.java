package leetcode;

import java.util.ArrayList;
import java.util.List;

class Subsets {

    public static void main(String[] args) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        subsets(new int[]{1, 2, 3}, 0, new ArrayList<>(), subsets);
        System.out.println(subsets);
    }

    public static void subsets(int[] nums, int index, List<Integer> current, List<List<Integer>> subsets) {
        if (current.size() == nums.length || index == nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);

            subsets.add(new ArrayList<>(current));
            subsets(nums, i + 1, current, subsets);

            current.remove(current.size() - 1);
        }
    }
}
