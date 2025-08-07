
public class BSTConversionAndBalance {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class DLLNode {

        int val;
        DLLNode prev, next;

        DLLNode(int val) {
            this.val = val;
        }
    }

    private static DLLNode head = null, prev = null;

    public static DLLNode bstToDoublyLinkedList(TreeNode root) {
        head = prev = null;
        inOrderToDLL(root);
        return head;
    }

    private static void inOrderToDLL(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrderToDLL(node.left);

        DLLNode curr = new DLLNode(node.val);
        if (prev == null) {
            head = curr;
        } else {
            prev.next = curr;
            curr.prev = prev;
        }
        prev = curr;

        inOrderToDLL(node.right);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBalancedBST(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBalancedBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBalancedBST(nums, left, mid - 1);
        node.right = buildBalancedBST(nums, mid + 1, right);
        return node;
    }

    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }

    private static int checkBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = checkBalance(node.left);
        int rightHeight = checkBalance(node.right);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int getBalanceFactor(TreeNode node) {
        return height(node.left) - height(node.right);
    }

    private static int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private static int runningSum = 0;

    public static void convertToGreaterSumTree(TreeNode root) {
        runningSum = 0;
        reverseInOrder(root);
    }

    private static void reverseInOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        reverseInOrder(node.right);

        runningSum += node.val;
        node.val = runningSum;

        reverseInOrder(node.left);
    }

    public static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void printDLL(DLLNode head) {
        DLLNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] sortedArr = {1, 2, 3, 4, 5, 6, 7};
        TreeNode bst = sortedArrayToBST(sortedArr);

        System.out.println("✅ 排序陣列 -> 平衡 BST:");
        printInOrder(bst);
        System.out.println();

        System.out.println("✅ BST 是否平衡？" + isBalanced(bst));
        System.out.println("根節點的平衡因子: " + getBalanceFactor(bst));

        System.out.println("✅ 將 BST 轉為排序的雙向鏈結串列:");
        DLLNode dll = bstToDoublyLinkedList(bst);
        printDLL(dll);

        System.out.println("✅ 將 BST 節點轉換為『大於等於該值的總和』:");
        convertToGreaterSumTree(bst);
        printInOrder(bst);
        System.out.println();
    }
}
