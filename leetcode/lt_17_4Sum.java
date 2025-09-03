
import java.util.*;

class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 跳過重複
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // 跳過重複
                }
                int left = j + 1, right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;

                        while (left < right && nums[left] == nums[left - 1]) {
                            left++; // 避免重複

                                                }while (left < right && nums[right] == nums[right + 1]) {
                            right--; // 避免重複
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}

/*
解題思路：
1. 先將陣列排序，方便去重與雙指針處理。
2. 固定前兩個數字 nums[i], nums[j]。
3. 在區間 [j+1, n-1] 使用雙指針 (left, right)，計算四數和。
4. 如果 sum == target，存入答案，並移動指針，同時跳過重複值。
5. 如果 sum < target，left++；如果 sum > target，right--。
6. 過程中確保 i、j 不能重複，以避免重複組合。
7. 因為數值範圍大 (-1e9 ~ 1e9)，所以用 long 存 sum 避免溢位。
8. 時間複雜度 O(n^3)，因為雙層迴圈 + 雙指針，n ≤ 200 可接受。
 */
