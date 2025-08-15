package leetcode.hard;

import java.util.PriorityQueue;

// didn't know, revisit
class FindMedianFromDataStream {
    public static void main(String[] args) {
        var f = new MedianFinder();
        f.addNum(2);
        f.addNum(3);
        f.addNum(4);
        f.addNum(5);
    }
}

class MedianFinder {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        minHeap.add(num);

        maxHeap.add(minHeap.poll());

        if (maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else if (minHeap.size() < maxHeap.size()) {
            return maxHeap.peek();
        } else {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }
}
