
class Solution {

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // 1. 找到第一個下降點
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // 2. 從右往左找到第一個比 nums[i] 大的數
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            // 3. 交換
            swap(nums, i, j);
        }

        // 4. 反轉 i 之後的區間
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}

/*
解題思路：
1. 從右往左找到第一個「下降點」(nums[i] < nums[i+1])。
   - 若不存在，表示 nums 是最大排列，直接反轉整個陣列。
2. 再從右往左找到第一個大於 nums[i] 的元素 nums[j]。
3. 交換 nums[i] 和 nums[j]。
4. 將 i+1 之後的子陣列反轉，保證字典序最小。
時間複雜度 O(n)，空間複雜度 O(1)。
 */
