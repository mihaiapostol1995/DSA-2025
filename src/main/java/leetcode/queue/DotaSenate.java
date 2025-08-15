package leetcode.queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class DotaSenate {
    public static void main(String[] args) {
        DotaSenate d = new DotaSenate();
        d.predictPartyVictory("DDRRR");
    }
    public String predictPartyVictory(String senate) {
        if (senate.length() < 2) {
            return switch (senate.charAt(0)) {
                case 'R' -> "Radiant";
                case 'D' -> "Dire";
                default -> "";
            };
        }

        Queue<Character> winners = new LinkedList<>();

        Map<Character, Integer> freq = new HashMap<>();

        for (int i = 0; i < senate.length(); i++) {
            winners.add(senate.charAt(i));
            freq.put(senate.charAt(i),
                    freq.getOrDefault(senate.charAt(i), 0) + 1);
        }

        int banR = 0;
        int banD = 0;
        while (winners.size() > 1) {
            var i = freq.get('R');
            if (i == 0) {
                return "Dire";
            }
            var j = freq.get('D');
            if (j == 0) {
                return "Radiant";
            }

            var first = winners.poll();
            if (first.equals('R') && banR > 0) {
                banR--;
                freq.put('R', freq.getOrDefault(first, 0) - 1);
            } else if (first.equals('D') && banD > 0) {
                banD--;
                freq.put('D', freq.getOrDefault(first, 0) - 1);
            } else {
                if (first.equals('R')) {
                    banD++;
                } else if (first.equals('D')) {
                    banR++;
                }
                winners.offer(first);
            }
        }

        return switch (winners.poll()) {
            case 'R' -> "Radiant";
            case 'D' -> "Dire";
            default -> "";
        };
    }
}
