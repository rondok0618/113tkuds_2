
class Solution {

    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        // 遍歷 haystack，從每個可能起點檢查
        for (int i = 0; i <= n - m; i++) {
            if (haystack.substring(i, i + m).equals(needle)) {
                return i; // 找到第一個符合的起始位置
            }
        }

        return -1; // 沒找到
    }
}

/*
解題思路：
1. 題目要求找出 needle 在 haystack 中第一次出現的索引。
2. 使用暴力法：
   - 遍歷 haystack 的每個可能起始位置 i。
   - 檢查從 i 開始長度為 needle.length() 的子字串是否等於 needle。
3. 如果相等則回傳 i，若整個 haystack 都沒找到則回傳 -1。
4. 時間複雜度 O((n-m+1) * m)，n=haystack 長度，m=needle 長度。
   - 對於題目給定的輸入範圍已經足夠。
5. 空間複雜度 O(1)，只需要變數存放索引。
 */
