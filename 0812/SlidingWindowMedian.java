
import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> maxHeap; // 左半邊（大根堆）
    private PriorityQueue<Integer> minHeap; // 右半邊（小根堆）

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new double[0];
        }
        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);

            if (i >= k) {
                remove(nums[i - k]);
            }

            if (i >= k - 1) {
                result[i - k + 1] = getMedian(k);
            }
        }

        return result;
    }

    private void add(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balance();
    }

    private void remove(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balance();
    }

    private void balance() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private double getMedian(int k) {
        if (k % 2 == 1) {
            return maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        {
            SlidingWindowMedian swm = new SlidingWindowMedian();
            int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
            int k1 = 3;
            double[] res1 = swm.medianSlidingWindow(nums1, k1);
            System.out.println("陣列：" + Arrays.toString(nums1) + ", K=" + k1);
            System.out.println("輸出：" + formatArray(res1));
            System.out.println("解釋：");
            for (int i = 0; i < res1.length; i++) {
                System.out.println(Arrays.toString(Arrays.copyOfRange(nums1, i, i + k1)) + " → 中位數 " + trimZero(res1[i]));
            }
        }

        System.out.println();

        {
            SlidingWindowMedian swm = new SlidingWindowMedian(); // ⚠ 新物件
            int[] nums2 = {1, 2, 3, 4};
            int k2 = 2;
            double[] res2 = swm.medianSlidingWindow(nums2, k2);
            System.out.println("陣列：" + Arrays.toString(nums2) + ", K=" + k2);
            System.out.println("輸出：" + formatArray(res2));
        }
    }

    // 美化輸出（整數不加小數點）
    private static String formatArray(double[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(trimZero(arr[i]));
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String trimZero(double val) {
        if (val == (long) val) {
            return String.format("%d", (long) val);
        } else {
            return String.format("%.1f", val);
        }
    }
}
