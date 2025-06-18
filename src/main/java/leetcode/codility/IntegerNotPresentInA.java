package leetcode.codility;

import java.util.Arrays;

class IntegerNotPresentInA {

    public static void main(String[] args) {

    }

    public int solution(int[] A) {
        Arrays.sort(A);
        var lastElement = A[A.length - 1];
        if (lastElement < 0) return 1;

        int found = -1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                continue;
            } if (A[i + 1] - A[i] > 1) {
                return A[i +1] + 1;
            }
        }
        if (found == -1) {
            return lastElement + 1;
        }

        return found;
    }
}
