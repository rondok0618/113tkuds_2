
public class TreeMirrorAndSymmetry {

    static class TreeNode {

        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public static void mirror(TreeNode root) {
        if (root == null) {
            return;
        }

        mirror(root.left);
        mirror(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }

        return (t1.data == t2.data)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    public static boolean isSubtree(TreeNode mainTree, TreeNode subTree) {
        if (subTree == null) {
            return true;
        }
        if (mainTree == null) {
            return false;
        }

        if (isSameTree(mainTree, subTree)) {
            return true;
        }

        return isSubtree(mainTree.left, subTree) || isSubtree(mainTree.right, subTree);
    }

    public static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }

        return (t1.data == t2.data)
                && isSameTree(t1.left, t2.left)
                && isSameTree(t1.right, t2.right);
    }

    public static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {

        TreeNode symmetricRoot = new TreeNode(1);
        symmetricRoot.left = new TreeNode(2);
        symmetricRoot.right = new TreeNode(2);
        symmetricRoot.left.left = new TreeNode(3);
        symmetricRoot.left.right = new TreeNode(4);
        symmetricRoot.right.left = new TreeNode(4);
        symmetricRoot.right.right = new TreeNode(3);

        System.out.println("=== 鏡像與對稱操作 ===");
        System.out.println("是否為對稱樹: " + isSymmetric(symmetricRoot));

        System.out.print("原樹中序遍歷: ");
        printInOrder(symmetricRoot);
        mirror(symmetricRoot);
        System.out.print("\n鏡像後中序遍歷: ");
        printInOrder(symmetricRoot);
        System.out.println();

        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);

        TreeNode t2 = new TreeNode(1);
        t2.left = new TreeNode(3);
        t2.right = new TreeNode(2);

        System.out.println("兩棵樹是否互為鏡像: " + isMirror(t1, t2));

        TreeNode mainTree = new TreeNode(10);
        mainTree.left = new TreeNode(5);
        mainTree.right = new TreeNode(15);
        mainTree.left.left = new TreeNode(3);
        mainTree.left.right = new TreeNode(7);

        TreeNode subTree = new TreeNode(5);
        subTree.left = new TreeNode(3);
        subTree.right = new TreeNode(7);

        System.out.println("是否為子樹: " + isSubtree(mainTree, subTree));
    }
}
