package leetcode;

import java.util.LinkedList;
import java.util.Queue;

class Matrix02 {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };

        int[][] matrix2 = {
                {0, 0, 0, 0}
        };

        var result = matrix02(matrix2);
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    static int[][] matrix02(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[m][n];

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    result[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }


        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int row = poll[0];
            int col = poll[1];

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                    if (result[newRow][newCol] > result[row][col]) { // Integer.MAX
                        result[newRow][newCol] = result[row][col] + 1;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }
        return result;
    }
}
