
import java.util.Scanner;

public class RecursiveMathCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 組合數計算
        System.out.println("=== 組合數 C(n, k) ===");
        System.out.print("輸入 n: ");
        int n = scanner.nextInt();
        System.out.print("輸入 k: ");
        int k = scanner.nextInt();
        System.out.printf("C(%d, %d) = %d\n", n, k, combination(n, k));

        // 卡塔蘭數計算
        System.out.println("\n=== 卡塔蘭數 Catalan(n) ===");
        System.out.print("輸入 n: ");
        int c = scanner.nextInt();
        System.out.printf("Catalan(%d) = %d\n", c, catalan(c));

        // 漢諾塔步數計算
        System.out.println("\n=== 漢諾塔步數 Hanoi(n) ===");
        System.out.print("輸入 n: ");
        int h = scanner.nextInt();
        System.out.printf("Hanoi(%d) 需要移動 %d 步\n", h, hanoi(h));

        // 回文數判斷
        System.out.println("\n=== 回文數判斷 ===");
        System.out.print("輸入數字：");
        int num = scanner.nextInt();
        if (isPalindrome(Integer.toString(num))) {
            System.out.println(num + " 是回文數");
        } else {
            System.out.println(num + " 不是回文數");
        }

        scanner.close();
    }

    // 組合數：C(n, k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 卡塔蘭數：Catalan(n)
    public static int catalan(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 漢諾塔：hanoi(n)
    public static int hanoi(int n) {
        if (n == 1) {
            return 1;
        }
        return 2 * hanoi(n - 1) + 1;
    }

    // 回文數判斷（使用字串遞迴）
    public static boolean isPalindrome(String str) {
        if (str.length() <= 1) {
            return true;
        }
        if (str.charAt(0) != str.charAt(str.length() - 1)) {
            return false;
        }
        return isPalindrome(str.substring(1, str.length() - 1));
    }
}
