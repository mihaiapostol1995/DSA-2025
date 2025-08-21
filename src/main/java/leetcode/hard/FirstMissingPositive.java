package leetcode.hard;

class FirstMissingPositive {
    public static void main(String[] args) {
        var f = new FirstMissingPositive();
//        f.firstMissingPositive(new int[]{3,1,2});
        f.firstMissingPositive(new int[]{1,1});
    }


    // core idea: nums[i] = i + 1
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // The correct check nums[nums[i]-1] != nums[i] ensures you only swap
            // if the target position doesn’t already contain the same value
            // 1, 1 -> infinite loop

            // simple example: 1, 2, 3

            // nums[i] - 1 = i

            // if we use if (not while) it will fail for this: 3, 1, 2 -> 2, 1, 3; 1,2,3
//            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
//                swap(nums, nums[i] - 1, i);
//            }
            while (nums[i] > 0 && nums[i] <= n && nums[i] - 1 != i) {
                swap(nums, nums[i] - 1, i);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
