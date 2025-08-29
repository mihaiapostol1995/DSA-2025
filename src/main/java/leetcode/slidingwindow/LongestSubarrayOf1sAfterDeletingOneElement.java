package leetcode.slidingwindow;

class LongestSubarrayOf1sAfterDeletingOneElement {

    public static void main(String[] args) {
        var l = new LongestSubarrayOf1sAfterDeletingOneElement();
        int i = l.longestSubarray(new int[]{0,1,1,1,0,1,1,0,1});
        System.out.println(i);
    }

    public int longestSubarray(int[] nums) {
        int max = 0;
        int countOfNonZeroElements = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                countOfNonZeroElements++;
                while (countOfNonZeroElements > 1) {
                    if (nums[left] == 0) {
                        countOfNonZeroElements--;
                    }
                    left++;
                }
            }
            max = Math.max(right - left + 1 - countOfNonZeroElements, max);
        }
        if (countOfNonZeroElements == 0) {
            return max - 1; // must delete ONE element anyway
        }
        return max;
    }
}
