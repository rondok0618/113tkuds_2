
public class MatrixCalculator {

    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("=== 矩陣 A ===");
        printMatrix(matrixA);

        System.out.println("\n=== 矩陣 B ===");
        printMatrix(matrixB);

        System.out.println("\n=== A + B（加法） ===");
        int[][] sum = addMatrices(matrixA, matrixB);
        printMatrix(sum);

        System.out.println("\n=== A × C（乘法） ===");
        int[][] product = multiplyMatrices(matrixA, matrixC);
        printMatrix(product);

        System.out.println("\n=== A 的轉置 ===");
        int[][] transposeA = transposeMatrix(matrixA);
        printMatrix(transposeA);

        System.out.println("\n=== A 中的最大與最小值 ===");
        findMaxMin(matrixA);
    }

    // 矩陣加法
    public static int[][] addMatrices(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("兩矩陣維度不一致，無法相加");
        }

        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    // 矩陣乘法
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("兩矩陣無法相乘（a的列數 ≠ b的行數）");
        }

        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    // 矩陣轉置
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    // 尋找最大值與最小值
    public static void findMaxMin(int[][] matrix) {
        int max = matrix[0][0];
        int min = matrix[0][0];

        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
                if (value < min) {
                    min = value;
                }
            }
        }

        System.out.println("最大值: " + max);
        System.out.println("最小值: " + min);
    }

    // 列印矩陣
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%4d", value);
            }
            System.out.println();
        }
    }
}
