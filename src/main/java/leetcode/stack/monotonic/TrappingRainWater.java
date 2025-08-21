package leetcode.stack.monotonic;

import java.util.Stack;

class TrappingRainWater {
    public static void main(String[] args) {
        var t = new TrappingRainWater();
        t.trap(new int[]{4,2,0,3,2,5});
    }

    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0;

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty()
                    && height[i] > height[stack.peek()]) {
                var bottom = stack.pop();
                if (stack.isEmpty()) break;

                var preBottom = stack.peek();
                var length = Math.min(height[preBottom], height[i]) - height[bottom];
                var width = i - preBottom - 1;
                water += length * width;
            }
            stack.push(i);
        }
        return water;
    }
}
