
import java.util.*;

public class MeetingRoomScheduler {

    // ===== 部分 1：最少需要多少會議室 =====
    public int minMeetingRooms(int[][] meetings) {
        if (meetings.length == 0) {
            return 0;
        }

        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0])); // 按開始時間排
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 存結束時間

        for (int[] meeting : meetings) {
            if (!minHeap.isEmpty() && meeting[0] >= minHeap.peek()) {
                minHeap.poll(); // 騰出一間會議室
            }
            minHeap.offer(meeting[1]);
        }
        return minHeap.size();
    }

    // ===== 部分 2：只有 N 間會議室時最大化總會議時間 =====
    public int maxMeetingTime(int[][] meetings, int N) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[1], b[1])); // 按結束時間排

        int n = meetings.length;
        int[] dp = new int[n];
        int[] prev = new int[n];
        int[] choice = new int[n]; // 記錄是否選會議

        // 找出每個會議前一個不重疊的會議索引
        for (int i = 0; i < n; i++) {
            prev[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (meetings[j][1] <= meetings[i][0]) {
                    prev[i] = j;
                    break;
                }
            }
        }

        if (N == 1) { // 單會議室：加權區間排程 + 回溯
            for (int i = 0; i < n; i++) {
                int include = (meetings[i][1] - meetings[i][0]);
                if (prev[i] != -1) {
                    include += dp[prev[i]];
                }
                int exclude = (i > 0 ? dp[i - 1] : 0);

                if (include > exclude) {
                    dp[i] = include;
                    choice[i] = 1; // 選這個會議
                } else {
                    dp[i] = exclude;
                    choice[i] = 0; // 不選
                }
            }

            // 回溯選出的會議
            List<int[]> selected = new ArrayList<>();
            int i = n - 1;
            while (i >= 0) {
                if (choice[i] == 1) {
                    selected.add(meetings[i]);
                    i = prev[i];
                } else {
                    i--;
                }
            }
            Collections.reverse(selected);

            System.out.println("最佳安排會議：" + Arrays.deepToString(selected.toArray()));
            return dp[n - 1];
        } else {
            // 多會議室情況：用貪心 + Min Heap 模擬排程
            Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0])); // 依開始時間
            PriorityQueue<int[]> rooms = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            int totalTime = 0;

            for (int[] m : meetings) {
                while (!rooms.isEmpty() && rooms.peek()[1] <= m[0]) {
                    rooms.poll();
                }
                if (rooms.size() < N) {
                    totalTime += (m[1] - m[0]);
                    rooms.offer(m);
                }
            }
            return totalTime;
        }
    }

    public static void main(String[] args) {
        MeetingRoomScheduler scheduler = new MeetingRoomScheduler();

        int[][] m1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("會議：" + Arrays.deepToString(m1));
        System.out.println("答案：需要" + scheduler.minMeetingRooms(m1) + "個會議室\n");

        int[][] m2 = {{9, 10}, {4, 9}, {4, 17}};
        System.out.println("會議：" + Arrays.deepToString(m2));
        System.out.println("答案：需要" + scheduler.minMeetingRooms(m2) + "個會議室\n");

        int[][] m3 = {{1, 5}, {8, 9}, {8, 9}};
        System.out.println("會議：" + Arrays.deepToString(m3));
        System.out.println("答案：需要" + scheduler.minMeetingRooms(m3) + "個會議室\n");

        int[][] m4 = {{1, 4}, {2, 3}, {4, 6}};
        System.out.println("如果只有1個會議室，會議：" + Arrays.deepToString(m4));
        System.out.println("最佳安排總時間 = " + scheduler.maxMeetingTime(m4, 1));
    }
}
