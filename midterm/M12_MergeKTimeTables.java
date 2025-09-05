
import java.util.*;

public class M12_MergeKTimeTables {

    static class Entry {

        int time;
        int listIdx;
        int pos;

        Entry(int t, int l, int p) {
            time = t;
            listIdx = l;
            pos = p;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = Integer.parseInt(sc.nextLine().trim());
        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int len = Integer.parseInt(sc.nextLine().trim());
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                list.add(sc.nextInt());
            }
            lists.add(list);
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }

        PriorityQueue<Entry> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.time));

        for (int i = 0; i < K; i++) {
            if (!lists.get(i).isEmpty()) {
                pq.offer(new Entry(lists.get(i).get(0), i, 0));
            }
        }

        List<Integer> merged = new ArrayList<>();

        while (!pq.isEmpty()) {
            Entry cur = pq.poll();
            merged.add(cur.time);

            int nextPos = cur.pos + 1;
            if (nextPos < lists.get(cur.listIdx).size()) {
                pq.offer(new Entry(lists.get(cur.listIdx).get(nextPos), cur.listIdx, nextPos));
            }
        }

        // 輸出結果
        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i));
            if (i < merged.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
