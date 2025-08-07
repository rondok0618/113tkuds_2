
import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBSTHelper(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }

        return isValidBSTHelper(node.left, min, node.val)
                && isValidBSTHelper(node.right, node.val, max);
    }

    public static List<TreeNode> findSwappedNodes(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        TreeNode[] prev = new TreeNode[1];
        findSwappedHelper(root, prev, result);
        return result;
    }

    private static void findSwappedHelper(TreeNode node, TreeNode[] prev, List<TreeNode> result) {
        if (node == null) {
            return;
        }

        findSwappedHelper(node.left, prev, result);

        if (prev[0] != null && prev[0].val > node.val) {
            if (result.isEmpty()) {
                result.add(prev[0]);
                result.add(node);
            } else {
                result.set(1, node);
            }
        }
        prev[0] = node;

        findSwappedHelper(node.right, prev, result);
    }

    public static void recoverBST(TreeNode root) {
        List<TreeNode> swapped = findSwappedNodes(root);
        if (swapped.size() == 2) {
            int tmp = swapped.get(0).val;
            swapped.get(0).val = swapped.get(1).val;
            swapped.get(1).val = tmp;
        }
    }

    public static int minRemovalsToValidBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);
        int lisLength = lengthOfLIS(inorder);
        return inorder.size() - lisLength;
    }

    private static void getInorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        getInorder(node.left, result);
        result.add(node.val);
        getInorder(node.right, result);
    }

    private static int lengthOfLIS(List<Integer> nums) {
        List<Integer> dp = new ArrayList<>();
        for (int num : nums) {
            int i = Collections.binarySearch(dp, num);
            if (i < 0) {
                i = -(i + 1);
            }
            if (i == dp.size()) {
                dp.add(num); 
            }else {
                dp.set(i, num);
            }
        }
        return dp.size();
    }

    public static void main(String[] args) {
        // 建立一棵有錯誤的 BST:
        // 正確 BST 中序：1 3 4 6 7 8 10
        // 錯誤 BST（節點 3 與 8 位置錯誤）：
        //         6
        //       /   \
        //      8     7
        //     / \   /
        //    1   4 10
        //         \
        //          3

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(8);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(3);
        root.right.left = new TreeNode(10);

        System.out.println("=== 驗證 BST ===");
        System.out.println("是否為合法 BST: " + isValidBST(root));

        System.out.println("\n=== 錯誤節點 ===");
        List<TreeNode> wrongNodes = findSwappedNodes(root);
        for (TreeNode node : wrongNodes) {
            System.out.println("錯誤節點: " + node.val);
        }

        System.out.println("\n=== 修復後 BST ===");
        recoverBST(root);
        System.out.println("是否為合法 BST: " + isValidBST(root));

        System.out.println("\n=== 最少移除節點數 ===");
        System.out.println("需要移除節點數: " + minRemovalsToValidBST(root));
    }
}
