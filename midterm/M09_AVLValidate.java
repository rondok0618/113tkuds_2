
import java.util.*;

public class M09_AVLValidate {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    public static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;

        while (!q.isEmpty() && i < arr.length) {
            TreeNode cur = q.poll();

            if (i < arr.length && arr[i] != -1) {
                cur.left = new TreeNode(arr[i]);
                q.offer(cur.left);
            }
            i++;

            if (i < arr.length && arr[i] != -1) {
                cur.right = new TreeNode(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    public static boolean isBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isBST(root.left, min, root.val)
                && isBST(root.right, root.val, max);
    }

    public static int checkAVL(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = checkAVL(root.left);
        if (lh == -1) {
            return -1;
        }
        int rh = checkAVL(root.right);
        if (rh == -1) {
            return -1;
        }

        if (Math.abs(lh - rh) > 1) {
            return -1;

        }
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        TreeNode root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else {
            int h = checkAVL(root);
            if (h == -1) {
                System.out.println("Invalid AVL");
            } else {
                System.out.println("Valid");
            }
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. isBST() 會走訪每個節點一次，O(n)。
 * 2. checkAVL() 亦會後序遍歷所有節點一次，O(n)。
 * 3. 兩者都是線性，總時間複雜度仍為 O(n)，其中 n 為節點數。
 */
