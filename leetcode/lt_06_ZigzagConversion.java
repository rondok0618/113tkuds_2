
class Solution {

    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int row = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[row].append(c);

            // 到頂或到底時轉向
            if (row == 0 || row == numRows - 1) {
                goingDown = !goingDown;
            }

            row += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rows) {
            result.append(sb);
        }

        return result.toString();
    }
}
