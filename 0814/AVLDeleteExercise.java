// 檔名：AVLDeleteExercise.java

public class AVLDeleteExercise {

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

    // 插入節點（方便測試）
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
        return rebalance(node, data);
    }

    // 重新平衡
    private Node rebalance(Node node, int key) {
        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.data) {
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && key > node.right.data) {
            return leftRotate(node);
        }
        // LR
        if (balance > 1 && key > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && key < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 找最小值節點（後繼）
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 刪除節點
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private Node deleteNode(Node node, int key) {
        if (node == null) {
            return null;
        }

        // 1. 標準 BST 刪除
        if (key < node.data) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.data) {
            node.right = deleteNode(node.right, key);
        } else {
            // 找到要刪除的節點
            if (node.left == null && node.right == null) {
                return null; // 葉節點
            } else if (node.left == null) {
                return node.right; // 只有右子節點
            } else if (node.right == null) {
                return node.left; // 只有左子節點
            } else {
                // 兩個子節點：找右子樹最小值（後繼）
                Node successor = findMin(node.right);
                node.data = successor.data;
                node.right = deleteNode(node.right, successor.data);
            }
        }

        // 2. 更新高度
        updateHeight(node);

        // 3. 檢查平衡因子並修復
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
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
        AVLDeleteExercise tree = new AVLDeleteExercise();

        // 插入節點
        tree.insert(9);
        tree.insert(5);
        tree.insert(10);
        tree.insert(0);
        tree.insert(6);
        tree.insert(11);
        tree.insert(-1);
        tree.insert(1);
        tree.insert(2);

        System.out.println("初始樹中序遍歷:");
        tree.printInOrder();

        // 刪除葉節點
        System.out.println("刪除葉節點 2:");
        tree.delete(2);
        tree.printInOrder();

        // 刪除只有一個子節點的節點
        System.out.println("刪除只有一個子節點的節點 0:");
        tree.delete(0);
        tree.printInOrder();

        // 刪除有兩個子節點的節點
        System.out.println("刪除有兩個子節點的節點 5:");
        tree.delete(5);
        tree.printInOrder();
    }
}
