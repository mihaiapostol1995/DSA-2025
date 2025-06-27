package leetcode.prefixsum;

import java.util.ArrayList;
import java.util.List;

class RangeSumQueryImmutableII {
    public static void main(String[] args) {
        int[][] grid = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix numMatrix = new NumMatrix(grid);
        int i = numMatrix.sumRegion(2, 1, 4, 3);
        System.out.println(i);
    }
}

class NumMatrix {

    List<List<Integer>> prefixSumMatrix = new ArrayList<>();

    public NumMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            List<Integer> rowTillNow = new ArrayList<>();
            var firstSum = matrix[i][0];
            if (i > 0) {
                firstSum = firstSum + prefixSumMatrix.get(i - 1).get(0);
            }
            rowTillNow.add(firstSum);

            List<Integer> currentRow = new ArrayList<>();
            currentRow.add(matrix[i][0]);
            if (matrix[i].length > 1) {
                for (int j = 1; j < matrix[0].length; j++) {
                    var previousSum = currentRow.get(currentRow.size() - 1);
                    var sum = previousSum + matrix[i][j];
                    currentRow.add(sum);

                    if (i > 0) {
                        sum = sum + prefixSumMatrix.get(i - 1).get(j);
                    }
                    rowTillNow.add(sum);
                }
            }

            prefixSumMatrix.add(rowTillNow);
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (row1 == 0 && col1 ==0) {
            return prefixSumMatrix.get(row2).get(col2);
        }
        if (row1 == 0) {
            return prefixSumMatrix.get(row2).get(col2)
                    - prefixSumMatrix.get(row2).get(col1 - 1);
        }
        if (col1 == 0) {
            return prefixSumMatrix.get(row2).get(row2)
                    - prefixSumMatrix.get(row1 - 1).get(col2);
        }
        int bigSquare = prefixSumMatrix.get(row2).get(col2);
        int smallRowSquare = prefixSumMatrix.get(row1 - 1).get(col2);
        int smallColSquare = prefixSumMatrix.get(row2).get(col1 - 1);
        int doublySubtractedSquare = prefixSumMatrix.get(row1 -1).get(col1 - 1);
        return bigSquare - smallColSquare - smallRowSquare + doublySubtractedSquare;
    }
}

/// this approach is much cleaner:

class NumMatrix2 {

    int prefix[][];

    public NumMatrix2(int[][] matrix) {

        prefix = matrix;
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 1; j < n; j++){
                prefix[i][j] += prefix[i][j-1];
            }
        }

        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                prefix[i][j] += prefix[i-1][j];
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        int ans = prefix[row2][col2];
        if(row1 > 0)ans -= prefix[row1 - 1][col2];
        if(col1 > 0)ans -= prefix[row2][col1 - 1];
        if(row1 > 0 && col1 > 0) ans += prefix[row1 - 1][col1 - 1];
        return ans;

    }
}