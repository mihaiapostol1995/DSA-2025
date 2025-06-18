package leetcode.array;

import java.util.ArrayList;
import java.util.List;

class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        int[][] matrix2 = {
                {6,9,7}
        };
        int[][] matrix3 = {
                {6},
                {9},
                {7}
        };

        int[][] matrix4 = {
                {3},
                {2}
        };
        System.out.println(spiralOrder(matrix4));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int top = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;

        List<Integer> result = new ArrayList<>();
        while (left <= right && top <= bottom) {

            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            if (top <= bottom && left <= right) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if (top <= bottom && left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }
}

