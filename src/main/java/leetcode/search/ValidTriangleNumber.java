package leetcode.search;

import java.util.Arrays;

class ValidTriangleNumber {
    public static void main(String[] args) {
        var v = new  ValidTriangleNumber();
        int i = v.triangleNumber(new int[]{2, 2, 3, 4});
        System.out.println(i);
    }

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);

        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int target = nums[i] + nums[j];
                int lastIndex = binarySearch(nums, target, j + 1);
                count += lastIndex - j - 1; // at must duplicates! 1, 2, 100 is not valid for example, 2,3,4,4 is for the duplicates of 4
            }
        }

        return count;
    }

    private int binarySearch(int[] nums, int target, int left) {
        // return the index
        // left is passed because you must NEVER advance more then left
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
