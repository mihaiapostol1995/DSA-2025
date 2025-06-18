package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class QueueReconstructionByHeight {
    public static void main(String[] args) {
        int[][] people = {
                {7, 0},
                {4, 4},
                {7, 1},
                {5, 0},
                {6, 1},
                {5, 2}
        };

        int[][] ints = reconstructQueue(people);
        System.out.println(Arrays.deepToString(ints));
    }

    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else return o2[0] - o1[0];
        });

        var list = new ArrayList<int[]>();
        for (int[] ints : people) {
            int x = ints[0], y = ints[1];

            list.add(y, new int[]{x, y});
        }

        return list.toArray(new int[people.length][]);
    }
}
