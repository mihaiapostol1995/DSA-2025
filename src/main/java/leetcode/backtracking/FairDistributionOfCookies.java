package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FairDistributionOfCookies {
    public static void main(String[] args) {
        var f =  new FairDistributionOfCookies();
        int i = f.distributeCookies(new int[]{6, 1, 3, 2, 2, 4, 1, 2}, 3);
        System.out.println(i);
    }

    private int min = Integer.MAX_VALUE;

    public int distributeCookies(int[] cookies, int k) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> bags = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            bags.add(0);
        }
        distributeCookiesHelper(cookies, bags, visited);
        return min;
    }

    void distributeCookiesHelper(int[] cookies, List<Integer> bags, Set<Integer> visited) {
        if (visited.size() == cookies.length) {
            // all cookies were distributed
            List<Integer> newBags = new ArrayList<>();
            newBags.addAll(bags);
            Collections.sort(newBags);

            var max = newBags.getLast();
            min = Math.min(min, max);
        }

        for (int i = 0; i < cookies.length; i++) {
            if (!visited.contains(i)) {
                // for each child's bags
                for (int j = 0; j < bags.size(); j++) {
                    var initialBag = bags.get(j);
                    var newBag = cookies[i] + initialBag;
                    bags.set(j, newBag);

                    // backtrack
                    visited.add(i);
                    distributeCookiesHelper(cookies, bags, visited);
                    visited.remove(i);
                    bags.set(j, initialBag);
                }
            }
        }
    }
}
