package leetcode.array;

class MoveZeroes {
    public static void main(String[] args) {
        MoveZeroes solution = new MoveZeroes();
        solution.moveZeroes(new int[] {1,0,2,3,0,4,5,0,6,0});
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        var lastNumber = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                var j = i + 1;
                for (; j < nums.length; j++) {
                    if (nums[j] != 0) {
                        lastNumber = j;
                        break;
                    }
                }
                if (j == nums.length) {
                    continue;
                }
                var temp = nums[i];
                nums[i] = nums[lastNumber];
                nums[lastNumber] = temp;
            }
        }
    }
}
