package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TwentyFourGame {

    public static void main(String[] args) {
        var t = new TwentyFourGame();
        t.judgePoint24(new int[]{1,3,4,6});
    }

    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (var card : cards) {
            nums.add((double)card);
        }
        return dfs(nums);
    }

    private boolean dfs(List<Double> nums) {
        if (nums.size() == 1) return Math.abs(nums.getFirst() - 24) < 1e-6;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i == j) continue;

                // next leftover numbers
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++) {
                    if (k != i && k != j) {
                        next.add(nums.get(k));
                    }
                }

                double a = nums.get(i), b = nums.get(j);
                double[] operations = new double[]{a - b, b - a, a * b, a + b};
                if (a != 0) operations = append(operations, b / a);
                if (b != 0) operations = append(operations, a / b);

                for (int k = 0; k < operations.length; k++) {
                    next.add(operations[k]);
                    if (dfs(next)) {
                        return true;
                    }
                    next.removeLast();
                }
            }
        }

        return false;
    }

    private double[] append(double[] operations, double val) {
        double[] res = Arrays.copyOf(operations, operations.length+1);
        res[operations.length] = val;
        return res;
    }
}
