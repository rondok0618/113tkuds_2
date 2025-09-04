
class Solution {

    public int[] searchRange(int[] nums, int target) {
        int left = findBound(nums, target, true);
        int right = findBound(nums, target, false);
        return new int[]{left, right};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    right = mid - 1; // 繼續往左找
                } else {
                    left = mid + 1;  // 繼續往右找
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return bound;
    }
}

/*
解題思路：
1. 題目要求 O(log n)，必須使用二分搜尋法 (Binary Search)。
2. 我們要找到 target 的「第一個出現位置」和「最後一個出現位置」：
   - findBound(..., true) → 找到左邊界：
       當 nums[mid] == target 時，繼續往左半邊搜尋。
   - findBound(..., false) → 找到右邊界：
       當 nums[mid] == target 時，繼續往右半邊搜尋。
3. 若找不到 target，返回 -1。
4. 最後組合成 [leftBound, rightBound]。

時間複雜度：O(log n)，因為每次都二分搜尋。
空間複雜度：O(1)，僅用變數儲存索引。
 */
