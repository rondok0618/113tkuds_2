
import java.util.*;

public class LC23_MergeKLists_Hospitals {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        ListNode[] lists = new ListNode[k];

        for (int i = 0; i < k; i++) {
            lists[i] = buildList(sc);
        }

        ListNode merged = mergeKLists(lists);

        ListNode cur = merged;
        while (cur != null) {
            System.out.print(cur.val + (cur.next != null ? " " : ""));
            cur = cur.next;
        }
    }

    private static ListNode buildList(Scanner sc) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (true) {
            int val = sc.nextInt();
            if (val == -1) {
                break;
            }
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        return dummy.next;
    }

    private static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                pq.offer(node.next);
            }
        }

        return dummy.next;
    }
}
