package leetcode.dfs;

class NumberOfIslands {

    public static void main(String[] args) {
        char[][] array = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(numberOfIslands(array));
    }

    static int numberOfIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                    grid[i][j] = '0';
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int i, int j) {
        if (i == grid.length || i < 0 || j == grid[0].length || j < 0) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
