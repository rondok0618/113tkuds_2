
import java.util.Arrays;

public class AdvancedArrayRecursion {

    public static void main(String[] args) {
        int[] array = {7, 2, 1, 8, 6, 3, 5, 4};

        System.out.println("原始陣列: " + Arrays.toString(array));
        quickSort(array, 0, array.length - 1);
        System.out.println("快速排序後: " + Arrays.toString(array));

        int[] sortedA = {1, 3, 5};
        int[] sortedB = {2, 4, 6, 8};
        int[] merged = merge(sortedA, sortedB, 0, 0);
        System.out.println("\n合併後陣列: " + Arrays.toString(merged));

        int[] sample = {10, 3, 5, 2, 8, 6, 1};
        int k = 4;
        int kth = quickSelect(sample, 0, sample.length - 1, k);
        System.out.printf("\n第 %d 小元素是: %d\n", k, kth);

        int[] nums = {3, 34, 4, 12, 5, 2};
        int target = 9;
        boolean exists = subsetSum(nums, nums.length - 1, target);
        System.out.printf("\n是否存在總和為 %d 的子序列？ %s\n", target, exists ? "是" : "否");
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] merge(int[] a, int[] b, int i, int j) {
        if (i == a.length) {
            return Arrays.copyOfRange(b, j, b.length);
        }
        if (j == b.length) {
            return Arrays.copyOfRange(a, i, a.length);
        }

        if (a[i] < b[j]) {
            int[] rest = merge(a, b, i + 1, j);
            return prepend(a[i], rest);
        } else {
            int[] rest = merge(a, b, i, j + 1);
            return prepend(b[j], rest);
        }
    }

    public static int[] prepend(int value, int[] arr) {
        int[] result = new int[arr.length + 1];
        result[0] = value;
        System.arraycopy(arr, 0, result, 1, arr.length);
        return result;
    }

    public static int quickSelect(int[] arr, int low, int high, int k) {
        if (low == high) {
            return arr[low];
        }

        int pi = partition(arr, low, high);
        int count = pi - low + 1;

        if (k == count) {
            return arr[pi];
        } else if (k < count) {
            return quickSelect(arr, low, pi - 1, k);
        } else {
            return quickSelect(arr, pi + 1, high, k - count);
        }
    }

    public static boolean subsetSum(int[] arr, int index, int target) {
        if (target == 0) {
            return true;
        }
        if (index < 0) {
            return false;
        }

        return subsetSum(arr, index - 1, target)
                || subsetSum(arr, index - 1, target - arr[index]);
    }
}
