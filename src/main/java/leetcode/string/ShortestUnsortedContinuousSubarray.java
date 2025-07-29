package leetcode.string;

class ShortestUnsortedContinuousSubarray {

    public static void main(String[] args) {
        var s = new ShortestUnsortedContinuousSubarray();
        int unsortedSubarray = s.findUnsortedSubarray(new int[]{1,1});
        System.out.println(unsortedSubarray);
    }

    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        // find first unsorted element
        int minIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                minIndex = i - 1;
                break;
            }
        }

        // find second unsorted element
        int maxIndex = nums.length - 1;
        for (int i = nums.length - 1; i >= 1; i--) {
            if (nums[i] < nums[i - 1]) {
                maxIndex = i;
                break;
            }
        }

        if (minIndex == 0 && maxIndex == nums.length -1 && nums[minIndex] <= nums[minIndex + 1]) {
            return 0;
        }

        // Find min and max within the unsorted subarray!!!!!!!!!!!!!
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = minIndex; i <= maxIndex; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        // Expand the left boundary
        while (minIndex > 0 && nums[minIndex - 1] > min) {
            minIndex--;
        }

        while (maxIndex < nums.length - 1 && nums[maxIndex + 1] < max) {
            maxIndex++;
        }

        return maxIndex - minIndex + 1;
    }
}
