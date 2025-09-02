
import java.util.*;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 先排序，方便去重與雙指針移動

        for (int i = 0; i < nums.length - 2; i++) {
            // 避免重複答案（如果當前數字和前一個一樣就跳過）
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    // 避免重複解
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if (sum < 0) {
                    left++; // 需要更大數字
                } else {
                    right--; // 需要更小數字
                }
            }
        }
        return res;
    }
}

/*
解題思路：
1. 將 nums 排序，方便後續去重與雙指針操作。
2. 固定一個數字 nums[i]，然後在區間 [i+1, n-1] 內使用雙指針 (left, right) 尋找另外兩個數字。
3. 如果 nums[i] + nums[left] + nums[right] == 0，記錄一組解答，並同時移動 left、right，跳過重複值。
4. 如果和小於 0，代表需要更大的數字，left++。
5. 如果和大於 0，代表需要更小的數字，right--。
6. 過程中避免相同的 i 值或相同的 left/right 值，確保不會出現重複三元組。
7. 時間複雜度 O(n^2)，空間複雜度 O(1)（不含輸出）。
 */
