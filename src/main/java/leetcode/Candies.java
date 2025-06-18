package leetcode;

class Candies {

    public static void main(String[] args) {
        System.out.println(distributeCandies(10007, 20003));
    }

    public static long distributeCandies(int n, int limit) {
        int count = 0;
        for (int z = 0; z <= n; z++) {
            if (z > limit) {
                continue;
            }
            int rest = n - z;
            long combinations = determineCombinations(rest, limit);
            if (combinations > 0) {
                count += combinations;
            }
        }
        return count;
    }

    static long determineCombinations(int n, int limit) {
        int count = 0;
        boolean hasDoubles = false;
        for (int i = 0; i <= n; i++) {
            int x = i;
            int y = n - i;
            if (x <= limit && y <= limit) {
                if (x == y) {
                    hasDoubles = true;
                } else {
                    count++;
                }
            }
        }
        return hasDoubles? count + 1 : count;
    }
}