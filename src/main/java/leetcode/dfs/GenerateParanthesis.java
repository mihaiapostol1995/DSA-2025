package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

class GenerateParanthesis {

    public static void main(String[] args) {
        var results = new ArrayList<String>();
        generateParenthesis(3, "", results, 0, 0);
    }

    public static void generateParenthesis(int n, String output, List<String> results, int left, int right) {
        if (left > n || right > left) {
            return;
        }
        if (left == n && right == n) {
            results.add(output);
        }

        generateParenthesis(n, output + ")", results, left, right + 1);
        generateParenthesis(n, output + "(", results, left + 1, right);
    }
}
