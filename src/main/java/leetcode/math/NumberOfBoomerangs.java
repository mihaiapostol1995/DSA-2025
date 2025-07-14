package leetcode.math;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class NumberOfBoomerangs {
    public static void main(String[] args) {

    }

    public int numberOfBoomerangs(int[][] points) {
        int result = 0;

        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> distance = new HashMap<>();

            int[] coordinates = points[i];
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int[] newCoordinates = points[j];

                double dx = Math.pow((coordinates[0] - newCoordinates[1]), 2);
                double dy = Math.pow((coordinates[1] - newCoordinates[0]), 2);
                var sum = dx + dy;
                distance.put(sum, distance.getOrDefault(sum, 0) + 1);
            }

            var results = (Integer) distance.values()
                    .stream()
                    .filter(integer -> integer > 1)
                    .map(integer -> integer * (integer - 1))
                    .mapToInt(x -> x)
                    .sum();
            result += results;
        }
        return result;
    }
}
