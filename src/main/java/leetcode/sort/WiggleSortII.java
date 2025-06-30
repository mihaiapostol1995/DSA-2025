package leetcode.sort;

import java.util.Arrays;

class WiggleSortII {
    public static void main(String[] args) {
        WiggleSortII wiggleSortII = new WiggleSortII();
        wiggleSortII.wiggleSort(new int[]{1,5,1,1,6,4});
    }

    public void wiggleSort(int[] nums) {
        var copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);

        int firstHalfSize = (nums.length + 1) / 2; // SMART! +1 to avoid the issue with ODD numbers
        int[] firstSmallerHalf = Arrays.copyOfRange(copy, 0, firstHalfSize);
        int[] secondBiggerHalf = Arrays.copyOfRange(copy, firstHalfSize, nums.length);

        int secondHalfSize = nums.length - firstHalfSize;

        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                firstHalfSize--;
                nums[i] = firstSmallerHalf[firstHalfSize];
            } else {
                secondHalfSize--;
                nums[i] = secondBiggerHalf[secondHalfSize];
            }
        }
    }
}
