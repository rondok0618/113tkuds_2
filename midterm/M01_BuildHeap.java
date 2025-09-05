
import java.util.*;

public class M01_BuildHeap {

    static String type;
    static int n;
    static int[] heap;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        type = sc.next();
        n = sc.nextInt();
        heap = new int[n];
        for (int i = 0; i < n; i++) {
            heap[i] = sc.nextInt();
        }

        buildHeap();

        for (int i = 0; i < n; i++) {
            System.out.print(heap[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
    }

    static void buildHeap() {

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    static void heapifyDown(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int target = i;

        if (type.equals("max")) {
            if (left < n && heap[left] > heap[target]) {
                target = left;
            }
            if (right < n && heap[right] > heap[target]) {
                target = right;
            }
        } else {
            if (left < n && heap[left] < heap[target]) {
                target = left;
            }
            if (right < n && heap[right] < heap[target]) {
                target = right;
            }
        }

        if (target != i) {
            int tmp = heap[i];
            heap[i] = heap[target];
            heap[target] = tmp;
            heapifyDown(target);
        }
    }
}
/*
 * Time Complexity: O(n)
 * 說明：建堆從最後一個非葉節點開始向下調整，每個節點的 heapifyDown 花費與其高度成正比。
 *       總和為 n/2 * 1 + n/4 * 2 + n/8 * 3 + ... = O(n)。
 */
