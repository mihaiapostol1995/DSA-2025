package leetcode.array;

import java.util.ArrayList;
import java.util.List;

class FindAllDuplicatesInArray {
    public static void main(String[] args) {
        var f = new FindAllDuplicatesInArray();
        List<Integer> duplicates = f.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        System.out.println(duplicates);
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;

            if (nums[index] < 0) {
                duplicates.add(index + 1);
            } else {
                nums[index] = -nums[index];
            }
        }
        return duplicates;
    }

}
