
import java.util.*;

public class M04_TieredTaxSimple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long totalTax = 0;
        long[] results = new long[n];

        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            results[i] = computeTax(income);
            totalTax += results[i];
        }

        for (long tax : results) {
            System.out.println("Tax: " + tax);
        }
        System.out.println("Average: " + (totalTax / n));
    }

    static long computeTax(long income) {
        long tax = 0;

        if (income <= 120000) {
            tax = income * 5 / 100;
        } else if (income <= 500000) {
            tax = 120000 * 5 / 100;
            tax += (income - 120000) * 12 / 100;
        } else if (income <= 1000000) {
            tax = 120000 * 5 / 100;
            tax += (500000 - 120000) * 12 / 100;
            tax += (income - 500000) * 20 / 100;
        } else {
            tax = 120000 * 5 / 100;
            tax += (500000 - 120000) * 12 / 100;
            tax += (1000000 - 500000) * 20 / 100;
            tax += (income - 1000000) * 30 / 100;
        }

        return tax;
    }
}
/*
 * Time Complexity: O(n)
 * 說明：輸入 n 筆收入，每筆收入依 4 段稅率計算，為 O(1)，
 *       總計 O(n)。
 */
