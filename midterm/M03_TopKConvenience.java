
import java.util.*;

public class M03_TopKConvenience {

    static class Item {

        String name;
        int qty;

        Item(String n, int q) {
            name = n;
            qty = q;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();

        PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) {
                    return a.qty - b.qty;

                }
                return a.name.compareTo(b.name);
            }
        });

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();

            pq.offer(new Item(name, qty));
            if (pq.size() > K) {
                pq.poll();

            }
        }

        List<Item> list = new ArrayList<>(pq);
        list.sort(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (b.qty != a.qty) {
                    return b.qty - a.qty;

                }
                return a.name.compareTo(b.name);
            }
        });

        for (Item item : list) {
            System.out.println(item.name + " " + item.qty);
        }
    }
}
/*
 * Time Complexity: O(n log K)
 * 說明：逐一讀入 n 筆資料，每筆在大小 K 的最小堆中插入或更新，花費 O(log K)，
 *       總共 O(n log K)。輸出時對 K 筆資料排序 O(K log K)，K ≪ n 時可忽略。
 */
