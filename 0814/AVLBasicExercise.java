// 檔名：AVLBasicExercise.java

public class AVLBasicExercise {

    private Node root;

    // 節點類別
    private static class Node {

        int data;
        Node left, right;
        int height;

        Node(int data) {
            this.data = data;
            this.height = 1; // 預設新節點高度為 1
        }
    }

    // 取得高度（遞迴）
    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 取得平衡因子
    private int getBalance(Node node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    // 插入節點（AVL 版本）
    public void insert(int data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node node, int data) {
        // 1. 標準 BST 插入
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        } else {
            return node; // 不允許重複值
        }

        // 2. 更新高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 3. 取得平衡因子
        int balance = getBalance(node);

        // 4. 平衡調整（四種情況）
        // LL
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }
        // LR
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 左旋
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // 右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 搜尋節點
    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(Node node, int data) {
        if (node == null) {
            return false;
        }
        if (data == node.data) {
            return true;
        }
        if (data < node.data) {
            return searchNode(node.left, data);
        }
        return searchNode(node.right, data);
    }

    // 計算樹高度（遞迴）
    public int height() {
        return getHeight(root);
    }

    // 檢查是否為有效 AVL 樹
    public boolean isValidAVL() {
        return checkAVL(root, Integer.MIN_VALUE, Integer.MAX_VALUE) != -1;
    }

    private int checkAVL(Node node, int min, int max) {
        if (node == null) {
            return 0;
        }

        // 檢查 BST 性質
        if (node.data <= min || node.data >= max) {
            return -1;
        }

        int leftHeight = checkAVL(node.left, min, node.data);
        int rightHeight = checkAVL(node.right, node.data, max);

        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        // 檢查平衡因子
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 中序列印
    public void printInOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    // 測試程式
    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("中序遍歷:");
        tree.printInOrder(); // 應為排序後的順序

        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 60: " + tree.search(60));

        System.out.println("樹高度: " + tree.height());
        System.out.println("是否為有效 AVL 樹: " + tree.isValidAVL());
    }
}
