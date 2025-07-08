package leetcode.puzzle;

class EliminationGame {
    public static void main(String[] args) {
        EliminationGame eliminationGame = new EliminationGame();
        int i = eliminationGame.lastRemaining(9);
        System.out.println(i);
    }

    public int lastRemaining(int n) {
        boolean leftToRight = true;
        int first = 1;
        int step = 1;
        int remaining = n;

        while (remaining > 1) {
            if (leftToRight || remaining % 2 == 1) {
                first = first + step;
            }

            remaining /= 2;
            step = step * 2;
            leftToRight = !leftToRight;
        }
        return first;
    }
}
