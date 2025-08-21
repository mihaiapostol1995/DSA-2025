package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class NetworkDelayTime {
    public static void main(String[] args) {
        var n = new NetworkDelayTime();
        int[][] arr2 = {
                {1, 2, 1},
                {2, 1, 3}
        };

        int[][] arr = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        n.networkDelayTime(arr, 4, 2);
    }


    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.computeIfAbsent(edge[0], _ -> new ArrayList<>())
                    .add(new int[]{edge[1], edge[2]});
        }

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);

        while (!queue.isEmpty()) {
            var sourceNode = queue.poll();

            if (graph.get(sourceNode) == null) continue;

            for (var targetValue : graph.get(sourceNode)) {
                int targetNode = targetValue[0];
                int distance = targetValue[1];
                if (distance + distances[sourceNode] < distances[targetNode]) {
                    distances[targetNode] = distance + distances[sourceNode];
                    queue.add(targetNode);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < distances.length; i ++) {
            if (distances[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, distances[i]);
        }
        return max;
    }
}
