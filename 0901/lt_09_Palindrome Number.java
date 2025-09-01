
class Solution {

    public boolean isPalindrome(int x) {
        // 負數 或 尾數是0但不是0本身 → 一定不是迴文
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int rev = 0;
        // 只反轉一半
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        // 偶數位數：x == rev
        // 奇數位數：x == rev/10
        return x == rev || x == rev / 10;
    }
}
