package leetcode.array;

class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,1,1,2,2,3}));
    }

    static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int duplicateCount = 1;
        int writeIndex = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                duplicateCount = 1;
            } else {
                duplicateCount++;
            }

            if (duplicateCount > 2) {
                continue;
            } else {
                nums[writeIndex] = nums[i];
                writeIndex++;
            }
        }

        return writeIndex;
    }
}
