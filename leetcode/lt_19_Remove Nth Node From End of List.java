
class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 使用 dummy node 處理刪除頭節點的情況，避免額外判斷
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;
        ListNode second = dummy;

        // 先讓 first 指標往前移動 n+1 步，保持兩個指標間距 n
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // 同時移動 first 和 second，直到 first 到達尾端
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 此時 second 指向要刪除節點的前一個節點
        second.next = second.next.next;

        // 返回新的 head（dummy.next）
        return dummy.next;
    }
}

/*
解題思路：
1. 題目要求刪除鏈結串列倒數第 n 個節點。
2. 使用「雙指標 + dummy node」技巧：
   - 先讓 first 指標先走 n+1 步，保持 first 與 second 間距 n。
   - 之後兩個指標一起往前走，直到 first 到尾端。
   - 此時 second 剛好在要刪除節點的前一個位置。
3. 透過修改 second.next 指標，跳過要刪除的節點。
4. 返回 dummy.next 作為新的頭節點。
5. 時間複雜度 O(L)，只需遍歷一次鏈結串列；空間複雜度 O(1)。
 */
