
import java.util.*;

public class StockMaximizer {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k == 0) {
            return 0;
        }

        // 小根堆：已選入的交易（保證不超過 k 筆）
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // 大根堆：候選交易
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int n = prices.length;
        int i = 0;
        int buy, sell;

        while (i < n) {
            // 找谷底
            while (i < n - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            buy = i;
            // 找山頂
            while (i < n - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            sell = i;

            if (buy < sell) {
                int profit = prices[sell] - prices[buy];
                if (minHeap.size() < k) {
                    minHeap.offer(profit);
                } else if (!minHeap.isEmpty() && profit > minHeap.peek()) {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(profit);
                } else {
                    maxHeap.offer(profit);
                }
            }
            i++;
        }

        // 把 minHeap 中所有已選交易利潤加總
        int totalProfit = 0;
        while (!minHeap.isEmpty()) {
            totalProfit += minHeap.poll();
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        StockMaximizer sm = new StockMaximizer();

        int[] p1 = {2, 4, 1};
        System.out.println("價格：" + Arrays.toString(p1) + ", K=2");
        System.out.println("答案：" + sm.maxProfit(2, p1) + " (在價格2時買入，在價格4時賣出)");

        int[] p2 = {3, 2, 6, 5, 0, 3};
        System.out.println("\n價格：" + Arrays.toString(p2) + ", K=2");
        System.out.println("答案：" + sm.maxProfit(2, p2) + " (在價格2時買入，在價格6時賣出得利潤4；在價格0時買入，在價格3時賣出得利潤3)");

        int[] p3 = {1, 2, 3, 4, 5};
        System.out.println("\n價格：" + Arrays.toString(p3) + ", K=2");
        System.out.println("答案：" + sm.maxProfit(2, p3) + " (一次交易：1買入5賣出)");
    }
}
