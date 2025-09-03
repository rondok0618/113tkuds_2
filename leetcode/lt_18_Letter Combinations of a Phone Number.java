
import java.util.*;

class Solution {

    private static final String[] KEYS = {
        "", // 0
        "", // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs",// 7
        "tuv", // 8
        "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder sb, String digits, int index) {
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }

        String letters = KEYS[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(result, sb, digits, index + 1);
            sb.deleteCharAt(sb.length() - 1); // 回溯
        }
    }
}

/*
解題思路：
1. 題目給出數字 2-9 對應的字母，就像手機按鍵。
   例如：2 → "abc"，3 → "def"，7 → "pqrs"，9 → "wxyz"。import java.util.*;

class Solution {
    private static final String[] KEYS = {
        "",    // 0
        "",    // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs",// 7
        "tuv", // 8
        "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;

        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder sb, String digits, int index) {
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }

        String letters = KEYS[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(result, sb, digits, index + 1);
            sb.deleteCharAt(sb.length() - 1); // 回溯
        }
    }
}

/*
解題思路：
1. 題目給出數字 2-9 對應的字母，就像手機按鍵。
   例如：2 → "abc"，3 → "def"，7 → "pqrs"，9 → "wxyz"。
2. 要求所有可能的字母組合，可以看成一棵樹的遍歷問題。
   - 每個數字對應多個字母。
   - 每層處理一個數字，分支是該數字的字母。
3. 使用回溯 (Backtracking)：
   - 當 index == digits.length()，表示找到一組完整組合，加入結果。
   - 否則取出當前 digits[index] 的字母，逐一嘗試。
   - 嘗試後再「回溯」刪除最後一個字元，繼續下一個選擇。
4. 時間複雜度 O(3^N * 4^M)，其中 N 為有 3 個字母的數字個數，M 為有 4 個字母的數字個數。
   因為 digits.length ≤ 4，所以最多 4^4 = 256 種組合，規模很小。
 */
