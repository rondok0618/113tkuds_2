
class Solution {

    public String countAndSay(int n) {
        String result = "1"; // base case
        for (int i = 2; i <= n; i++) {
            result = encode(result); // 對前一個字串做 RLE
        }
        return result;
    }

    private String encode(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++; // 同樣字元，增加數量
            } else {
                sb.append(count).append(s.charAt(i - 1)); // 輸出 "次數 + 字元"
                count = 1;
            }
        }
        // 記得最後一組也要輸出
        sb.append(count).append(s.charAt(s.length() - 1));
        return sb.toString();
    }
}

/*
解題思路：
1. Base case：n=1 時回傳 "1"。
2. 從 i=2 到 n，依序對前一個字串做 RLE 編碼。
   - 掃描字串，統計連續相同字元的數量。
   - 當遇到不同字元，或到達結尾，就把「數量 + 字元」輸出到新字串。
3. 最後得到的字串就是 countAndSay(n)。

時間複雜度：O(n * L)，L 為字串長度，隨 n 指數增長，但 n ≤ 30，能接受。  
空間複雜度：O(L)，主要用 StringBuilder 暫存。
 */
