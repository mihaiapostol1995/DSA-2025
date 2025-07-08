package leetcode.dp;

class ArithmeticSlices {
    public static void main(String[] args) {
        var slices = new ArithmeticSlices();
        slices.numberOfArithmeticSlices(new int[]{1,3,5,7,9});
    }

    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length <= 2) return 0;

        int count = 0;
        int current = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1]
                    == nums[i - 1] - nums[i - 2]) {
                current++;
                count = count + current;
            } else {
                current = 0;
            }
        }
        return count;
    }
}
