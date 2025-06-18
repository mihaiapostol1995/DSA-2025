package leetcode;

class MinimumSizeSubArray {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println(minSubArrayLenEfficient(7, new int[]{2,3,1,2,4,3}));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int length = Integer.MAX_VALUE;
        for (int left = 0; left < nums.length - 1; left++) {

            int right = left;
            int sum = 0;
            while (right < nums.length) {
                sum += nums[right];
                if (sum < target) {
                    right++;
                } else if (sum >= target) {
                    length = Math.min(length, right - left + 1);
                    break;
                }
            }
        }

        if (length == Integer.MAX_VALUE) {
            return 0;
        } else {
            return length;
        }
    }

    public static int minSubArrayLenEfficient(int target, int[] nums) {
        int length = Integer.MAX_VALUE;
        int sum = 0, left = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                length = Math.min(length, right - left + 1);
                sum -= nums[left];

                left++;
            }
        }

        if (length == Integer.MAX_VALUE) {
            return 0;
        } else {
            return length;
        }
    }
}
