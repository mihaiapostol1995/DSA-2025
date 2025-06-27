package leetcode.array;

import java.util.Arrays;

class HIndex {
    public static void main(String[] args) {
        HIndex obj = new HIndex();
        int i = obj.hIndex(new int[]{100});
        System.out.println(i);

        obj.hIndexBinarySearch(new int[]{0, 1, 100, 100, 100});
    }

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int max = 0;
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] > max) {
                int min = Math.min(citations[i], citations.length - i);
                max = Math.max(min, max);
            }
        }
        return max;
    }

    public int hIndexFast(int[] citations) {
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            int remaining = citations.length - i;
            if (citations[i] >= remaining) {
                return remaining;
            }
        }
        return 0;
    }

    // now with binary search:
    public int hIndexBinarySearch(int[] citations) {
        int left = 0;
        int right = citations.length - 1;
        int length = citations.length;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] == length - mid) {
                return length - mid;
            } else if (citations[mid] < length - mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return citations.length - left;
    }
}
