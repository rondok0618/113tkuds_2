
class Solution {

    public int divide(int dividend, int divisor) {
        // 處理邊界：溢出情況
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE; // 超出範圍，回傳 2^31 - 1
        }
        if (dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE; // 最小值
        }

        // 判斷結果正負
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 轉成 long 並取絕對值，避免溢出
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long result = 0;

        // 不斷減去 divisor 的倍數
        while (a >= b) {
            long temp = b, multiple = 1;
            // 透過位移加速，直到 temp * 2 > a
            while ((temp << 1) <= a) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            result += multiple;
        }

        // 套用正負號
        result = negative ? -result : result;

        // 回傳整數範圍內的值
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int) result;
    }
}

/*
解題思路：
1. 特殊情況處理：
   - 當 dividend = Integer.MIN_VALUE 且 divisor = -1，結果會溢出，回傳 Integer.MAX_VALUE。
   - 當 divisor = 1 或 -1 時，直接回傳對應值即可。
2. 確定結果正負號：透過 (dividend < 0) ^ (divisor < 0) 判斷是否異號。
3. 轉為 long 並取絕對值，避免負數溢出。
4. 核心做法：
   - 不斷減去 divisor 的倍數，直到小於 divisor。
   - 為了加速，用「位移法」：每次嘗試把 divisor * 2、*4、*8...（透過左移運算）。
   - 當超過 dividend 時，就回退，累加對應的倍數。
5. 時間複雜度 O(log n)，因為每次都至少減掉 divisor 的倍數。
6. 空間複雜度 O(1)，只使用常數額外變數。
 */
