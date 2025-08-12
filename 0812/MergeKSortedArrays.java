
import java.util.*;

public class MergeKSortedArrays {

    // 儲存 heap 中的元素資訊
    static class Element {

        int value;      // 元素值
        int arrayIndex; // 來自第幾個陣列
        int elementIndex; // 在該陣列中的位置

        Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();

        // Min Heap 根據 value 排序
        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));

        // 初始化：將每個陣列的第一個元素放入 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        // 取出 heap 最小值，然後放入該陣列的下一個元素
        while (!minHeap.isEmpty()) {
            Element e = minHeap.poll();
            result.add(e.value);

            int nextIndex = e.elementIndex + 1;
            if (nextIndex < arrays[e.arrayIndex].length) {
                minHeap.offer(new Element(arrays[e.arrayIndex][nextIndex], e.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        int[][] arr2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] arr3 = {{1}, {0}};

        System.out.println("輸入：" + Arrays.deepToString(arr1));
        System.out.println("輸出：" + mergeKSortedArrays(arr1));

        System.out.println("\n輸入：" + Arrays.deepToString(arr2));
        System.out.println("輸出：" + mergeKSortedArrays(arr2));

        System.out.println("\n輸入：" + Arrays.deepToString(arr3));
        System.out.println("輸出：" + mergeKSortedArrays(arr3));
    }
}
