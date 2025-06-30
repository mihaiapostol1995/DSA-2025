package leetcode.codility;

import java.util.Collections;
import java.util.List;
import java.util.Random;

class PlaylistShuffle {
    public static <T> void fisherYatesShuffle(List<T> playlist) {
        Random random = new Random();

        // Start from the last element and work backwards
        for (int i = playlist.size() - 1; i > 0; i--) {
            // Pick a random index from 0 to i (inclusive)
            int j = random.nextInt(i + 1);

            // Swap elements at i and j
            Collections.swap(playlist, i, j);
        }
    }
}
