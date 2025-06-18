package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CombinationSum {
    public static void main(String[] args) {
        var result = new ArrayList<List<Integer>>();
        Arrays.sort(new int[]{2,3,6,7});
        combinationSum(new int[]{2,3,6,7}, 7, 0, new ArrayList<>(), result);
        System.out.println(result);
    }

    public static void combinationSum(int[] candidates, int target, int index, List<Integer> currentCombination,
                               List<List<Integer>> result) {
        if (target == 0) {
            result.add(currentCombination);
            return;
        }
        if (index >= candidates.length) {
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            currentCombination.add(candidates[i]);
            combinationSum(candidates, target - candidates[i], i,
                    currentCombination, result);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
