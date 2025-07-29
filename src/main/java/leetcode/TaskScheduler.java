package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

class TaskScheduler {

    public static void main(String[] args) {
        var t = new TaskScheduler();
        char[] tasks = {'B', 'C', 'D', 'A', 'A', 'A', 'A', 'G'};
        t.leastInterval(tasks, 2);
    }

    public int leastInterval(char[] tasks, int n) {
        TreeSet<Character> set = new TreeSet<>();
        for (char task : tasks) {
            set.add(task);
        }

        Map<Integer, Character> map = new HashMap<>();
        int count = 0;
        for (var c : set) {
            map.put(count++, c);
        }

        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            counter.put(tasks[i], counter.getOrDefault(tasks[i], 0) + 1);
        }

        int intervalCount = 0;
        int extracted = tasks.length;

        while (extracted > 0) {
            if (map.containsKey(intervalCount)) {
                var c = map.get(intervalCount);
                var characterCount = counter.getOrDefault(c, 0);

                if (characterCount > 0) {
                    counter.put(c, counter.get(c) - 1);
                    map.put(intervalCount + n + 1, c);
                }
                extracted--;
            }

            intervalCount++;
        }

        return intervalCount;
    }
}

// GOOD problem!
class TaskScheduler2 {

    record CooldownTask(int remainingCount, int availableTime) {}

    // example: 3, 1, 1
    public int leastInterval(char[] tasks, int n) {
        // Count task frequencies
        Map<Character, Integer> taskCount = new HashMap<>();
        for (char task : tasks) {
            taskCount.put(task, taskCount.getOrDefault(task, 0) + 1);
        }

        // Max-heap to prioritize tasks with the highest frequency
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(taskCount.values());

        // Queue to manage cooldown tasks
        Queue<CooldownTask> cooldownQueue = new LinkedList<>();

        int time = 0;

        while (!maxHeap.isEmpty() || !cooldownQueue.isEmpty()) {
            time++;

            // Check if any task in cooldown is ready to be scheduled
            if (!cooldownQueue.isEmpty() && cooldownQueue.peek().availableTime() == time) {
                maxHeap.offer(cooldownQueue.poll().remainingCount());
            }

            // Schedule the next task from the max-heap
            if (!maxHeap.isEmpty()) {
                int remaining = maxHeap.poll() - 1;
                if (remaining > 0) {
                    cooldownQueue.offer(new CooldownTask(remaining, time + n + 1));
                }
            }
        }

        return time;
    }

    public static void main(String[] args) {
        TaskScheduler2 scheduler = new TaskScheduler2();
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        System.out.println(scheduler.leastInterval(tasks, n)); // Output: 8
    }
}

class TaskScheduler3 {

    public static void main(String[] args) {
        var t = new TaskScheduler3();
        char[] tasks = {'B', 'C', 'D', 'A', 'A', 'A', 'A', 'G'};
        int i = t.leastInterval(tasks, 1);
        System.out.println(i);

        // priority queue
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(1);
        heap.offer(2);
        heap.offer(3);
        heap.offer(4);
        heap.offer(5);
        heap.poll();
        System.out.println(heap);
    }

    public int leastInterval(char[] tasks, int n) {
        // Count task frequencies
        int[] frequencies = new int[26];
        for (char task : tasks) {
            frequencies[task - 'A']++;
        }

        // PriorityQueue sorted by nextTime (ascending)
        PriorityQueue<Task> pq = new PriorityQueue<>((a, b) ->
        {
//            if (a.nextTime == b.nextTime && a.remainingCount != b.remainingCount) {
//                return a.remainingCount - b.remainingCount; // higher frequency has priority
//            } else {
                return a.nextTime - b.nextTime;
//            }
        });

        // Add all tasks with their frequencies
        int count = 0;
        for (int freq : frequencies) {
            if (freq > 0) {
                pq.offer(new Task(freq, count++));
            }
        }

        int time = 0;

        while (!pq.isEmpty()) {
            if (pq.peek().nextTime > time) {
                time++;
                continue;
            }

            time++;
            // Process the task
            Task current = pq.poll();
            current.remainingCount--;
            // Reinsert the task if it has remaining executions
            if (current.remainingCount > 0) {
                current.nextTime = time + n;
                pq.offer(current);
            }
        }

        return time;
    }

    static class Task {
        int remainingCount;
        int nextTime;

        Task(int remainingCount, int nextTime) {
            this.remainingCount = remainingCount;
            this.nextTime = nextTime;
        }
    }
}


// my implementation
class TaskScheduler4 {

    public static void main(String[] args) {

    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> taskCount = new HashMap<>();
        for (char task : tasks) {
            taskCount.put(task, taskCount.getOrDefault(task, 0) + 1);
        }

        // running tasks
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(taskCount.values());

        // remaining tasks
        Queue<CooldownTask> cooldownQueue = new LinkedList<>();

        int time = 0;
        while (!maxHeap.isEmpty()) {
            time++;

            if (!cooldownQueue.isEmpty() && cooldownQueue.peek().availableTime() == time) {
                maxHeap.offer(cooldownQueue.poll().remainingCount());
            }

            if (!maxHeap.isEmpty()) {
                int remaining = maxHeap.poll() - 1;
                if (remaining > 0) {
                    cooldownQueue.offer(new CooldownTask(remaining, time + n + 1));
                }
            }
        }
        return time;
    }

    record CooldownTask(int remainingCount, int availableTime) {}
}