
public class RecursionVsIteration {

    public static void main(String[] args) {

        int n = 20, k = 10;
        int[] array = {1, 2, 3, 4, 5};
        String testString = "Recursion vs Iteration is educational and interesting!";
        String brackets = "((())())";

        System.out.println("=== 1. 二項式係數 C(n, k) ===");
        System.out.println("遞迴結果：" + binomialRecursive(n, k));
        System.out.println("迭代結果：" + binomialIterative(n, k));

        System.out.println("\n=== 2. 陣列乘積 ===");
        System.out.println("遞迴結果：" + arrayProductRecursive(array, 0));
        System.out.println("迭代結果：" + arrayProductIterative(array));

        System.out.println("\n=== 3. 元音字母數量 ===");
        System.out.println("遞迴結果：" + countVowelsRecursive(testString, 0));
        System.out.println("迭代結果：" + countVowelsIterative(testString));

        System.out.println("\n=== 4. 括號是否配對正確 ===");
        System.out.println("遞迴結果：" + isBalancedRecursive(brackets));
        System.out.println("迭代結果：" + isBalancedIterative(brackets));
    }

    public static long binomialRecursive(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static long binomialIterative(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i, k); j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    public static int arrayProductRecursive(int[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }
        return arr[index] * arrayProductRecursive(arr, index + 1);
    }

    public static int arrayProductIterative(int[] arr) {
        int product = 1;
        for (int value : arr) {
            product *= value;
        }
        return product;
    }

    public static int countVowelsRecursive(String s, int index) {
        if (index == s.length()) {
            return 0;
        }
        char c = Character.toLowerCase(s.charAt(index));
        int count = "aeiou".indexOf(c) >= 0 ? 1 : 0;
        return count + countVowelsRecursive(s, index + 1);
    }

    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static boolean isBalancedRecursive(String s) {
        return checkBalanced(s.toCharArray(), 0, 0);
    }

    private static boolean checkBalanced(char[] chars, int index, int balance) {
        if (balance < 0) {
            return false;
        }
        if (index == chars.length) {
            return balance == 0;
        }
        if (chars[index] == '(') {
            balance++;
        } else if (chars[index] == ')') {
            balance--;
        }
        return checkBalanced(chars, index + 1, balance);
    }

    public static boolean isBalancedIterative(String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
}
