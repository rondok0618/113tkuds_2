
import java.util.*;

public class M08_BSTRangedSum {

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

    public static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }

        if (root.val < L) {

            return rangeSumBST(root.right, L, R);
        } else if (root.val > R) {

            return rangeSumBST(root.left, L, R);
        } else {

            return root.val
                    + rangeSumBST(root.left, L, R)
                    + rangeSumBST(root.right, L, R);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int L = sc.nextInt();
        int R = sc.nextInt();

        TreeNode root = buildTree(arr);
        int sum = rangeSumBST(root, L, R);

        System.out.println("Sum: " + sum);
    }
}
