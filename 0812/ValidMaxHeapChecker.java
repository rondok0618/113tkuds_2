
import java.util.Arrays;

public class ValidMaxHeapChecker {

    public static String checkMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[left] > arr[i]) {
                return Arrays.toString(arr) + " → false (索引" + left + "的" + arr[left] + "大於父節點" + arr[i] + ")";
            }
            if (right < n && arr[right] > arr[i]) {
                return Arrays.toString(arr) + " → false (索引" + right + "的" + arr[right] + "大於父節點" + arr[i] + ")";
            }
        }
        return Arrays.toString(arr) + " → true";
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };

        for (int[] arr : testCases) {
            System.out.println(checkMaxHeap(arr));
        }
    }
}
