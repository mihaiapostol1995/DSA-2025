package leetcode.search;

class KokoEatingBananas {
    public static void main(String[] args) {
        int[] arr = new int[]{3,6,7,11};
        var k = new KokoEatingBananas();
        k.minEatingSpeed(arr, 8);
    }

    public int minEatingSpeed(int[] piles, int hoursAllowed) {
        int highestPile = Integer.MIN_VALUE;
        for (int pile : piles) {
            highestPile = Math.max(highestPile, pile);
        }

        int left = 0, right = highestPile;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, hoursAllowed, mid)) {
                // try eating slower
                right = mid;
            } else {
                // eat faster
                left = mid + 1;
            }
        }
        return left;
    }

    boolean canFinish(int[] piles, int h, int k) {
        int totalHours = 0;
        for (int pile : piles) {
            int hoursToFinish = (pile + k - 1) / k; // speed formula
            totalHours += hoursToFinish;
            if (totalHours > h) {
                return false;
            }
        }
        return true;
    }
}
