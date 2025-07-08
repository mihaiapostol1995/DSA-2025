package leetcode.priorityqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class KthSmallestElementInASortedMatrix {
    public static void main(String[] args) {

    }

    public int kthSmallestSlow(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return -1;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                list.add(matrix[i][j]);
            }
        }
        Collections.sort(list);
        return list.get(k);
    }

    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return -1;
        }

        return 0;
        // use priority queue and store value, col and row. store rows. poll and offer, for columns
    }
}
