package leetcode.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class MinimumGeneticMutation {

    public static void main(String[] args) {
        var m = new MinimumGeneticMutation();
        int i = m.minMutation("AACCGGTT", "AACCGCTA", new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"});
        System.out.println(i);
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        Queue<String> gene = new LinkedList<>();
        gene.add(startGene);

        // prevent cycles
        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        int count = -1;
        while (!gene.isEmpty()) {
            count++;

            int size = gene.size();
            for (int i = 0; i < size; i++) {
                var cur = gene.poll();
                if (cur.equals(endGene)) {
                    return count;
                }

                for (var b : bank) {
                    var isValid = isValid(b, cur) && !visited.contains(b);
                    if (isValid) {
                        gene.offer(b);
                        visited.add(b);
                    }
                }
            }
        }

        return -1;
    }

    private boolean isValid(String gene, String cur) {
        var count = 0;
        for (int i = 0; i < gene.length(); i++) {
            if (gene.charAt(i) != cur.charAt(i)) {
                count++;
                if (count > 1) {
                   return false;
                }
            }
        }
        return true;
    }
}
