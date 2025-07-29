package leetcode.dfs;

import java.util.Arrays;

class OutOfBoundaryPaths {

    public static void main(String[] args) {
        var o = new OutOfBoundaryPaths();
        int paths = o.findPaths(2, 2, 2, 0, 0);
        System.out.println(paths);
    }

    private static final int MOD = 1_000_000_007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[m][n][maxMove + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= maxMove; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return dfs(dp, m, n, maxMove, startRow, startColumn);
    }

    private int dfs(int[][][]dp, int m, int n, int maxMove, int row, int col) {
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return 1;
        }
        if (maxMove <= 0) {
            return 0;
        }
        if (dp[row][col][maxMove] != -1) {
            return dp[row][col][maxMove];
        }

        int count = 0;
        count = (count + dfs(dp, m, n, maxMove - 1, row - 1, col)) % MOD;
        count = (count + dfs(dp, m, n, maxMove - 1, row + 1, col)) % MOD;
        count = (count + dfs(dp, m, n, maxMove - 1, row, col - 1)) % MOD;
        count = (count + dfs(dp, m, n, maxMove - 1, row, col + 1)) % MOD;

        dp[row][col][maxMove] = count;

        return count;
    }
}
