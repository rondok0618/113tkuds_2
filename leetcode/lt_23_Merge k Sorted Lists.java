
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        // 最小堆，根據節點的 val 排序
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 把每個鏈表的頭節點丟進最小堆
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 從最小堆中依序取出最小的節點
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;

            // 如果還有下一個節點，把它加入最小堆
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 題目要求合併 k 條已排序的鏈結串列。
2. 使用最小堆（PriorityQueue）：
   - 先把每個鏈結串列的頭節點放進最小堆。
   - 每次從堆中取出最小節點，接到結果鏈表。
   - 如果該節點還有後續，則將它的 next 節點放回堆中。
3. 持續這樣直到堆為空，即完成合併。
4. 時間複雜度：O(N log k)，其中 N 是所有節點總數，k 是鏈表數量。
   空間複雜度：O(k)，最小堆最多存放 k 個節點。
 */
