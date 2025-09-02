
class Solution {

    public int romanToInt(String s) {
        // 建立符號對應表
        int[] values = new int[26];
        values['I' - 'A'] = 1;
        values['V' - 'A'] = 5;
        values['X' - 'A'] = 10;
        values['L' - 'A'] = 50;
        values['C' - 'A'] = 100;
        values['D' - 'A'] = 500;
        values['M' - 'A'] = 1000;

        int total = 0;
        int prev = 0;

        // 從右往左掃描
        for (int i = s.length() - 1; i >= 0; i--) {
            int cur = values[s.charAt(i) - 'A'];

            if (cur < prev) {
                // 小數字在大數字左邊 → 減法情況
                total -= cur;
            } else {
                // 正常情況 → 加法
                total += cur;
            }
            prev = cur;
        }

        return total;
    }
}

/*
解題思路：
1. 羅馬數字有加法與減法規則：
   - 小數字在大數字右邊 → 加法，例如 VI = 6。
   - 小數字在大數字左邊 → 減法，例如 IV = 4。
2. 我們可以從字串最右邊開始往左掃描：
   - 若當前數字 < 前一個數字，表示是減法，做 total -= 當前值。
   - 否則是加法，做 total += 當前值。
3. 利用字元對應表 (陣列/Map) 快速取值。
4. 時間複雜度 O(n)，n = 字串長度 ≤ 15 → 幾乎是常數時間。
 */
