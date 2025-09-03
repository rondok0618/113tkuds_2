
class Solution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // 如果是開括號就推入 stack
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 如果 stack 為空，代表沒有對應的開括號
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                // 檢查是否匹配
                if (c == ')' && top != '(') {
                    return false;
                }
                if (c == ']' && top != '[') {
                    return false;
                }
                if (c == '}' && top != '{') {
                    return false;
                }
            }
        }

        // 最後 stack 必須為空，才是有效括號
        return stack.isEmpty();
    }
}

/*
解題思路：
1. 題目要求檢查字串中的括號是否成對、順序正確。
2. 使用 Stack 來處理：
   - 遇到開括號就壓入 Stack。
   - 遇到閉括號就檢查 Stack 頂端是否為對應的開括號。
   - 若不匹配或 Stack 為空，則為無效。
3. 最後 Stack 必須為空，代表所有括號都正確配對。
4. 時間複雜度 O(n)，遍歷字串一次；空間複雜度 O(n)（最壞情況全部都是開括號）。
 */
