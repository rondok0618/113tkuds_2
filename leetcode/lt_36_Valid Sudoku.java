
class Solution {

    public boolean isValidSudoku(char[][] board) {
        // 使用三個集合陣列分別追蹤 row、col、box 的數字
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') {
                    continue; // 空格跳過
                }
                // 計算目前所在的 box index
                int boxIndex = (r / 3) * 3 + (c / 3);

                // 如果重複出現，直接 return false
                if (rows[r].contains(ch) || cols[c].contains(ch) || boxes[boxIndex].contains(ch)) {
                    return false;
                }

                // 加入對應集合
                rows[r].add(ch);
                cols[c].add(ch);
                boxes[boxIndex].add(ch);
            }
        }

        return true;
    }
}

/*
解題思路：
1. Sudoku 的規則要求：
   - 每一列 (row) 不可有重複數字。
   - 每一行 (col) 不可有重複數字。
   - 每一個 3x3 的子方格 (box) 不可有重複數字。
   - 只需檢查填入的數字，空格 '.' 可忽略。

2. 具體做法：
   - 建立三個 HashSet 陣列，rows[9]、cols[9]、boxes[9]。
   - 每遇到一個數字：
     - 檢查是否已存在於對應的 row、col、box。
     - 若已存在，回傳 false。
     - 否則加入集合。
   - 若整個掃描完成都沒違規，回傳 true。

3. box 的索引計算方式：
   - 9 個子方格可編號為 0~8。
   - 對應公式：boxIndex = (row / 3) * 3 + (col / 3)。

時間複雜度：O(9*9) = O(1)（固定大小的棋盤）  
空間複雜度：O(1)（最多存 81 個數字）
 */
