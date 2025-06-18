package leetcode;

import java.util.Arrays;

public class MaximumBinaryTree {

    public static void main(String[] args) {
        System.out.println(constructMaximumBinaryTree(new int[]{3,2,1,6,0,5}));
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }

        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        int[] sufix = Arrays.copyOfRange(nums, maxIndex + 1, nums.length);
        int[] prefix = Arrays.copyOfRange(nums, 0, maxIndex);
        return new TreeNode(nums[maxIndex],
                constructMaximumBinaryTree(prefix),
                constructMaximumBinaryTree(sufix));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}