package leetcode.array;

import java.util.HashMap;
import java.util.Map;

class BullsAndCows {
    public static void main(String[] args) {
        BullsAndCows b = new BullsAndCows();
        String hint = b.getHint("1807", "7810");
        System.out.println(hint);
    }

    // OR use array: int[] h = new int[10]; and increment  / decrement the counts
    public String getHint(String secret, String guess) {
        var bullsCount = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            char secretChar = secret.charAt(i);
            if (secretChar == guess.charAt(i)) {
                bullsCount++;
            } else {
                map.put(secretChar, map.getOrDefault(secretChar, 0) + 1);
            }
        }

        var cowsCount = 0;
        for (int j = 0; j < guess.length(); j++) {
            if (secret.charAt(j) != guess.charAt(j)
                    && map.getOrDefault(guess.charAt(j), 0) > 0) {
                    cowsCount++;
                    map.put(guess.charAt(j), map.get(guess.charAt(j)) - 1);
                }
        }
        return bullsCount + "A" + cowsCount + "B";
    }
}
