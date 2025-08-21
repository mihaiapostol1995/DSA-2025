package leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElementII {
    public static void main(String[] args) {
        var n = new NextGreaterElementII();
        int[] ints = n.nextGreaterElements(new int[]{-1, 0});
        System.out.println(Arrays.toString(ints));
    }

    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>(); // stack of indexes

        int[] res = new int[nums.length];
        Arrays.fill(res, -1);

        for (int i = 0; i < nums.length * 2; i++) {
            int j = i % nums.length;
            while (!stack.isEmpty()
                    && nums[j] > nums[stack.peek()]) {
                res[stack.pop()] = nums[j];
            }

            stack.push(j);
        }

        return res;
    }
}
