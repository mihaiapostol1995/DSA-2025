package leetcode.array.twod;

import java.util.Arrays;

public class DiagonalTraverse {
    public static void main(String[] args) {
        var d = new DiagonalTraverse();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
//        int[][] matrix = {
//                {1, 2},
//                {3, 4},
//        };
        int[] diagonalOrder = d.findDiagonalOrder(matrix);
        System.out.println(Arrays.toString(diagonalOrder));
    }

    public int[] findDiagonalOrder(int[][] mat) {
        int dir = 1;
        int[] copy = new int[mat.length * mat[0].length];
        int i = 0, j = 0;

        for (int index = 0; index < copy.length; index++) {
            copy[index] = mat[i][j];

            if (dir == 1) {
                if (j == mat[0].length - 1) {
                    i++;
                    dir = - 1;
                } else if (i == 0) {
                    j++;
                    dir = -1;
                } else {
                    j++;
                    i--;
                }
            } else {
                if (i == mat.length - 1) {
                    j++;
                    dir = 1;
                } else if (j == 0) {
                    i++;
                    dir = 1;
                } else {
                    i++;
                    j--;
                }
            }
        }

        return copy;
    }
}
