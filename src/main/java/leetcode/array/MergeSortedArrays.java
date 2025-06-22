package leetcode.array;

class MergeSortedArrays {
    public static void main(String[] args) {
        merge(new int[]{4,5,6,0,0,0}, 3, new int[]{1,2,3}, 3);
    }

    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            nums1[0] = nums2[0];
        }
        if (n == 0) {
            return nums1;
        }

        var index = nums1.length - 1;
        while (m > 0 && n > 0) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[index] = nums1[m - 1];
                m--;
            } else if (nums1[m - 1] <= nums2[n - 1]) {
                nums1[index] = nums2[n - 1];
                n--;
            }
            index--;
        }
        if (m == 0 && n > 0) {
            for (int i = n - 1; i >= 0; i--) {
                nums1[index] = nums2[i];
                index--;
            }
        }

        return nums1;
    }
}
