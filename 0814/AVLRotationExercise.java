// 檔名：AVLRotationExercise.java

public class AVLRotationExercise {

    private Node root;

    // 節點類別
    private static class Node {

        int data;
        Node left, right;
        int height;

        Node(int data) {
            this.data = data;
            this.height = 1; // 新節點高度預設為 1
        }
    }

    // 工具方法：取得高度
    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 工具方法：更新節點高度
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }

    // 右旋 (Right Rotate, LL 情況修正)
    public Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        updateHeight(y);
        updateHeight(x);

        return x; // 新的子樹根
    }

    // 左旋 (Left Rotate, RR 情況修正)
    public Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        updateHeight(x);
        updateHeight(y);

        return y; // 新的子樹根
    }

    // 左右旋 (Left-Right Rotate, LR 情況修正)
    public Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left); // 先對左子樹左旋
        return rightRotate(node);         // 再對自己右旋
    }

    // 右左旋 (Right-Left Rotate, RL 情況修正)
    public Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right); // 先對右子樹右旋
        return leftRotate(node);              // 再對自己左旋
    }

    // 測試方法
    public void testRotations() {
        System.out.println("=== 測試 LL (Right Rotate) ===");
        root = new Node(30);
        root.left = new Node(20);
        root.left.left = new Node(10);
        updateHeight(root.left);
        updateHeight(root);
        root = rightRotate(root);
        printInOrder(root); // 10 20 30

        System.out.println("=== 測試 RR (Left Rotate) ===");
        root = new Node(10);
        root.right = new Node(20);
        root.right.right = new Node(30);
        updateHeight(root.right);
        updateHeight(root);
        root = leftRotate(root);
        printInOrder(root); // 10 20 30

        System.out.println("=== 測試 LR (Left-Right Rotate) ===");
        root = new Node(30);
        root.left = new Node(10);
        root.left.right = new Node(20);
        updateHeight(root.left);
        updateHeight(root);
        root = leftRightRotate(root);
        printInOrder(root); // 10 20 30

        System.out.println("=== 測試 RL (Right-Left Rotate) ===");
        root = new Node(10);
        root.right = new Node(30);
        root.right.left = new Node(20);
        updateHeight(root.right);
        updateHeight(root);
        root = rightLeftRotate(root);
        printInOrder(root); // 10 20 30
    }

    // 中序列印（檢查順序）
    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + " ");
            printInOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLRotationExercise exercise = new AVLRotationExercise();
        exercise.testRotations();
    }
}
