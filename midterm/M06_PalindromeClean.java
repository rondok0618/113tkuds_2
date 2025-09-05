
import java.util.*;

public class M06_PalindromeClean {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        if (isPalindrome(s)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l < r) {
            while (l < r && !Character.isLetter(s.charAt(l))) {
                l++;
            }
            while (l < r && !Character.isLetter(s.charAt(r))) {
                r--;
            }

            if (l < r) {
                char cl = Character.toLowerCase(s.charAt(l));
                char cr = Character.toLowerCase(s.charAt(r));
                if (cl != cr) {
                    return false;
                }
                l++;
                r--;
            }
        }

        return true;
    }
}
