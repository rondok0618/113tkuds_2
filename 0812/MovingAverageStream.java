
import java.util.*;

public class MovingAverageStream {

    private int size;
    private Queue<Integer> window;
    private double sum;
    private PriorityQueue<Integer> maxHeap; // smaller half
    private PriorityQueue<Integer> minHeap; // larger half
    private Map<Integer, Integer> delayed;  // lazy removal
    private TreeMap<Integer, Integer> treeMap; // for min/max

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.delayed = new HashMap<>();
        this.treeMap = new TreeMap<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;
        treeMap.put(val, treeMap.getOrDefault(val, 0) + 1);

        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }
        balanceHeaps();

        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            treeMap.put(removed, treeMap.get(removed) - 1);
            if (treeMap.get(removed) == 0) {
                treeMap.remove(removed);
            }
            delayed.put(removed, delayed.getOrDefault(removed, 0) + 1);

            if (removed <= maxHeap.peek()) {
                prune(maxHeap);
            } else {
                prune(minHeap);
            }
            balanceHeaps();
        }

        return Math.round((sum / window.size()) * 100.0) / 100.0;
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
    }

    public int getMin() {
        return treeMap.firstKey();
    }

    public int getMax() {
        return treeMap.lastKey();
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
            prune(maxHeap);
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
            prune(minHeap);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
        }
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println("ma.next(1) = " + ma.next(1));
        System.out.println("ma.next(10) = " + ma.next(10));
        System.out.println("ma.next(3) = " + ma.next(3));
        System.out.println("ma.next(5) = " + ma.next(5));
        System.out.println("ma.getMedian() = " + ma.getMedian());
        System.out.println("ma.getMin() = " + ma.getMin());
        System.out.println("ma.getMax() = " + ma.getMax());
    }
}
