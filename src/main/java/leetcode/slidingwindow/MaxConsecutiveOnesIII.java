package leetcode.slidingwindow;

class MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        var m = new MaxConsecutiveOnesIII();
        int i = m.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2);
        System.out.println(i);
    }

    public int longestOnes(int[] nums, int k) {
        int usedK = 0;
        int max = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 1) {
                usedK++;
                while (left <= right && usedK > k) {
                    if (nums[left] == 0) {
                        usedK--;
                    }
                    left++;
                }
            }

            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
