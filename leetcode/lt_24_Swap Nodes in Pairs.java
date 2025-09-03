
class Solution {

    public ListNode swapPairs(ListNode head) {
        // 建立 dummy node 簡化處理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        // 當還有兩個以上節點時，才進行交換
        while (current.next != null && current.next.next != null) {
            // 指定要交換的兩個節點
            ListNode first = current.next;
            ListNode second = current.next.next;

            // 交換操作
            first.next = second.next;
            second.next = first;
            current.next = second;

            // 移動指標到下一組
            current = first;
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 題目要求兩兩交換鏈結串列中的節點（不能直接交換值）。
2. 使用 dummy node 簡化處理：
   - dummy 指向 head，方便處理頭節點交換。
3. 每次取出一組相鄰節點 first、second：
   - 讓 first 指向 second.next。
   - 讓 second 指向 first。
   - 讓上一個節點 current 指向 second。
4. 更新 current 到下一組 (即原來的 first)，繼續迭代。
5. 時間複雜度 O(n)，僅遍歷一次鏈結串列；空間複雜度 O(1)。
 */
