
class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 建立一個 dummy 節點，方便處理結果鏈結串列
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 同時遍歷兩個鏈結串列
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // 如果其中一個鏈結串列還有剩餘，直接接上
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next; // 返回合併後的頭節點
    }
}

/*
解題思路：
1. 題目要求合併兩個排序好的鏈結串列。
2. 使用「雙指標」比較兩個鏈結串列的節點值，逐一選擇較小的節點接到結果鏈表中。
3. 若其中一個鏈結串列走完，直接將另一個鏈結串列剩餘部分接到結果後面。
4. 使用 dummy 節點簡化邏輯，避免特別處理頭節點。
5. 時間複雜度 O(m+n)，其中 m、n 為兩個鏈結串列長度；空間複雜度 O(1)（僅使用常數額外空間）。
 */
