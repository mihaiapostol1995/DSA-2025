package leetcode.dfs.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindFrequentTreeSum {

    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        findFrequentTreeSum(map, root);

        List<Integer> result = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int value : map.values()) {
            if (value > max) {
                max = value;
            }
        }
        for (var entry : map.entrySet()) {
            if (entry.getValue() == max) {
                result.add(entry.getKey());
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private int findFrequentTreeSum(Map<Integer, Integer> frequency, TreeNode root) {
        if (root == null) return 0;

        int left = findFrequentTreeSum(frequency, root.left);
        int right = findFrequentTreeSum(frequency, root.right);
        int sum = left + right + root.val;
        if (sum != 0) {
            frequency.put(sum, frequency.getOrDefault(sum, 0) + 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        var f = new FindFrequentTreeSum();
    }
}
