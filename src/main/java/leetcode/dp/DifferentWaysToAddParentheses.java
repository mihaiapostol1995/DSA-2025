package leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DifferentWaysToAddParentheses {
    public static void main(String[] args) {
        DifferentWaysToAddParentheses solution = new DifferentWaysToAddParentheses();
        solution.diffWaysToCompute("2-1-1");
    }

    Map<String, List<Integer>> map = new HashMap<>();

    // split by half, only with memoization
    public List<Integer> diffWaysToCompute(String expression) {
        if (map.containsKey(expression)) {
            return map.get(expression);
        }
        if (!expression.contains("+") && !expression.contains("-") && !expression.contains("*")) {
            List<Integer> list = new ArrayList<>();
            list.add(Integer.valueOf(expression));
            map.put(expression, list);
            return list;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            var c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                var left = diffWaysToCompute(expression.substring(0, i));
                var right = diffWaysToCompute(expression.substring(i + 1));

                for (var leftNumber : left) {
                    for (var rightNumber : right) {
                        if (c == '+') {
                            result.add(leftNumber + rightNumber);
                        } else if (c == '-') {
                            result.add(leftNumber - rightNumber);
                        } else if (c == '*') {
                            result.add(leftNumber * rightNumber);
                        }
                    }
                }
            }
        }
        map.put(expression, result);
        return result;
    }
}
