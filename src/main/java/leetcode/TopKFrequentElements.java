package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class TopKFrequentElements {

    public static void main(String[] args) {
//        topKElements(new int[]{0, 1, 3, 0}, 2);
        topKElements(new int []{1,1,1,2,2,3}, 2);
    }

    static int[] topKElements(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<FreqToValue> minHeap = new PriorityQueue<>();

        Set<Integer> integers = frequencyMap.keySet();
        int count = 0;
        for (Integer integer : integers) {
            if (count < k) {
                minHeap.add(new FreqToValue(frequencyMap.get(integer), integer));
            } else {
                FreqToValue peek = minHeap.peek();
                if (peek.freq < frequencyMap.get(integer)) {
                    minHeap.poll();
                    minHeap.add(new FreqToValue(frequencyMap.get(integer), integer));
                }
            }

            count++;
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().value;
        }
        return result;
    }

    static class FreqToValue implements Comparable<FreqToValue> {
        int freq;
        int value;

        public FreqToValue(int i, int num) {
            freq = i;
            value = num;
        }

        @Override
        public int compareTo(FreqToValue o) {
           return Integer.compare(freq, o.freq);
        }
    }
}
