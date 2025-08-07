
import java.util.*;

public class AdvancedStringRecursion {

    public static void main(String[] args) {
        String input = "abc";
        System.out.println("1. 所有排列組合:");
        generatePermutations("", input);

        System.out.println("\n2. 字串匹配演算法:");
        String text = "hello world";
        String pattern = "world";
        int index = stringMatch(text, pattern, 0);
        System.out.printf("在 \"%s\" 中找到 \"%s\" 的位置：%d\n", text, pattern, index);

        System.out.println("\n3. 移除重複字元:");
        String withDuplicates = "banana";
        String noDuplicates = removeDuplicates(withDuplicates, 0, new HashSet<>());
        System.out.printf("原始字串：%s -> 去重後：%s\n", withDuplicates, noDuplicates);

        System.out.println("\n4. 所有子字串組合:");
        String subInput = "abc";
        Set<String> substrings = new HashSet<>();
        generateAllSubstrings(subInput, 0, "", substrings);
        System.out.println("子字串總數：" + substrings.size());
        System.out.println(substrings);
    }

    public static void generatePermutations(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            String newPrefix = prefix + remaining.charAt(i);
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            generatePermutations(newPrefix, newRemaining);
        }
    }

    public static int stringMatch(String text, String pattern, int index) {
        if (index + pattern.length() > text.length()) {
            return -1;
        }
        if (text.substring(index, index + pattern.length()).equals(pattern)) {
            return index;
        }
        return stringMatch(text, pattern, index + 1);
    }

    public static String removeDuplicates(String str, int index, Set<Character> seen) {
        if (index == str.length()) {
            return "";
        }
        char current = str.charAt(index);
        if (seen.contains(current)) {
            return removeDuplicates(str, index + 1, seen);
        } else {
            seen.add(current);
            return current + removeDuplicates(str, index + 1, seen);
        }
    }

    public static void generateAllSubstrings(String str, int index, String current, Set<String> result) {
        if (index == str.length()) {
            if (!current.isEmpty()) {
                result.add(current);
            }
            return;
        }

        generateAllSubstrings(str, index + 1, current + str.charAt(index), result);

        generateAllSubstrings(str, index + 1, current, result);
    }
}
