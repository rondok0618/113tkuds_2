
import java.util.*;

public class TreePathProblems {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        path.add(node.val);

        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            backtrack(node.left, path, result);
            backtrack(node.right, path, result);
        }

        path.remove(path.size() - 1);
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }

    public static List<Integer> maxRootToLeafPath(TreeNode root) {
        List<Integer> maxPath = new ArrayList<>();
        maxPathHelper(root, new ArrayList<>(), 0, new int[]{Integer.MIN_VALUE}, maxPath);
        return maxPath;
    }

    private static void maxPathHelper(TreeNode node, List<Integer> path, int sum,
            int[] maxSum, List<Integer> maxPath) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        sum += node.val;

        if (node.left == null && node.right == null) {
            if (sum > maxSum[0]) {
                maxSum[0] = sum;
                maxPath.clear();
                maxPath.addAll(path);
            }
        } else {
            maxPathHelper(node.left, path, sum, maxSum, maxPath);
            maxPathHelper(node.right, path, sum, maxSum, maxPath);
        }

        path.remove(path.size() - 1);
    }

    public static int maxPathSum(TreeNode root) {
        int[] maxSum = new int[]{Integer.MIN_VALUE};
        maxPathSumHelper(root, maxSum);
        return maxSum[0];
    }

    private static int maxPathSumHelper(TreeNode node, int[] maxSum) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(0, maxPathSumHelper(node.left, maxSum));
        int right = Math.max(0, maxPathSumHelper(node.right, maxSum));

        maxSum[0] = Math.max(maxSum[0], node.val + left + right);

        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        /*
            範例樹結構:
                    5
                   / \
                  4   8
                 /   / \
                11  13  4
               /  \      \
              7    2      1
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        System.out.println("=== 所有根到葉路徑 ===");
        List<List<Integer>> allPaths = allRootToLeafPaths(root);
        for (List<Integer> path : allPaths) {
            System.out.println(path);
        }

        System.out.println("\n=== 判斷是否存在和為 22 的路徑 ===");
        System.out.println("結果: " + hasPathSum(root, 22));

        System.out.println("\n=== 最大總和根到葉路徑 ===");
        List<Integer> maxPath = maxRootToLeafPath(root);
        System.out.println("路徑: " + maxPath);

        System.out.println("\n=== 任意兩節點最大路徑總和 ===");
        int maxSum = maxPathSum(root);
        System.out.println("最大路徑和: " + maxSum);
    }
}
