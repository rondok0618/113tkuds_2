
class Solution {

    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') { // 找到空格
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, r, c, num)) {
                            board[r][c] = num; // 嘗試填入
                            if (backtrack(board)) {
                                return true; // 成功完成
                            }
                            board[r][c] = '.'; // 回溯
                        }
                    }
                    return false; // 9 個數字都不行 → 無解
                }
            }
        }
        return true; // 所有格子都填滿
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            // 檢查 row
            if (board[row][i] == num) {
                return false;
            }
            // 檢查 col
            if (board[i][col] == num) {
                return false;
            }
            // 檢查 3x3 box
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == num) {
                return false;
            }
        }
        return true;
    }
}

/*
解題思路：
1. 整體採用 **回溯法 (Backtracking)**：
   - 從左到右、從上到下遍歷棋盤。
   - 遇到空格 '.' 嘗試放入 1~9。
   - 每次放數字都檢查 row、col、3x3 box 是否符合規則。
   - 若合法，遞迴處理下一個格子。
   - 若後續失敗，回溯（恢復成 '.'）。

2. isValid 檢查方法：
   - 檢查當前 row 是否已有 num。
   - 檢查當前 col 是否已有 num。
   - 檢查對應的 3x3 box 是否已有 num。

3. 停止條件：
   - 當所有格子都填滿 → 回傳 true，代表找到唯一解。

時間複雜度：最差 O(9^(81))，但大量剪枝，實際遠小於理論值。  
空間複雜度：O(81) = O(1)，主要是遞迴堆疊。
 */
