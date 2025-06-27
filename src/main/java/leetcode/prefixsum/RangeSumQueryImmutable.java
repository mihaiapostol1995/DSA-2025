package leetcode.prefixsum;

import java.util.ArrayList;
import java.util.List;

public class RangeSumQueryImmutable {
    public static void main(String[] args) {
        var numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        numArray.sumRange(1, 2);
    }
}

class NumArray {

    List<Integer> sums = new ArrayList<>();

    public NumArray(int[] nums) {
        sums.add(0);
        for (int i = 1; i <= nums.length; i++) {
            var nextElement = sums.get(i - 1) + nums[i - 1];
            sums.add(nextElement);
        }
    }

    public int sumRange(int left, int right) {
        return sums.get(right + 1) - sums.get(left);
    }
}