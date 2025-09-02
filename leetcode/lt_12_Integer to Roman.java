
class Solution {

    public String intToRoman(int num) {
        // 對應數字與羅馬符號
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };

        StringBuilder sb = new StringBuilder();

        // 貪婪法：從最大值開始減
        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }
}

/*
解題思路：
1. 羅馬數字的規則限定了幾個「特殊數值」：1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1。
2. 使用貪婪法 (Greedy)：從最大值開始，一直減掉並加上對應的符號。
   例如 num = 3749，先減 1000 → M，繼續直到不能減。
3. 特殊數值 (4, 9, 40, 90, 400, 900) 都已經事先放進對應表，這樣就能自動處理減法表示法。
4. 因為數值範圍限制在 1 ~ 3999，最多 15 個符號就能組成答案。
5. 時間複雜度 O(1)，因為最多跑固定 13 個數值，輸出結果長度 ≤ 15。
 */
