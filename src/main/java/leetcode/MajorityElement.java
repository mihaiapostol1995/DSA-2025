package leetcode;

class MajorityElement {
    public static void main(String[] args) {
        majorityElement(new int[]{3, 2, 3});
    }

    public static int majorityElement(int[] nums) {
        int count = 1;
        int candidate = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                candidate = nums[i + 1];
            }
        }
        return candidate;
    }
}
