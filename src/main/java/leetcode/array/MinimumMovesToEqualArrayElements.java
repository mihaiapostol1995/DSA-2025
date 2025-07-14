package leetcode.array;

import java.util.Arrays;

class MinimumMovesToEqualArrayElements {
    public static void main(String[] args) {
        var m = new MinimumMovesToEqualArrayElements();
        m.minMoves(new int[]{1, 2, 3});
    }

    public int minMoves(int[] nums) {
        Arrays.sort(nums);

        int moves = 0;
        for (int i = 0; i < nums.length; i++) {
            moves = moves + nums[i] - nums[0];
        }
        return moves;
    }
}
