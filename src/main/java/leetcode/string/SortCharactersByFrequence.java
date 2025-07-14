package leetcode.string;

import java.util.Comparator;
import java.util.PriorityQueue;

class SortCharactersByFrequence {

    public static void main(String[] args) {
        var f = new  SortCharactersByFrequence();
        String s = f.frequencySort("tree");
        System.out.println(s);
    }

    public String frequencySort(String s) {
        int[] freq = new int[128];
        for (char c : s.toCharArray()) {
            freq[c]++;
        }

        PriorityQueue<Freq> freqs = new PriorityQueue<>(Comparator.comparing((Freq f) -> f.freq).reversed());
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                freqs.offer(new Freq(String.valueOf((char) (i)), freq[i]));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!freqs.isEmpty()) {
            var f = freqs.poll();
            sb.append(String.valueOf(f.s)
                    .repeat(Math.max(0, f.freq)));
        }
        return sb.toString();
    }

    record Freq(String s, int freq) {}
}
