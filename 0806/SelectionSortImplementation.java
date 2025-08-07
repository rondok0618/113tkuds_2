
import java.util.Arrays;

public class SelectionSortImplementation {

    public static void main(String[] args) {
        int[] originalData = {64, 25, 12, 22, 11};

        // 為了公平比較，分別複製原始資料
        int[] selectionData = Arrays.copyOf(originalData, originalData.length);
        int[] bubbleData = Arrays.copyOf(originalData, originalData.length);

        System.out.println("原始資料: " + Arrays.toString(originalData));

        System.out.println("\n--- 選擇排序 ---");
        performSelectionSort(selectionData);

        System.out.println("\n--- 氣泡排序 ---");
        performBubbleSort(bubbleData);
    }

    // 實作選擇排序
    public static void performSelectionSort(int[] arr) {
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                // 交換
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }

            System.out.printf("第 %d 輪: %s\n", i + 1, Arrays.toString(arr));
        }

        System.out.println("總比較次數: " + comparisons);
        System.out.println("總交換次數: " + swaps);
    }

    // 實作氣泡排序
    public static void performBubbleSort(int[] arr) {
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < arr.length - 1 - i; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    // 交換
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            System.out.printf("第 %d 輪: %s\n", i + 1, Arrays.toString(arr));

            if (!swapped) {
                break; // 若已排序好，可提前結束

            }
        }

        System.out.println("總比較次數: " + comparisons);
        System.out.println("總交換次數: " + swaps);
    }
}
