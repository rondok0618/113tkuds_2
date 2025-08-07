
import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {

        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static int sumAllNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.data + sumAllNodes(root.left) + sumAllNodes(root.right);
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static double averageValue(TreeNode root) {
        int totalSum = sumAllNodes(root);
        int totalNodes = countNodes(root);
        if (totalNodes == 0) {
            return 0;
        }
        return (double) totalSum / totalNodes;
    }

    public static int findMax(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("樹為空");
        }
        int max = root.data;
        if (root.left != null) {
            max = Math.max(max, findMax(root.left));
        }
        if (root.right != null) {
            max = Math.max(max, findMax(root.right));
        }
        return max;
    }

    public static int findMin(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("樹為空");
        }
        int min = root.data;
        if (root.left != null) {
            min = Math.min(min, findMin(root.left));
        }
        if (root.right != null) {
            min = Math.min(min, findMin(root.right));
        }
        return min;
    }

    public static int getMaxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }

        return maxWidth;
    }

    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reachedEnd = false;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.left != null) {
                if (reachedEnd) {
                    return false;
                }
                queue.offer(current.left);
            } else {
                reachedEnd = true;
            }

            if (current.right != null) {
                if (reachedEnd) {
                    return false;
                }
                queue.offer(current.right);
            } else {
                reachedEnd = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(18);

        System.out.println("=== 二元樹基本操作練習 ===");
        System.out.println("節點總和: " + sumAllNodes(root));
        System.out.println("平均值: " + averageValue(root));
        System.out.println("最大值: " + findMax(root));
        System.out.println("最小值: " + findMin(root));
        System.out.println("樹的最大寬度: " + getMaxWidth(root));
        System.out.println("是否為完全二元樹: " + isCompleteBinaryTree(root));
    }
}
