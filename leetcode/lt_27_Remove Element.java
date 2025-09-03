
class Solution {

    public int removeElement(int[] nums, int val) {
        int i = 0; // 慢指針，指向下一個「非 val 元素」的位置

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j]; // 把非 val 的元素放到前面
                i++;
            }
        }

        return i; // i 代表非 val 元素的數量
    }
}

/*
解題思路：
1. 題目要求移除陣列中所有等於 val 的元素，並回傳剩餘元素數量 k。
2. 使用雙指針：
   - j (快指針)：遍歷整個陣列。
   - i (慢指針)：指向下一個「非 val 元素」要放置的位置。
3. 當 nums[j] != val 時，把 nums[j] 放到 nums[i]，並 i++。
4. 遍歷完成後，前 i 個元素就是不等於 val 的元素，回傳 i 即可。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
 */
