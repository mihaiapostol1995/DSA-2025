package leetcode.array.twod;

class GameOfLife {

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();
        int[][] grid = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        gameOfLife.gameOfLife(grid);
    }

    /*
     * Use special values to encode transitions:
     * 0 -> 0: dead stays dead
     * 1 -> 1: alive stays alive
     * 1 -> 0: alive dies (use -1)
     * 0 -> 1: dead becomes alive (use 2)
     */
    public void gameOfLife(int[][] board) {
        if (board == null) {
            return;
        }

        int[][] directions = new int[][]{{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int live = 0;
                for (var direction : directions) {
                    int x = direction[0] + i, y = direction[1] + j;
                    if (x >= 0 && y >= 0 && x < board.length && y < board[0].length
                            && (board[x][y] == 1 || board[x][y] == -1)) { // -1 WAS ALIVE, that's why we keep count
                        live++;
                    }
                }

                if (board[i][j] == 0) {
                    // was dead
                    if (live == 3) {
                        board[i][j] = 2;
                    }
                } else {
                    // was alive
                    if (live < 2 || live > 3) {
                        board[i][j] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                } else if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }
}
