package leetcode.codility;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomFlipMatrix {
    public static void main(String[] args) {

    }


}

// HARD, interesting problem
class MySolution {

    int rows;
    int cols;
    int total;
    Map<Integer, Integer> flippedToNextFlip;
    Random random;

    public MySolution(int m, int n) {
        rows = m;
        cols = n;
        total = m * n;
        flippedToNextFlip = new HashMap<>();
        random = new Random();
    }

    public int[] flip() {
        // Generate random index from remaining available positions
        int randIdx = random.nextInt(total);
        total--; // Reduce available positions

        // Check if this position has been mapped to another position
        int actualIdx = flippedToNextFlip.getOrDefault(randIdx, randIdx);

        // Map current random position to the last available position
        // This effectively "removes" the current position from future selections
        flippedToNextFlip.put(randIdx, flippedToNextFlip.getOrDefault(total, total));

        // Convert 1D index back to 2D coordinates
        return new int[]{actualIdx / cols, actualIdx % cols};
        // example: 10 columns, 1 row. If I pick 5, I get row 0, column 5, which is correct! if i chose row it would be wrong
    }

    public void reset() {
        total = rows * cols;
        flippedToNextFlip.clear();
    }
}