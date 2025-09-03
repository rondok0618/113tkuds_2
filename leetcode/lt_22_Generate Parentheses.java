
class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        // 如果字串長度達到 2*n，表示一組完整的括號
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // 只要還有 "(" 可以放，就遞迴加入
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }

        // 只有當 ")" 數量小於 "(" 時，才可以放 ")"
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
}

/*
解題思路：
1. 題目要求生成所有「合法的括號組合」。
2. 採用回溯法（Backtracking）：
   - 在遞迴過程中動態構建字串。
   - 只有當 "(" 的數量小於 n 時，才能加入 "("。
   - 只有當 ")" 的數量小於 "(" 時，才能加入 ")"。
   - 當字串長度等於 2*n 時，代表形成一個合法組合。
3. 這樣可以保證所有結果都是合法的括號序列。
4. 時間複雜度大約為 O(4^n / √n)，這是 Catalan 數的增長率。
   空間複雜度 O(n)，主要來自遞迴深度。
 */
