package leetcode;

import java.util.LinkedList;
import java.util.Queue;

class ShortestPathInBinaryMatrix {

    static int FINAL_MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };

//        int[][] grid2 = {
//                {0, 1},
//                {1, 0}
//        };

        int[][] array = {
                {0, 1, 1, 0, 0, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 0}
        };

        int[][] visited = new int[array.length][array[0].length];

        shortestPath(array, 0, 0, 0, visited);
        int result = FINAL_MAX;
        FINAL_MAX = Integer.MAX_VALUE;
        System.out.println(result);

        System.out.println(shortestPathDfs(array));
    }

    static void shortestPath(int[][] grid, int row, int col, int count, int[][] visited) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length
                || grid[row][col] == 1 || visited[row][col] == 1) {
            return;
        }
        // reached the end of the grid
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            count++;
            FINAL_MAX = Math.min(FINAL_MAX, count);
            return;
        }

        count++;
        visited[row][col] = 1;

        shortestPath(grid, row + 1, col, count, visited);
        shortestPath(grid, row - 1, col, count, visited);

        shortestPath(grid, row, col + 1, count, visited);
        shortestPath(grid, row, col - 1, count, visited);

        shortestPath(grid, row - 1, col - 1, count, visited);
        shortestPath(grid, row + 1, col + 1, count, visited);

        shortestPath(grid, row - 1, col + 1, count, visited);
        shortestPath(grid, row + 1, col - 1, count, visited);

        visited[row][col] = 0;
    }

    static int shortestPathDfs(int[][] grid) {
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) {
            return -1;
        }

        int[][] positions = new int[][]{
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}
        };
        int[][] visited = new int[grid.length][grid[0].length];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] position = queue.poll();

            for (int i = 0; i < positions.length; i++) {
                int x = position[0];
                int y = position[1];

                int nextX = x + positions[i][0];
                int nextY = y + positions[i][1];

                if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length
                        && visited[nextX][nextY] == 0) {
                    if (nextX == grid.length - 1 && nextY == grid[0].length - 1) {
                        return position[2] + 1;
                    }
                    if (grid[nextX][nextY] == 0) {
                        queue.offer(new int[]{nextX, nextY, position[2] + 1});
                    }

                    visited[nextX][nextY] = 1;
                }
            }
        }

        return -1;
    }
}
