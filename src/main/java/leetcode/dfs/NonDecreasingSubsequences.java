package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class NonDecreasingSubsequences {
    public static void main(String[] args) {
        var nds = new NonDecreasingSubsequences();
        System.out.println(nds.findSubsequences(new int[]{4, 6, 7, 7}));
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        findSubsequencesHelper(nums, 0, current, result);
        return  result;
    }

    private void findSubsequencesHelper(int[] nums, int start, List<Integer> current,
                                         List<List<Integer>> result) {
        if (current.size() >= 2) {
            result.add(new ArrayList<>(current));
        }
        for (int i = start; i < nums.length; i++) {
            if (current.isEmpty() || nums[i] >= current.get(current.size() - 1)) {
                current.add(nums[i]);
                findSubsequencesHelper(nums, i + 1, current, result);
                current.remove(current.size() - 1);
            }
        }
    }
}
