
import java.util.*;

public class M02_YouBikeNextArrival {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String t = sc.nextLine();
            times[i] = toMinutes(t);
        }

        String queryStr = sc.nextLine();
        int query = toMinutes(queryStr);

        int ansIndex = binarySearch(times, query);
        if (ansIndex == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHMM(times[ansIndex]));
        }
    }

    static int toMinutes(String t) {
        String[] parts = t.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    static String toHHMM(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    static int binarySearch(int[] arr, int query) {
        int l = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] > query) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
