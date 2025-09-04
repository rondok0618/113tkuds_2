
class Solution {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到目標
            }

            // 判斷哪一半是有序的
            if (nums[left] <= nums[mid]) {
                // 左半部分有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // target 在左半
                } else {
                    left = mid + 1; // target 在右半
                }
            } else {
                // 右半部分有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // target 在右半
                } else {
                    right = mid - 1; // target 在左半
                }
            }
        }
        return -1; // 沒找到
    }
}

/*
解題思路：
1. 陣列是「有序 + 旋轉」，所以必定有一半是有序的。
2. 使用二分搜尋 (Binary Search)：
   - 計算 mid，若 nums[mid] == target，直接回傳。
   - 如果左半部分有序 (nums[left] <= nums[mid])：
       → 判斷 target 是否落在 [nums[left], nums[mid]) 之間。
       → 如果是，縮小到左半；否則縮小到右半。
   - 否則右半部分有序：
       → 判斷 target 是否落在 (nums[mid], nums[right]] 之間。
       → 如果是，縮小到右半；否則縮小到左半。
3. 持續迴圈直到 left > right，表示找不到，回傳 -1。

時間複雜度：O(log n)，每次搜尋範圍減半。
空間複雜度：O(1)，僅用變數儲存邊界。
 */
