
import java.util.*;

public class LC25_ReverseKGroup_Shifts {

    // 鏈結節點定義
    static class ListNode {

        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 反轉 start 到 end 節點（不含 end），返回新的頭節點
    static ListNode reverse(ListNode start, ListNode end) {
        ListNode prev = null, curr = start;
        while (curr != end) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 k
        int k = sc.nextInt();
        sc.nextLine();

        // 讀取一行整數，建立鏈表
        String line = sc.nextLine().trim();
        if (line.isEmpty()) {
            System.out.println();
            return;
        }
        String[] parts = line.split("\\s+");

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (String s : parts) {
            cur.next = new ListNode(Integer.parseInt(s));
            cur = cur.next;
        }

        ListNode head = dummy.next;
        dummy.next = head;

        ListNode prevGroupEnd = dummy;

        while (true) {
            // 檢查剩餘長度是否 >= k
            ListNode kth = prevGroupEnd;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) {
                break;
            }

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            // 反轉區段
            ListNode newGroupHead = reverse(groupStart, kth.next);

            // 接回鏈表
            prevGroupEnd.next = newGroupHead;
            groupStart.next = nextGroupStart;

            // 移動 prevGroupEnd 到尾部
            prevGroupEnd = groupStart;
        }

        // 輸出結果
        cur = dummy.next;
        List<String> res = new ArrayList<>();
        while (cur != null) {
            res.add(String.valueOf(cur.val));
            cur = cur.next;
        }
        System.out.println(String.join(" ", res));
    }
}
