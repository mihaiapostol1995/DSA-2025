package leetcode.array;

class RotateImage {
    public static void main(String[] args) {
        rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }

    public static void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            int k = 0;
            int j = matrix[i].length - k - 1;

            while (k < j) {
                int temp = matrix[i][k];

                matrix[i][k] = matrix[i][j];
                matrix[i][j] = temp;
                k++;
                j--;
            }
        }
    }
}
