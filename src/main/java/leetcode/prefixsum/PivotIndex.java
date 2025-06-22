package leetcode.prefixsum;

class PivotIndex {
    public static void main(String[] args) {
        int result = pivotIndex(new int[]{2,1,-1});
        System.out.println(result);
    }

    static int pivotIndex(int[] nums) {
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            if (sum[i] == sum[sum.length - 1] - sum[i + 1]) {
                return i;
            }
        }
        return -1;
    }
}
