package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.ToDoubleFunction;

class KClosestPointsToTheOrigin {
    public static void main(String[] args) {
        kClosest(new int[][]{
                {3, 3}, {5, -1}, {-2, 4}
        }, 2);
//        kClosest(new int[][]{
//                {0, 1}, {1, 0}
//        }, 2);
//        kClosest(new int[][]{
//                {-3, 2}, {2, -2}, {-2, 2}
//        }, 2);
    }

    public static int[][] kClosest(int[][] points, int k) {
        Map<Double, List<int[]>> map = new HashMap<>();

        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            double distanceToOrigin = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

            List<int[]> orDefault = map.getOrDefault(distanceToOrigin, new ArrayList<>());
            orDefault.add(new int[]{x, y});
            map.put(distanceToOrigin, orDefault);
        }

        ToDoubleFunction<Point> objectToDoubleFunction = p -> p.distance;
        PriorityQueue<Point> minHeap = new PriorityQueue<>(Comparator.comparingDouble(objectToDoubleFunction).reversed());
        var keys = map.keySet();
        for (var key : keys) {
            List<int[]> ints = map.get(key);

            for (var point : ints) {
                if (minHeap.size() < k) {
                    minHeap.offer(new Point(point[0], point[1], key));
                } else {
                    Point peek = minHeap.peek();
                    if (peek.distance >  key) {
                        minHeap.poll();
                        minHeap.offer(new Point(point[0], point[1], key));
                    }
                }
            }
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            Point poll = minHeap.poll();
            result[i] = new int[]{poll.x, poll.y};
        }
        return result;
    }

    static class Point {
        int x;
        int y;
        double distance;
        public Point(int x, int y, double distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public static int[][] kClosestFast(int[][] points, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((p1, p2) -> {
            int multiplyFirst = p1[0] * p1[0] + p1[1] * p1[1];
            int multiplySecond = p2[1] * p2[1] + p2[0] * p2[0];
            return multiplySecond - multiplyFirst;
        });

        for (int[] point : points) {
            minHeap.offer(point);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }
}
