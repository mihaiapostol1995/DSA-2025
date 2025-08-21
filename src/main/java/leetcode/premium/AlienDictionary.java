package leetcode.premium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class AlienDictionary {
    public static void main(String[] args) {
        var solver = new AlienDictionary();
//        a.alienOrder()
        System.out.println(solver.alienOrder(new String[]{"wrt","wrf","er","ett","rftt"})); // "wertf"
        System.out.println(solver.alienOrder(new String[]{"z","x"})); // "zx"
        System.out.println(solver.alienOrder(new String[]{"z","x","z"})); // ""
    }

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> precedenceGraph = new HashMap<>();
        Map<Character, Integer> indegrees = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegrees.putIfAbsent(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            var word = words[i];
            var nextWord = words[i + 1];
            for (int j = 0; j < Math.min(word.length(), nextWord.length()); j++) {
                char firstWordChar = word.charAt(j);
                char secondWordChar = nextWord.charAt(j);
                if (firstWordChar != secondWordChar) {
                    precedenceGraph
                            .computeIfAbsent(firstWordChar, _ -> new HashSet<>())
                            .add(secondWordChar);
                    indegrees.put(secondWordChar, indegrees.get(secondWordChar) + 1);
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (var entrySet: indegrees.entrySet()) {
            if (entrySet.getValue() == 0) {
                queue.offer(entrySet.getKey());
//                break; a -> b, c -> b
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            var node = queue.poll();
            sb.append(node);

            var neighbours = precedenceGraph.get(node);
            if (neighbours != null) {
                for (var neighbour: neighbours) {
                    var i = indegrees.get(neighbour);
                    i--;
                    indegrees.put(neighbour, i);
                    if (i == 0) {
                        queue.offer(neighbour);
                    }
                }
            }
        }
        if (sb.length() != indegrees.size()) return "";
        return sb.toString();
    }
}
