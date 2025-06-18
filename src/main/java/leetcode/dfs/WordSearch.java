package leetcode.dfs;

class WordSearch {
    public static void main(String[] args) {
        char[][] array = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        exist(array, "ABCCED");
    }

    static boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int index = 0; index < board.length; index++) {
            for (int index2 = 0; index2 < board[index].length; index2++) {
                var search = search(board, word, index, index2, visited, 0);
                if (search) {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean search(char[][] board, String word, int i, int j, boolean[][] visited, int letterIndex) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
                || letterIndex >= word.length()
                || visited[i][j]) {
            return false;
        }
        if (board[i][j] != word.charAt(letterIndex)) {
            return false;
        }
        if (letterIndex == word.length() - 1) {
            return true;
        }

        visited[i][j] = true;

        letterIndex++;
        boolean search = search(board, word, i + 1, j, visited, letterIndex);
        boolean search1 = search(board, word, i - 1, j, visited, letterIndex);
        boolean search2 = search(board, word, i, j + 1, visited, letterIndex);
        boolean search3 = search(board, word, i, j - 1, visited, letterIndex);

        visited[i][j] = false;

        return search || search1 || search2 || search3;
    }
}
