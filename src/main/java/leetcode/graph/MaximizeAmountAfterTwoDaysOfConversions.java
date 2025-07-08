package leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MaximizeAmountAfterTwoDaysOfConversions {

    public static void main(String[] args) {
        var ma = new MaximizeAmountAfterTwoDaysOfConversions();
        double v = ma.maxAmount("EUR", List.of(List.of("EUR", "USD"), List.of("USD", "JPY")), new double[]{2.0, 3.0},
                List.of(List.of("JPY", "USD"), List.of("USD", "CHF"), List.of("CHF", "EUR")), new double[]{4.0, 5.0, 6.0});
        System.out.println(v);
    }


    public double maxAmount(String initialCurrency, List<List<String>> pairs1,
                            double[] rates1, List<List<String>> pairs2, double[] rates2) {
        Map<String, List<CurrencyRate>> graph1 = new HashMap<>();

        // first day
        for (int i = 0; i < pairs1.size(); i++) {
            var pair = pairs1.get(i);

            var first = pair.getFirst();
            var second = pair.get(1);
            var rate =  rates1[i];

            graph1.computeIfAbsent(first, k -> new ArrayList<>()).add(new CurrencyRate(second, rate));
            graph1.computeIfAbsent(second, k -> new ArrayList<>()).add(new CurrencyRate(first, 1.0 / rate));
        }

        // econd day
        Map<String, List<CurrencyRate>> graph2 = new HashMap<>();
        for (int i = 0; i < pairs2.size(); i++) {
            var pair =  pairs2.get(i);

            var first = pair.getFirst();
            var second = pair.get(1);
            var rate =  rates2[i];

            graph2.computeIfAbsent(first, k -> new ArrayList<>()).add(new CurrencyRate(second, rate));
            graph2.computeIfAbsent(second, k -> new ArrayList<>()).add(new CurrencyRate(first, 1.0 / rate));
        }

        Map<String, Double> rates = new HashMap<>();
        dfs(initialCurrency, 1.0, graph1, rates);

        // WHAT THE FUCK
        double maxAmount = 0.0;
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            Map<String, Double> temp = new HashMap<>();
            dfs(entry.getKey(), entry.getValue(), graph2, temp);
            maxAmount = Math.max(maxAmount, temp.getOrDefault(initialCurrency, 0.0));
        }

        return maxAmount;
    }

    private void dfs(String initialCurrency, double initialMoney, Map<String, List<CurrencyRate>> graph,
                     Map<String, Double> rates) {
        // this is like VISITED
        if (rates.containsKey(initialCurrency)) {
            return;
        }

        rates.put(initialCurrency, initialMoney);

        for (var pair: graph.getOrDefault(initialCurrency, new ArrayList<>())) {
            dfs(pair.currency(), pair.rate() * initialMoney, graph, rates);
        }
    }
}

record CurrencyRate(String currency, double rate) {}