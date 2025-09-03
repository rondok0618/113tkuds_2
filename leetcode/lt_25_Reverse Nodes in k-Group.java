
class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        while (true) {
            // 找到一組的最後一個節點
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) {
                break; // 不足 k 個，直接結束
            }
            ListNode groupNext = kth.next;

            // 反轉這一組
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;

            while (curr != groupNext) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // 接上反轉後的鏈表
            ListNode temp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = temp;
        }

        return dummy.next;
    }

    // 幫助函式：找到從 start 開始的第 k 個節點
    private ListNode getKthNode(ListNode start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }
        return start;
    }
}

/*
解題思路：
1. 題目要求把鏈表每 k 個節點一組進行反轉，若最後不足 k 個則保持不變。
2. 方法：
   - 使用 dummy node 方便處理頭節點。
   - 每次找到一組的第 k 個節點 (kth)。
   - 若不足 k 個則結束。
   - 否則反轉這一段 [groupPrev.next, kth]。
   - 把反轉後的鏈表重新接回原鏈表。
3. 重複這個過程直到遍歷完整個鏈表。
4. 時間複雜度 O(n)，每個節點反轉一次。
   空間複雜度 O(1)，只使用了常數額外指標。
 */
