package leetcode.hashset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class ValidSudoku {

    public static void main(String[] args) {

    }

    public static boolean isValidSudoku(char[][] board) {
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char digit = board[i][j];
                if (digit == '.') {
                    continue;
                }

                if (rows[i].contains(digit)
                        || cols[j].contains(digit)) {
                    return false;
                }

                var box = (i / 3) * 3 + j / 3;
                if (boxes[box].contains(digit)) {
                    return false;
                }

                rows[i].add(digit);
                cols[j].add(digit);
                boxes[box].add(digit);
            }
        }

        return true;
    }
}
