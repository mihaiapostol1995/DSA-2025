package leetcode.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PacificAtlanticWaterFlow {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        var p = new PacificAtlanticWaterFlow();
        List<List<Integer>> lists = p.pacificAtlantic(matrix);
        System.out.println(lists);
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        // pacific
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];
        // can use a simple loop
        for (int j = 0; j < heights[0].length; j++) {
            dfs(pacific, 0, j, heights);
            dfs(atlantic, heights.length - 1, j, heights);
        }

        for (int i = 0; i < heights.length; i++) {
            dfs(pacific, i, 0, heights);
            dfs(atlantic, i, heights[0].length - 1, heights);
        }

        // Collect cells reachable by both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(boolean[][] ocean, int i, int j, int[][] heights) {
        int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {0, 0}};

        for (int[] direction : directions) {
            int x = i + direction[0];
            int y = j + direction[1];
            if (x >= 0 && y >= 0 && x < ocean.length && y < ocean[0].length
                    && !ocean[x][y]) {
                // advance
                if (heights[x][y] >= heights[i][j]) {
                    ocean[x][y] = true;
                    // recurse
                    dfs(ocean, x, y, heights);
                }
            }
        }
    }
}

record Coordinate(int x, int y) {}