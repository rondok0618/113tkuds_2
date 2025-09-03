
class Solution {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // 慢指針 i 指向最後一個「唯一元素」
        int i = 0;

        // 快指針 j 掃描整個陣列
        for (int j = 1; j < nums.length; j++) {
            // 當遇到新的數字，放到 i+1 位置
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }

        // i 是最後唯一元素的索引，因此長度是 i+1
        return i + 1;
    }
}

/*
解題思路：
1. 題目要求移除重複元素，使得前 k 個元素為唯一元素，並回傳 k。
2. 由於陣列已排序，相同元素必然相鄰。
3. 使用雙指針法：
   - i 慢指針：指向最後一個唯一元素的位置。
   - j 快指針：遍歷整個陣列。
   - 當 nums[j] != nums[i] 時，表示找到新元素，把它放到 i+1 位置。
4. 遍歷完成後，唯一元素數量為 i+1。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
 */
