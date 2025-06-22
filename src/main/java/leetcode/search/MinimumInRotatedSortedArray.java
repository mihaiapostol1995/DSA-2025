package leetcode.search;

class MinimumInRotatedSortedArray {
    public static void main(String[] args) {
        findMin(new int[]{3,4,5,6,7,8,9,10,11,1,2});
    }

    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[right] < nums[mid]) {
                left = mid + 1;
            } else if (nums[mid] <= nums[right]) {
                right = mid;
            }
        }

        return nums[left];
    }
}
