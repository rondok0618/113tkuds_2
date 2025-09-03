
import java.util.*;

class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) {
            return result;
        }

        // 用 HashMap 計算 words 的詞頻
        Map<String, Integer> wordMap = new HashMap<>();
        for (String w : words) {
            wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);
        }

        // 嘗試不同的起始偏移（避免 wordLen > 1 時漏掉情況）
        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i;
            Map<String, Integer> windowMap = new HashMap<>();
            int count = 0; // 當前匹配到的 word 數量

            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordMap.containsKey(word)) {
                    windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                    count++;

                    // 超過需要的數量，要移動 left 縮小窗口
                    while (windowMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // 剛好匹配所有 words
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // 重置
                    windowMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}

/*
解題思路：
1. 計算每個 word 出現的次數（wordMap）。
2. 因為 word 長度固定，所以用「固定步長滑動視窗」來掃描字串。
   - 視窗大小 = wordLen * words.length
   - 以 wordLen 為單位移動，確保每次都是完整 word。
3. 用 windowMap 記錄當前視窗內的詞頻，並用 count 記錄匹配數。
4. 如果某個 word 出現次數超過需求，則移動 left 指針縮小視窗。
5. 當 count == words.length 時，表示找到符合條件的 substring，記錄 left。
6. 時間複雜度 O(n * wordLen)，其中 n = s.length()。
 */
