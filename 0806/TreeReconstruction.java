
import java.util.*;

public class TreeReconstruction {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = buildIndexMap(inorder);
        return buildFromPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildFromPreIn(int[] preorder, int preStart, int preEnd,
            int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildFromPreIn(preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRoot - 1, inMap);
        root.right = buildFromPreIn(preorder, preStart + numsLeft + 1, preEnd,
                inorder, inRoot + 1, inEnd, inMap);
        return root;
    }

    public static TreeNode buildTreeFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = buildIndexMap(inorder);
        return buildFromPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildFromPostIn(int[] postorder, int postStart, int postEnd,
            int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildFromPostIn(postorder, postStart, postStart + numsLeft - 1,
                inorder, inStart, inRoot - 1, inMap);
        root.right = buildFromPostIn(postorder, postStart + numsLeft, postEnd - 1,
                inorder, inRoot + 1, inEnd, inMap);
        return root;
    }

    public static TreeNode buildCompleteBinaryTree(int[] levelOrder) {
        if (levelOrder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < levelOrder.length) {
            TreeNode current = queue.poll();

            if (i < levelOrder.length) {
                current.left = new TreeNode(levelOrder[i++]);
                queue.offer(current.left);
            }
            if (i < levelOrder.length) {
                current.right = new TreeNode(levelOrder[i++]);
                queue.offer(current.right);
            }
        }
        return root;
    }

    private static Map<Integer, Integer> buildIndexMap(int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return map;
    }

    public static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] levelOrder = {1, 2, 3, 4, 5, 6, 7};

        System.out.println("=== 重建：前序 + 中序 ===");
        TreeNode preInRoot = buildTreeFromPreIn(preorder, inorder);
        printInOrder(preInRoot);
        System.out.println();

        System.out.println("=== 重建：後序 + 中序 ===");
        TreeNode postInRoot = buildTreeFromPostIn(postorder, inorder);
        printInOrder(postInRoot);
        System.out.println();

        System.out.println("=== 重建：層序（完全二元樹） ===");
        TreeNode completeTree = buildCompleteBinaryTree(levelOrder);
        printInOrder(completeTree);
        System.out.println();
    }
}
