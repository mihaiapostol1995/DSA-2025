package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

class AllPathsFromSourceToTarget {

    // this also can be done with backtracking...
    public static void main(String[] args) {
        int[][] array = {
                {4, 3, 1},
                {3, 2, 4},
                {3},
                {4},
                {}};

        List<List<Integer>> paths = new ArrayList<>();
//        allPathsSourceTarget(array, paths);
//        allPathsSourceTarget(new int[][]{
//                {2}, {}, {1}
//        }, paths);

        //
        int[][] array2 = {
                {1, 2},
                {3},
                {3},
                {}
        };
        var list = new ArrayList<Integer>();
        list.add(0);
        allPathsSourceTarget2(array2, 0, list, paths);
    }

    public static void allPathsSourceTarget(int[][] graph, List<List<Integer>> paths) {
        List<List<Integer>> nodes = new ArrayList<>();

        for (int i = 0; i < graph.length; i++) {
            List<Integer> node = new ArrayList<>();
            if (graph[i].length == 0 ) {
                nodes.add(node);
                continue;
            }
            for (int j = 0; j < graph[i].length; j++) {
                node.add(graph[i][j]);
            }
            nodes.add(node);
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);
        findEnd(nodes, 0, list, paths);
    }

    static void findEnd(List<List<Integer>> graph, int start, List<Integer> list, List<List<Integer>> paths) {
        List<Integer> integers = graph.get(start);
        if (integers == null || integers.isEmpty()) {
            if (start == graph.size() - 1) {
                paths.add(new ArrayList<>(list));
            }
            return;
        } else if (start == graph.size() - 1) {
            paths.add(new ArrayList<>(list));
            return;
        }

        for (int index : integers) {
            var newList = new ArrayList<>(list);
            newList.add(index);
            findEnd(graph, index, newList, paths);
        }
    }

    public static void allPathsSourceTarget2(int[][] graph, int node, List<Integer> path, List<List<Integer>> paths) {
        if (node == graph.length - 1) {
            var list = new ArrayList<>(path);
            paths.add(list);
            return;
        }

        for (int n : graph[node]) {
            path.add(n);
            allPathsSourceTarget2(graph, n, path, paths);
            path.remove(path.size() - 1);
        }
    }
}
