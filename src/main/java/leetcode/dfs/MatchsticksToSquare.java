package leetcode.dfs;

import java.util.Arrays;

// GOOD, hard problem
class MatchsticksToSquare {
    public static void main(String[] args) {
        var m = new MatchsticksToSquare();
        boolean makesquare = m.makesquare(new int[]{5,5,5,5,4,4,4,4,3,3,3,3});
        System.out.println(makesquare);
    }

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int stick : matchsticks) {
            sum += stick;
        }

        if (sum % 4 != 0) {
            return false;
        }

        int sideLength = sum / 4;
        Arrays.sort(matchsticks);
        reverseArray(matchsticks);

        return dfs(matchsticks, new boolean[matchsticks.length], 0, 0, sideLength, 0);
    }

    private static boolean dfs(int[] matchsticks, boolean[] used, int start, int current, int sideLength, int k) {
        if (k == 4) {
            return true;
        }
        if (current == sideLength) {
            // reset current length, meaning the "current" and "start" variables
            return dfs(matchsticks, used, 0, 0, sideLength, k + 1);
        }

        for (int i = start; i < matchsticks.length; i++) {
            if (!used[i] && current + matchsticks[i] <= sideLength) {
                used[i] = true;
                if (dfs(matchsticks, used, i + 1, current + matchsticks[i], sideLength, k)) {
                    return true;
                }
                used[i] = false;
            } else {
                // optimization!
                while (i + 1 < matchsticks.length
                        && matchsticks[i] == matchsticks[i + 1]) {
                    i++;
                }
            }
        }

        return false;
    }

    private static void reverseArray(int[] matchsticks) {
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }
    }
}
