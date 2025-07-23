package leetcode.search;

class SingleElementInASortedArray {
    public static void main(String[] args) {
        var s = new  SingleElementInASortedArray();
        int[] nums = {3, 3, 7, 7, 10, 11, 11};
//        int i = s.singleNonDuplicate(nums);
//        int i = s.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8});
        int i = s.singleNonDuplicate(new int[]{1,1,2});
        System.out.println(i);
    }

    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length - 1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == n) {
                return nums[mid];
            }
            if (0 == mid) {
                return nums[mid];
            }

            if (nums[mid] != nums[mid + 1]
                    && nums[mid] != nums[mid - 1]) {
                return nums[mid];
            } else if (nums[mid] == nums[mid - 1]
                    && (mid + 1) % 2 == 0) {
                // number is on the right
                left = mid + 1;
            } else if (nums[mid] == nums[mid - 1]
                    && (mid + 1) % 2 != 0) {
                // number is on the left
                right = mid - 1;

            } else if (nums[mid] == nums[mid + 1]
                    && (n - mid + 1) % 2 != 0) {
                left = mid + 1;
            } else if (nums[mid] == nums[mid + 1]
                    && (n - mid + 1) % 2 == 0) {
                right = mid - 1;
            }
        }
        return -1;
    }
}
