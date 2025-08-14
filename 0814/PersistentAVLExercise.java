// 檔案：PersistentAVLExercise.java

import java.util.*;

public class PersistentAVLExercise {

    // 節點不可變（immutable）
    private static class Node {

        final int key;
        final Node left;
        final Node right;
        final int height;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = Math.max(height(left), height(right)) + 1;
        }

        private static int height(Node n) {
            return n == null ? 0 : n.height;
        }
    }

    // 保存不同版本的根節點
    private final List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null); // 版本 0: 空樹
    }

    // ===== 插入操作（產生新版本）=====
    public void insert(int versionId, int key) {
        Node oldRoot = versions.get(versionId);
        Node newRoot = insertAndCopy(oldRoot, key);
        versions.add(newRoot);
    }

    private Node insertAndCopy(Node node, int key) {
        if (node == null) {
            return new Node(key, null, null);
        }

        if (key < node.key) {
            Node newLeft = insertAndCopy(node.left, key);
            Node newNode = new Node(node.key, newLeft, node.right);
            return balance(newNode);
        } else if (key > node.key) {
            Node newRight = insertAndCopy(node.right, key);
            Node newNode = new Node(node.key, node.left, newRight);
            return balance(newNode);
        } else {
            // 已存在，不修改（共享原節點）
            return node;
        }
    }

    // ===== 平衡處理 =====
    private Node balance(Node node) {
        int balanceFactor = height(node.left) - height(node.right);

        if (balanceFactor > 1) {
            if (height(node.left.left) >= height(node.left.right)) {
                return rotateRight(node);
            } else {
                return rotateLeftRight(node);
            }
        } else if (balanceFactor < -1) {
            if (height(node.right.right) >= height(node.right.left)) {
                return rotateLeft(node);
            } else {
                return rotateRightLeft(node);
            }
        }
        return node;
    }

    private int height(Node n) {
        return Node.height(n);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t2 = x.right;
        return new Node(x.key, x.left, new Node(y.key, t2, y.right));
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node t2 = y.left;
        return new Node(y.key, new Node(x.key, x.left, t2), y.right);
    }

    private Node rotateLeftRight(Node node) {
        Node newLeft = rotateLeft(node.left);
        return rotateRight(new Node(node.key, newLeft, node.right));
    }

    private Node rotateRightLeft(Node node) {
        Node newRight = rotateRight(node.right);
        return rotateLeft(new Node(node.key, node.left, newRight));
    }

    // ===== 查詢歷史版本 =====
    public boolean search(int versionId, int key) {
        Node root = versions.get(versionId);
        return searchNode(root, key);
    }

    private boolean searchNode(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (key < node.key) {
            return searchNode(node.left, key);
        }
        if (key > node.key) {
            return searchNode(node.right, key);
        }
        return true;
    }

    // ===== 中序遍歷（查看版本內容）=====
    public List<Integer> inorder(int versionId) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(versions.get(versionId), result);
        return result;
    }

    private void inorderTraversal(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, result);
        result.add(node.key);
        inorderTraversal(node.right, result);
    }

    // ===== 測試 =====
    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();

        tree.insert(0, 10); // v1
        tree.insert(1, 20); // v2
        tree.insert(2, 5);  // v3
        tree.insert(3, 15); // v4

        System.out.println("版本 0: " + tree.inorder(0));
        System.out.println("版本 1: " + tree.inorder(1));
        System.out.println("版本 2: " + tree.inorder(2));
        System.out.println("版本 3: " + tree.inorder(3));
        System.out.println("版本 4: " + tree.inorder(4));

        System.out.println("版本 2 是否包含 20? " + tree.search(2, 20));
        System.out.println("版本 1 是否包含 5? " + tree.search(1, 5));
    }
}
