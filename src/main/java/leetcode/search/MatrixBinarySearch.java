package leetcode.search;

class MatrixBinarySearch {

    public static void main(String[] args) {
        int[][] array = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };

        searchMatrix(array, 23);
        binarySearchElegant(array, 23);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int result = binarySearch(matrix[i], target);
            if (result != -1) {
                return true;
            }
        }
        return false;
    }

    static int binarySearch(int[]matrix, int target) {
        int left = 0, right = matrix.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[mid] == target) {
                return mid;
            } else if (matrix[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    static int binarySearchElegant(int[][] matrix, int target) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int max = rows * columns - 1;

        int left = 0, right = max;
        while (left <= right) {
            int mid = (left + right) / 2;

            int element = matrix[mid / columns][mid % columns];
            if (element == target) {
                return mid;
            } else if (element > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
