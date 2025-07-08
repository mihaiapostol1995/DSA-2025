package leetcode.dp;

import java.util.HashSet;
import java.util.Set;

class WiggleMaxSequence {
    public static void main(String[] args) {
        WiggleMaxSequence obj = new WiggleMaxSequence();
//        int i = obj.wiggleMaxLengthSlow(new int[]{1,2,3,4});
//        System.out.println(i);

        int i = obj.wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5});
        System.out.println(i);
    }

    public int wiggleMaxLengthSlow(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        if (set.size() == 1) {
            return 0;
        }

        // search for first positive diff
        int firstPositive = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                firstPositive = i;
                break;
            }
        }
        if (firstPositive == 0) return 2;
        int posCount = getPosCount(nums, true, firstPositive);

        // search for first negative diff
        int firstNegative = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                firstNegative = i;
                break;
            }
        }
        if (firstNegative == 0) return 2;
        int negCount = getPosCount(nums,false, firstNegative);

        return Math.max(posCount + 2, negCount + 2);
    }

    private int getPosCount(int[] nums, boolean isPositive, int firstNegative) {
        int count = 0;
        for (int i = firstNegative + 1; i < nums.length; i++) {
            if (isPositive) {
                if (nums[i] < nums[i - 1]) {
                    count++;
                    isPositive = false;
                }
            } else {
                if (nums[i] > nums[i - 1]) {
                    isPositive = true;
                    count++;
                }
            }
        }
        return count;
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) return 0;

        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = down[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if  (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = up[i - 1] + 1;
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(up[nums.length - 1], down[nums.length - 1]);
    }
}
