package leetcode.array;

import java.util.Arrays;

class Heaters {

    // GOOD binary search problem
    // GOOD problem! Really tests your knowledge of binary search
    public static void main(String[] args) {
        var h = new Heaters();
//        h.findRadiusSlow(new int[]{1,5}, new int[]{2});
//        h.findRadius(new int[]{1, 2, 3}, new int[]{2});

        h.binarySearchLeft(2, new int[]{1,2,3,4,5});
    }

    public int findRadiusSlow(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        int[] copy = new int[houses.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < heaters.length; i++) {
            for (int j = 0; j < houses.length; j++) {
                copy[j] = Math.min(copy[j], Math.abs(houses[j] - heaters[i]));
            }
        }

        return Arrays.stream(copy).max().getAsInt();
    }

    public int findRadius(int[] houses, int[] heaters) {
        var max = Integer.MIN_VALUE;
        Arrays.sort(houses);

        for (int house: houses) {
            // house: 2
            // heaters: 5, 6, 7, 8
            // left search: right = -1
            // right search: left = 0

            // otherwise, simple binary search on the INTERVAL
            int right = binarySearchLeft(house, heaters);
            int left = binarySearchRight(house, heaters);

            if (right == -1) {
                max = Math.max(max, heaters[left] - house);
            } else if (left == heaters.length) {
                max = Math.max(max, house - heaters[right]);
            } else {
                max = Math.max(max, Math.min(heaters[left] - house,
                        house - heaters[right]));
            }
        }

        return max;
    }

    // binary search: search in heater interval
    private int binarySearchLeft(int target, int[] heaters) {
        int left = 0, right = heaters.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (heaters[mid] <= target) {
                left = mid + 1;
            } else {
                // try to minimize this to get the WRONG result
                right = mid - 1;
            }
        }
        return right;
    }

    private int binarySearchRight(int target, int[] heaters) {
        int left = 0, right = heaters.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (heaters[mid] >= target) {
                right = mid - 1;
            } else {
                // try to maximize this to get the WRONG result
                left = mid + 1;
            }
        }
        return left;
    }
}
