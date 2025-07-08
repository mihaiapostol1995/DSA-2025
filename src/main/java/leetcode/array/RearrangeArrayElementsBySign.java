package leetcode.array;

import java.util.Arrays;

class RearrangeArrayElementsBySign {
    public static void main(String[] args) {
        RearrangeArrayElementsBySign obj = new RearrangeArrayElementsBySign();
        int[] ints = obj.rearrangeArray(new int[]{3, 1, -2, -5, 2, -4});
        System.out.println(Arrays.toString(ints));
    }

    public int[] rearrangeArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int[] positives = new int[nums.length];
        int posIndex = 0;
        int[] negatives = new int[nums.length];
        int negIndex = 0;
        for (int num : nums) {
            if (num > 0) {
                positives[posIndex] = num;
                posIndex++;
            } else {
                negatives[negIndex] = num;
                negIndex++;
            }
        }

        posIndex = 0;
        negIndex = 0;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (posIndex == negIndex) {
                res[i] = positives[posIndex];
                posIndex++;
            } else {
                res[i] = negatives[negIndex];
                negIndex++;
            }
        }
        return res;
    }
}
