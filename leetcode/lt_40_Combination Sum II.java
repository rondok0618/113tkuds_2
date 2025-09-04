
import java.util.*;

class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 排序方便去重
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue; // 跳過同層重複元素

            }
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, path, res); // i+1 表示每個數字只能用一次
            path.remove(path.size() - 1); // 回溯
        }
    }
}

/*
解題思路：
1. 對 candidates 排序，方便跳過重複元素，避免產生重複組合。
2. 使用回溯：
   - 從 start 索引開始遍歷 candidates。
   - 每次選擇 candidate[i]，遞迴 target - candidate[i]。
   - target = 0 → 找到合法組合。
   - 遇到 target < 0 → 超過目標，回溯。
   - 選擇時使用 i + 1 避免重複使用同一元素。
   - 若同層有重複元素 (candidates[i] == candidates[i-1])，跳過以去重。

時間複雜度：
- 理論上 O(2^n)，n 為 candidates 長度。
空間複雜度：
- O(target/min(candidates)) 遞迴堆疊深度。
 */
