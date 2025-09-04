
class Solution {

    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();

        // 基準點，避免第一個字元就是 ')' 導致 stack 空
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i); // 左括號進 stack
            } else {
                stack.pop(); // 嘗試配對左括號

                if (stack.isEmpty()) {
                    // 沒有基準，補一個新的基準點
                    stack.push(i);
                } else {
                    // 計算合法長度
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}

/*
解題思路：
1. 使用 Stack 追蹤「未匹配的括號索引」。
2. 先壓入 -1 當基準點，避免 "()..." 開頭時 stack 為空。
3. 遇到 '('：索引入 stack。
4. 遇到 ')'：
   - 嘗試 pop 一個 '('；
   - 若 stack 空了，補入新的基準點 i；
   - 否則，合法長度 = 當前索引 i - stack.peek()。
5. 全程更新最長合法括號長度。
時間複雜度：O(n)，一次遍歷字串。
空間複雜度：O(n)，最差情況所有 '(' 都入 stack。
 */
