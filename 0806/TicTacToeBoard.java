
import java.util.Scanner;

public class TicTacToeBoard {

    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();

        System.out.println("=== 井字遊戲 ===");
        printBoard();

        while (true) {
            System.out.printf("輪到玩家 %c，請輸入列(0-2) 和 欄(0-2)：", currentPlayer);
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (placeMark(row, col)) {
                printBoard();

                if (checkWin()) {
                    System.out.printf("玩家 %c 獲勝！\n", currentPlayer);
                    break;
                } else if (isBoardFull()) {
                    System.out.println("平手！");
                    break;
                }

                switchPlayer();
            } else {
                System.out.println("無效位置，請重新輸入。");
            }
        }

        scanner.close();
    }

    // 初始化棋盤
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // 印出棋盤
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // 放置棋子
    public static boolean placeMark(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false; // 超出邊界
        }
        if (board[row][col] != ' ') {
            return false; // 已有棋子
        }

        board[row][col] = currentPlayer;
        return true;
    }

    // 切換玩家
    public static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // 判斷是否有玩家獲勝
    public static boolean checkWin() {
        // 檢查行與列
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer
                    && board[i][1] == currentPlayer
                    && board[i][2] == currentPlayer) {
                return true;
            }

            if (board[0][i] == currentPlayer
                    && board[1][i] == currentPlayer
                    && board[2][i] == currentPlayer) {
                return true;
            }
        }

        // 檢查對角線
        if (board[0][0] == currentPlayer
                && board[1][1] == currentPlayer
                && board[2][2] == currentPlayer) {
            return true;
        }

        if (board[0][2] == currentPlayer
                && board[1][1] == currentPlayer
                && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    // 判斷棋盤是否已滿（平手）
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
