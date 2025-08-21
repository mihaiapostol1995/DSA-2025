package leetcode.search;

class FirstAndLastElementInSortedArray {
    public static void main(String[] args) {
        searchRange(new int[]{2,2}, 2);
    }

    public static int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }

        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    static int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int found = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target == nums[mid]) {
                found = mid;
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return found;
    }

    static int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int found = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target == nums[mid]) {
                found = mid;
                left = mid + 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return found;
    }
}
