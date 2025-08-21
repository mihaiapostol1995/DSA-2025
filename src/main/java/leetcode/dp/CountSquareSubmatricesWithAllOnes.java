package leetcode.dp;

class CountSquareSubmatricesWithAllOnes {
    public static void main(String[] args) {
//        int[][] grid = {
//                {0, 1, 1, 1},
//                {1, 1, 1, 1},
//                {0, 1, 1, 1}
//        };
//        int[][] grid = {
//                {1, 0, 1},
//                {1, 1, 0},
//                {1, 1, 0}
//        };
        int[][] grid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 1, 0},
                {1, 1, 1},
                {1, 1, 0}
        };

        var c = new  CountSquareSubmatricesWithAllOnes();
        c.countSquares(grid);
    }

    public int countSquares(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 1) {
                count++;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 1) {
                count++;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += Math.min(matrix[i - 1][j - 1],
                            Math.min(matrix[i - 1][j], matrix[i][j - 1]));
                    count += matrix[i][j];
                }
            }
        }
        return count;
    }
}
