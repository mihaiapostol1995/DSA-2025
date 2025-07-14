package leetcode.array;

import java.util.Stack;

class Find132Pattern {
    public static void main(String[] args) {
        var f = new Find132Pattern();
        boolean f132pattern = f.find132pattern(new int[]{1,0,1,-4,-3});
        System.out.println(f132pattern);
    }

//    [-1,3,2,0]
//    [1,0,1,-4,-3]
    // monotonic stack
    public boolean find132pattern(int[] nums) {
        int[] minsFromLeft = new int[nums.length];
        minsFromLeft[0] = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            minsFromLeft[i] = min;
        }

        // HARD. don't know if i will remember this
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length -1; i >= 0; i--) {
            // 2 or 3 has to be bigger than 1
            if (nums[i] > minsFromLeft[i]) {
                while (!stack.isEmpty()
                        // THIS IS WHY WE NEED THIS FUCKING LOOP
                        && stack.peek() <= minsFromLeft[i]) {
                    stack.pop();
                }

                // did we find 3?
                if (!stack.isEmpty() && nums[i] > stack.peek()) {
                    return true;
                }

                // possible 2
                stack.push(nums[i]);
            }
        }

        return false;
    }

    record IndexValue(int index, int value) {}
}
