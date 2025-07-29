package leetcode.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ShoppingOffers {
    public static void main(String[] args) {
        var s = new ShoppingOffers();

        ArrayList<Integer> price = new ArrayList<>();
        price.add(2);
        price.add(5);

        List<List<Integer>> special = new ArrayList<>();
        ArrayList<Integer> offer1 = new ArrayList<>();
        offer1.add(3);
        offer1.add(0);
        offer1.add(5);
        special.add(offer1);

        ArrayList<Integer> offer2 = new ArrayList<>();
        offer2.add(1);
        offer2.add(2);
        offer2.add(10);
        special.add(offer2);

        ArrayList<Integer> needs = new ArrayList<>();
        needs.add(3);
        needs.add(2);

        int i = s.shoppingOffers(price, special, needs, new HashMap<>());
        System.out.println(i);
    }

    // the needs are like a visited array
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> map) {
        if (map.containsKey(price)) {
            return map.get(price);
        }

        int minCost = 0;
        for (int i = 0; i < needs.size(); i++) {
            minCost += price.get(i) * needs.get(i);
        }

        for (int i = 0; i < special.size(); i++) {
            List<Integer> specialPrice = special.get(i);

            boolean isValid = true;
            for (int j = 0; j < specialPrice.size() - 1; j++) {
                // if the offer gives us more elements than we need
                if (specialPrice.get(j) > needs.get(j)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                // update the needs
                for (int j = 0; j < specialPrice.size() - 1; j++) {
                    // lower the needs with the elements from the offer
                    needs.set(j, needs.get(j) - specialPrice.get(j));
                }

                //dfs
                minCost = Math.min(minCost,
                        // actual price of these iitems
                        specialPrice.getLast() + shoppingOffers(price, special, needs, map));

                // backtrack
                for (int j = 0; j < specialPrice.size() - 1; j++) {
                    // lower the needs with the elements from the offer
                    needs.set(j, specialPrice.get(j) + needs.get(j));
                }
            }
        }

        map.put(price, minCost);
        return minCost;
    }
}
