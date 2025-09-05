
import java.util.*;

public class LC24_SwapPairs_Shifts {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListNode head = buildList(sc);
        ListNode swapped = swapPairs(head);
        printList(swapped);
    }

    private static ListNode buildList(Scanner sc) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (sc.hasNextInt()) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + (cur.next != null ? " " : ""));
            cur = cur.next;
        }
    }

    private static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // swap
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // move prev two steps forward
            prev = a;
        }

        return dummy.next;
    }
}
