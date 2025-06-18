package leetcode.search;

class SearchInRotatedArray {
    public static void main(String[] args) {
        search2(8, new int[]{4,5,6,7,8,1,2,3});
    }

    static int search(int target, int[] nums) {
        int left = 0, right = nums.length - 1;
        int mid = left + (right - left) / 2;

        while (left <= right) {
            if (target == nums[mid]) {
                return mid;
            }

            if (target <= nums[mid] && target >= nums[left]) {
                right = mid - 1;
            } else if (target >= nums[mid] && target <= nums[right]) {
                left = mid + 1;
            } else if (nums[mid] >= target && nums[left] >= target) {
                left = mid + 1;
            } else if (nums[mid] <= target && nums[right] <= target) {
                right = mid - 1;
            }
            mid = left + (right - left) / 2;
        }

        return -1;
    }

    static int search2(int target, int[] nums) {
        var pivot = findPivot(nums);

        if (pivot == 0) {
            return binarySearch(0, nums.length - 1, target, nums);
        }

        if (target >= nums[pivot] && target <= nums[nums.length - 1]) {
            return binarySearch(pivot, nums.length - 1, target, nums);
        } else {
            return binarySearch(0, pivot, target, nums);
        }
    }

    private static int findPivot(int[] nums) {
        int left = 0, right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static int binarySearch(int start, int end, int target, int[] nums) {
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target == nums[mid]) {
                return mid;
            }

            if (target > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}
