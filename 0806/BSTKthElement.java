
import java.util.*;

public class BSTKthElement {

    static class TreeNode {

        int data;
        TreeNode left, right;
        int size; // 此節點為根的子樹節點總數（包含自己）

        TreeNode(int data) {
            this.data = data;
            this.size = 1;
        }
    }

    /**
     * 動態插入（同時維護 size）
     */
    public static TreeNode insert(TreeNode root, int data) {
        if (root == null) {
            return new TreeNode(data);
        }

        if (data < root.data) {
            root.left = insert(root.left, data); 
        }else if (data > root.data) {
            root.right = insert(root.right, data);
        }

        // 更新子樹大小
        root.size = 1 + getSize(root.left) + getSize(root.right);
        return root;
    }

    /**
     * 動態刪除（同時維護 size）
     */
    public static TreeNode delete(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.data) {
            root.left = delete(root.left, key);
        } else if (key > root.data) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                return root.right; 
            }else if (root.right == null) {
                return root.left;
            }

            TreeNode minNode = findMin(root.right);
            root.data = minNode.data;
            root.right = delete(root.right, minNode.data);
        }

        root.size = 1 + getSize(root.left) + getSize(root.right);
        return root;
    }

    /**
     * 找出第 k 小元素（中序第 k 個）
     */
    public static Integer findKthSmallest(TreeNode root, int k) {
        if (root == null || k <= 0 || k > getSize(root)) {
            return null;
        }

        int leftSize = getSize(root.left);
        if (k <= leftSize) {
            return findKthSmallest(root.left, k);
        } else if (k == leftSize + 1) {
            return root.data;
        } else {
            return findKthSmallest(root.right, k - leftSize - 1);
        }
    }

    /**
     * 找出第 k 大元素（中序第 (n-k+1) 個）
     */
    public static Integer findKthLargest(TreeNode root, int k) {
        if (root == null || k <= 0 || k > getSize(root)) {
            return null;
        }
        int total = getSize(root);
        return findKthSmallest(root, total - k + 1);
    }

    /**
     * 找出第 k 小到第 j 小的元素（中序遍歷範圍）
     */
    public static List<Integer> findRange(TreeNode root, int k, int j) {
        List<Integer> result = new ArrayList<>();
        inOrderRange(root, new int[]{0}, k, j, result);
        return result;
    }

    private static void inOrderRange(TreeNode root, int[] count, int k, int j, List<Integer> result) {
        if (root == null) {
            return;
        }
        inOrderRange(root.left, count, k, j, result);

        count[0]++;
        if (count[0] >= k && count[0] <= j) {
            result.add(root.data);
        }

        if (count[0] > j) {
            return;
        }

        inOrderRange(root.right, count, k, j, result);
    }

    /**
     * 工具：取得子樹大小
     */
    public static int getSize(TreeNode node) {
        return node == null ? 0 : node.size;
    }

    /**
     * 工具：尋找最小節點
     */
    public static TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 主程式：測試功能
     */
    public static void main(String[] args) {
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }

        System.out.println("=== BST 第k小/大元素查詢系統 ===");
        System.out.println("第 3 小的元素: " + findKthSmallest(root, 3));
        System.out.println("第 2 大的元素: " + findKthLargest(root, 2));
        System.out.println("第 2 小到第 5 小的元素: " + findRange(root, 2, 5));

        // 測試動態插入與刪除
        root = insert(root, 13);
        System.out.println("插入 13 後第 4 小: " + findKthSmallest(root, 4));
        root = delete(root, 10);
        System.out.println("刪除 10 後第 4 小: " + findKthSmallest(root, 4));
    }
}
