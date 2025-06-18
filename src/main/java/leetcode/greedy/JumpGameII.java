package leetcode.greedy;

class JumpGameII {

    public static void main(String[] args) {
//        System.out.println(jump(new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5}, 0, 0));
//        jumpGreedy(new int[]{2, 3, 1, 1, 4});
        jumpGreedy(new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5});
    }

    public static int jump(int[] nums, int pos, int jump) {
        if (pos == nums.length - 1) {
            return jump;
        }
        if (pos > nums.length - 1) {
            return Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE;
        jump = jump + 1;
        for (int i = 1; i <= nums[pos]; i++) {
            min = Math.min(min, jump(nums, pos + i, jump));
        }

        return min;
    }

    public static int jumpGreedy(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int jumps = 0;
        int furthestRange = 0;
        int currentRange = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            furthestRange = Math.max(furthestRange, i + nums[i]);

            if (i == currentRange) {
                jumps++;
                currentRange = furthestRange;

                if (currentRange >= nums.length - 1) {
                    return jumps;
                }
            }
        }

        return -1;
    }
}
