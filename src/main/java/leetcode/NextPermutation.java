package leetcode;

class NextPermutation {
    // GOOD example:
    // 9  2  5 4 3 2 1
    public static void main(String[] args) {
        nextPermutation(new int[]{2, 3, 1, 3, 3});
    }

    static public void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }

//        int pivot = nums.length - 1;
//        int last = 0;
//        for (int i = pivot - 1; i >= 0; i--) {
//            if (nums[i] < nums[pivot]) {
//                last = i;
//                break;
//            }
//        }
        int pivot = nums.length - 2;
        while (pivot >= 0) {
            if (nums[pivot] < nums[pivot + 1]) {
                break;
            }
            pivot--;
        }

        if (pivot == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int min = Integer.MAX_VALUE;
        int swapIndex = 0;
        for (int i = pivot + 1; i < nums.length; i++) {
            if (nums[i] > nums[pivot] && nums[i] <= min) {
                min = nums[i];
                swapIndex = i;
            }
        }

        swap(pivot, swapIndex, nums);

        reverse(nums, pivot + 1, nums.length - 1);
    }

    private static void reverse(int[] nums, int pivot, int last) {
        int first = pivot;
        while (first < last) {
            int temp = nums[first];
            nums[first] = nums[last];
            nums[last] = temp;
            first++;
            last--;
        }
    }

    static void swap(int i1, int i2, int[] nums) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
}
