package leetcode.stack.monotonic;

import java.util.Stack;

class LargestRectangleInHistogram {
    public static void main(String[] args) {
        var l = new  LargestRectangleInHistogram();
        l.largestRectangleArea(new int[]{2,1,5,6,2,3});
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;

        for (int i = 0; i <= heights.length; i++) {
            // Use 0 as a sentinel for the end
            int currHeight = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty()
                    && currHeight < heights[stack.peek()]) {
                int top = stack.pop();
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, heights[top] * width);
            }
            stack.push(i);
        }
        return max;
    }
}
