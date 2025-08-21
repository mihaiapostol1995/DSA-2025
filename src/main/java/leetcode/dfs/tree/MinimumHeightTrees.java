package leetcode.dfs.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class MinimumHeightTrees {
    public static void main(String[] args) {
        MinimumHeightTrees minimumHeightTrees = new MinimumHeightTrees();
        int[][] grid = {
                {1, 0},
                {1, 2},
                {1, 3}
        };
        minimumHeightTrees.findMinHeightTrees(4, grid);
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Create adjacency list
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        // Add edges (both directions since undirected)
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).size() == 1) {
                // these are the leaves
                queue.add(i);
            }
        }

        int total = n;

        while (total > 2) {
            total = total - queue.size();

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                var cur = queue.poll();
                var neighbours = graph.get(cur);
                // single neighbour, this is why we call just once next
                var next = neighbours.iterator().next();
                Set<Integer> integers = graph.get(next);
                integers.remove(cur);
                // single neighbour
                if (integers.size() == 1) {
                    queue.add(next);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            var cur = queue.poll();
            res.add(cur);
        }
        return res;
    }
}
