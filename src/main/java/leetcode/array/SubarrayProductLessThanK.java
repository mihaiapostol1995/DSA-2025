package leetcode.array;

class SubarrayProductLessThanK {

    public static void main(String[] args) {
//        numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100);
        numSubarrayProductLessThanKSlidingWindow(new int[]{1,1,1}, 1);
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            var digit = nums[i];
            if (digit < k) {
                count++;
            }
            int product = digit;
            for (int j = i + 1; j < nums.length; j++) {
                product *= nums[j];
                if (product >= k) {
                    break;
                } else {
                    count++;
                }
            }
        }

        return count;
    }

    public static int numSubarrayProductLessThanKSlidingWindow(int[] nums, int k) {
        int product = 1;
        int left = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];

            while (product >= k) {
                product /= nums[left];
                left++;
            }

            count += right - left;
        }

        return count;
    }
}
