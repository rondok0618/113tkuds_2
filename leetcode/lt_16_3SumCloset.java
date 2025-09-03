
import java.util.*;

class Solution {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 先排序
        int n = nums.length;
        int closest = nums[0] + nums[1] + nums[2]; // 初始化答案（前3個數）

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 如果比之前更接近 target，就更新答案
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                if (sum == target) {
                    return sum; // 剛好等於 target，直接回傳
                } else if (sum < target) {
                    left++; // sum 太小，要加大
                } else {
                    right--; // sum 太大，要縮小
                }
            }
        }
        return closest;
    }
}

/*
解題思路：
1. 先將陣列排序，方便雙指針操作。
2. 固定一個數 nums[i]，然後用雙指針 (left, right) 掃描區間 [i+1, n-1]。
3. 每次計算三數和 sum，並檢查是否比目前的 closest 更接近 target，如果是就更新 closest。
4. 如果 sum == target，表示找到最精準的解，直接回傳。
5. 如果 sum < target，表示和太小，要讓 left++。
6. 如果 sum > target，表示和太大，要讓 right--。
7. 時間複雜度 O(n^2)，空間複雜度 O(1)。
 */
