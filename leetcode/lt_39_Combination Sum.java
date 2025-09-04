
import java.util.*;

class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path)); // 找到一組合法解
            return;
        }
        if (target < 0) {
            return; // 超過 target，直接回溯
        }
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]); // 選擇該元素
            backtrack(candidates, target - candidates[i], i, path, res); // 可以重複選擇
            path.remove(path.size() - 1); // 回溯
        }
    }
}

/*
解題思路：
1. 使用回溯法 (Backtracking)：
   - 從 candidates 的 start 索引開始遍歷。
   - 每次嘗試將 candidate[i] 加入 path。
   - 遞迴呼叫 target - candidate[i]。
   - 若 target = 0 → 找到一組解。
   - 若 target < 0 → 超過 target，停止。
   - 回溯時移除最後加入的元素，嘗試下一個 candidate。

2. 為什麼 start = i？
   - 允許重複使用同一個數字。
   - 避免產生重複組合，保持順序。

時間複雜度：
- 理論上最差 O(N^(T/M+1))，N 為 candidates 長度，T 為 target，M 為 candidates 最小值。
空間複雜度：
- O(T/M)，即遞迴堆疊深度。
 */
