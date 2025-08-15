package versions;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Gatherer;

public class GatherersExample {
    
    public static void main(String[] args) {
        String name = "Udvuleanu";
        
        System.out.println("=== Java 24 Gatherers Examples with '" + name + "' ===\n");
        
        // Example 1: Custom sliding window gatherer
        System.out.println("1. Sliding Window (size 3) of characters:");
        name.chars()
            .mapToObj(c -> (char) c)
            .gather(slidingWindow(3))
            .forEach(window -> System.out.println("  " + window));
        
        System.out.println();
        
        // Example 2: Custom grouping gatherer with character frequency
        System.out.println("2. Character frequency analysis:");
        Map<Character, Integer> charFreq = name.toLowerCase().chars()
            .mapToObj(c -> (char) c)
            .gather(frequencyCounter())
            .findFirst()
            .orElse(Map.of());
        
        charFreq.entrySet().stream()
            .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
            .forEach(entry -> System.out.println("  '" + entry.getKey() + "': " + entry.getValue()));
        
        System.out.println();
        
        // Example 3: Custom batching gatherer
        System.out.println("3. Batching characters into groups of 3:");
        name.chars()
            .mapToObj(c -> (char) c)
            .gather(batch(3))
            .forEach(batch -> System.out.println("  Batch: " + batch));
        
        System.out.println();
        
        // Example 4: Custom stateful gatherer - running character positions
        System.out.println("4. Character positions with running index:");
        name.chars()
            .mapToObj(c -> (char) c)
            .gather(withIndex())
            .forEach(indexed -> System.out.println("  Position " + indexed.index() + ": '" + indexed.value() + "'"));
        
        System.out.println();
        
        // Example 5: Custom transformation gatherer - character codes
        System.out.println("5. Transform to character codes with differences:");
        List<Integer> charCodes = name.chars()
            .boxed()
            .gather(withDifferences())
            .toList();
        
        for (int i = 0; i < charCodes.size(); i++) {
            char c = (char) charCodes.get(i).intValue();
            System.out.println("  '" + c + "' -> " + charCodes.get(i) + 
                (i > 0 ? " (diff: " + (charCodes.get(i) - charCodes.get(i-1)) + ")" : ""));
        }
    }
    
    // Custom Gatherer 1: Sliding Window
    public static <T> Gatherer<T, ?, List<T>> slidingWindow(int windowSize) {
        return Gatherer.<T, List<T>, List<T>>ofSequential(
            ArrayList::new,  // initializer
            (window, element, downstream) -> {
                window.add(element);
                if (window.size() > windowSize) {
                    window.remove(0);
                }
                if (window.size() == windowSize) {
                    return downstream.push(new ArrayList<>(window));
                }
                return true;
            }
        );
    }
    
    // Custom Gatherer 2: Frequency Counter
    public static <T> Gatherer<T, ?, Map<T, Integer>> frequencyCounter() {
        return Gatherer.<T, Map<T, Integer>, Map<T, Integer>>ofSequential(
            HashMap::new,  // initializer
            (frequencies, element, downstream) -> {
                frequencies.merge(element, 1, Integer::sum);
                return true;
            },
            (frequencies, downstream) -> {
                downstream.push(Map.copyOf(frequencies));
            }
        );
    }
    
    // Custom Gatherer 3: Batching
    public static <T> Gatherer<T, ?, List<T>> batch(int batchSize) {
        return Gatherer.<T, List<T>, List<T>>ofSequential(
            ArrayList::new,  // initializer
            (batch, element, downstream) -> {
                batch.add(element);
                if (batch.size() == batchSize) {
                    boolean success = downstream.push(new ArrayList<>(batch));
                    batch.clear();
                    return success;
                }
                return true;
            },
            (batch, downstream) -> {
                if (!batch.isEmpty()) {
                    downstream.push(new ArrayList<>(batch));
                }
            }
        );
    }
    
    // Custom Gatherer 4: With Index
    public static <T> Gatherer<T, ?, IndexedValue<T>> withIndex() {
        return Gatherer.<T, int[], IndexedValue<T>>ofSequential(
            () -> new int[]{0},  // initializer - using array to make it mutable
            (state, element, downstream) -> {
                boolean success = downstream.push(new IndexedValue<>(state[0], element));
                state[0]++;
                return success;
            }
        );
    }
    
    // Custom Gatherer 5: With Differences (for integers)
    public static Gatherer<Integer, ?, Integer> withDifferences() {
        return Gatherer.<Integer, Integer[], Integer>ofSequential(
            () -> new Integer[]{null},  // initializer - previous value
            (state, element, downstream) -> {
                boolean success = downstream.push(element);
                state[0] = element;
                return success;
            }
        );
    }
    
    // Helper record for indexed values
    public record IndexedValue<T>(int index, T value) {}
}

// Note: This example uses conceptual Gatherer API that may differ from final Java 24 implementation
// The actual API might have different method signatures or patterns