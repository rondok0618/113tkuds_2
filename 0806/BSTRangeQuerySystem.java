
import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {

        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    public static TreeNode insert(TreeNode root, int data) {
        if (root == null) {
            return new TreeNode(data);
        }
        if (data < root.data) {
            root.left = insert(root.left, data); 
        }else if (data > root.data) {
            root.right = insert(root.right, data);
        }
        return root;
    }

    public static List<Integer> rangeQuery(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private static void rangeQueryHelper(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (node.data > min) {
            rangeQueryHelper(node.left, min, max, result);
        }

        if (node.data >= min && node.data <= max) {
            result.add(node.data);
        }

        if (node.data < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }

    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) {
            return 0;
        }

        if (root.data < min) {
            return rangeCount(root.right, min, max);
        } else if (root.data > max) {
            return rangeCount(root.left, min, max);
        } else {
            return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
        }
    }

    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) {
            return 0;
        }

        if (root.data < min) {
            return rangeSum(root.right, min, max);
        } else if (root.data > max) {
            return rangeSum(root.left, min, max);
        } else {
            return root.data + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
        }
    }

    public static int closestValue(TreeNode root, int target) {
        int closest = root.data;

        while (root != null) {
            if (Math.abs(root.data - target) < Math.abs(closest - target)) {
                closest = root.data;
            }

            if (target < root.data) {
                root = root.left;
            } else if (target > root.data) {
                root = root.right;
            } else {
                return root.data;
            }
        }

        return closest;
    }

    public static void main(String[] args) {
        int[] values = {20, 10, 30, 5, 15, 25, 35, 3, 7, 13, 17};
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }

        int min = 10, max = 25, target = 16;

        System.out.println("=== BST 範圍查詢系統 ===");
        System.out.println("範圍 [" + min + ", " + max + "] 節點: " + rangeQuery(root, min, max));
        System.out.println("範圍節點數: " + rangeCount(root, min, max));
        System.out.println("範圍總和: " + rangeSum(root, min, max));
        System.out.println("最接近 " + target + " 的值: " + closestValue(root, target));
    }
}
