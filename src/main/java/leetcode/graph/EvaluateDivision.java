package leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class EvaluateDivision {
    public static void main(String[] args) {
        var ed = new EvaluateDivision();
        ed.calcEquation(List.of(List.of("x1", "x2"), List.of("x2", "x3"), List.of("x3", "x4"), List.of("x4", "x5")),
                new double[]{3.0, 4.0, 5.0, 6.0},
                List.of(List.of("x1", "x5"), List.of("x5", "x2"), List.of("x2", "x4")));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Pair>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> cur = equations.get(i);

            String first = cur.get(0);
            String second = cur.get(1);
            double val = values[i];

            graph.computeIfAbsent(first, k -> new ArrayList<>()).add(new Pair(second, val));
            graph.computeIfAbsent(second, k -> new ArrayList<>()).add(new Pair(first, 1 / val));
        }

        List<Double> res = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {
            List<String> cur = queries.get(i);

            String first = cur.get(0);
            String second = cur.get(1);

            if (!graph.containsKey(first) || !graph.containsKey(second)) {
                res.add(-1.0);
            } else if (first.equals(second)) {
                res.add(1.0);
            } else {
                Set<String> visited = new HashSet<>();
                double result = dfs(graph, first, second, visited);
                res.add(result);
            }
        }

        return res.stream().mapToDouble(d -> d).toArray();
    }

    private double dfs(Map<String, List<Pair>> graph, String first, String second, Set<String> visited) {
        //break;
        if (first.equals(second)) {
            return 1.0;
        }

        // we need VISITED to track that we do not go back!
        visited.add(first);

        for (Pair pair : graph.get(first)) {
            if (!visited.contains(pair.node())) {
                double dfs = dfs(graph, pair.node(), second, visited);
                if (dfs != -1.0) {
                    return pair.value() * dfs;
                }
            }
        }

        visited.remove(first);
        // bad path
        return -1.0;
    }
}

record Pair(String node, double value){}