// 檔名：AVLRangeQueryExercise.java

import java.util.*;

public class AVLRangeQueryExercise {

    private Node root;

    // 節點類別
    private static class Node {

        int data;
        Node left, right;
        int height;

        Node(int data) {
            this.data = data;
            this.height = 1;
        }
    }

    // 工具方法
    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }

    // 左旋
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // 右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // 插入節點
    public void insert(int data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node node, int data) {
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

        updateHeight(node);

        int balance = getBalance(node);

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

    // 範圍查詢
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private void rangeQueryHelper(Node node, int min, int max, List<Integer> result) {
        if (node == null) {
            return;
        }

        // 剪枝：只在必要時往左走
        if (node.data > min) {
            rangeQueryHelper(node.left, min, max, result);
        }

        // 範圍內 → 加入結果
        if (node.data >= min && node.data <= max) {
            result.add(node.data);
        }

        // 剪枝：只在必要時往右走
        if (node.data < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }

    // 中序列印（方便檢查）
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
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();

        int[] values = {20, 8, 22, 4, 12, 10, 14};
        for (int v : values) {
            tree.insert(v);
        }

        System.out.println("中序遍歷:");
        tree.printInOrder();

        System.out.println("範圍查詢 [10, 20]:");
        List<Integer> range = tree.rangeQuery(10, 20);
        System.out.println(range); // 預期 [10, 12, 14, 20]

        System.out.println("範圍查詢 [5, 13]:");
        System.out.println(tree.rangeQuery(5, 13)); // 預期 [8, 10, 12]
    }
}
