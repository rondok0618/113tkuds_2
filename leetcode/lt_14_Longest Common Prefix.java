
class Solution {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 以第一個字串當基準
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短 prefix，直到符合當前字串的開頭
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
}

/*
解題思路：
1. 將第一個字串設為初始的公共前綴 prefix。
2. 從第二個字串開始逐一比對：
   - 使用 indexOf(prefix) 檢查 prefix 是否為當前字串的開頭。
   - 如果不是，就縮短 prefix（去掉最後一個字元），直到符合或變成空字串。
3. 如果 prefix 最後變成空字串，表示沒有公共前綴，直接回傳 ""。
4. 時間複雜度 O(S)，其中 S 為所有字元總數，因為最多檢查每個字元一次。
 */
