package leetcode.dfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class CanIWin {

    public static void main(String[] args) {
        var c = new  CanIWin();
        c.canIWin(3, 4);
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (maxChoosableInteger + 1) * (maxChoosableInteger) / 2;
        if (sum < desiredTotal) return false;

        boolean[] choices = new boolean[maxChoosableInteger + 1];
        return canIWinHelper(choices, desiredTotal, new HashMap<>());
    }

    private boolean canIWinHelper(boolean[] choices, int desiredTotal, Map<String, Boolean> memo) {
        var choiceKey = Arrays.toString(choices);
        if (memo.containsKey(choiceKey)) {
            return memo.get(choiceKey);
        }

        for (int i = 1; i < choices.length; i++) {
            if (choices[i]) continue;

            choices[i] = true;
            // let's see if I can win immediately:
            // OR if 2nd player cannot win
            var playerChoice = desiredTotal - i;
            if (playerChoice <= 0
                    || !canIWinHelper(choices, playerChoice, memo)) {
                memo.put(choiceKey, true); // winning configuration
                choices[i] = false;
                return true;
            }

            // ??
            memo.put(choiceKey, false);
            choices[i] = false;
        }

        return false;
    }
}
