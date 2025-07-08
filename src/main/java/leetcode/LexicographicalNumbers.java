package leetcode;

import java.util.ArrayList;
import java.util.List;

class LexicographicalNumbers {
    public static void main(String[] args) {
        LexicographicalNumbers lexicographicalNumbers = new LexicographicalNumbers();
        lexicographicalNumbers.lexicalOrder(10);
    }

    public List<Integer> lexicalOrder(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        if (n == 1) {
            return List.of(1);
        }

        List<Integer> res = new ArrayList<>();

        var curr = 1;
        for (int i = 0; i < n; i++) {
            res.add(curr);

            // go one level deep: meaning 10, 100
            if (curr * 10 <= n) { // has to include N
                curr = curr * 10;
            } else {
                // backtrack with WHILE if you reach 200 for example..
                while (curr % 10 == 9 || curr >= n) {
                    curr /= 10;
                }
                // or not
                curr++;
            }
        }
        return res;
    }
}
