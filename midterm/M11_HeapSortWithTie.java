
import java.util.*;

public class M11_HeapSortWithTie {

    static class Pair {

        int score;
        int idx;

        Pair(int s, int i) {
            score = s;
            idx = i;
        }
    }

    static boolean greater(Pair a, Pair b) {
        if (a.score != b.score) {
            return a.score > b.score;
        }
        return a.idx > b.idx;
    }

    static void heapify(Pair[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && greater(arr[l], arr[largest])) {
            largest = l;
        }
        if (r < n && greater(arr[r], arr[largest])) {
            largest = r;
        }

        if (largest != i) {
            Pair tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            heapify(arr, n, largest);
        }
    }

    static void heapSort(Pair[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Pair tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            arr[i] = new Pair(s, i);
        }

        heapSort(arr, n);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score + (i == n - 1 ? "" : " "));
        }
        System.out.println();
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：
 * 1. 建立 Heap 需要 O(n)。
 * 2. 每次取出元素並重新 heapify，需 O(log n)，共 n 次 → O(n log n)。
 * 3. 總時間複雜度為 O(n log n)，其中 n 為學生人數。
 */
