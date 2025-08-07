
import java.util.*;

public class MultiWayTreeAndDecisionTree {

    static class MultiWayNode {

        String val;
        List<MultiWayNode> children;

        MultiWayNode(String val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    public static void dfs(MultiWayNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        for (MultiWayNode child : node.children) {
            dfs(child);
        }
    }

    public static void bfs(MultiWayNode root) {
        if (root == null) {
            return;
        }
        Queue<MultiWayNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            MultiWayNode node = queue.poll();
            System.out.print(node.val + " ");
            queue.addAll(node.children);
        }
    }

    public static int getHeight(MultiWayNode node) {
        if (node == null) {
            return 0;
        }
        int maxChildHeight = 0;
        for (MultiWayNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, getHeight(child));
        }
        return maxChildHeight + 1;
    }

    public static void printDegrees(MultiWayNode node) {
        if (node == null) {
            return;
        }
        System.out.println("節點 " + node.val + " 的度數: " + node.children.size());
        for (MultiWayNode child : node.children) {
            printDegrees(child);
        }
    }

    static class DecisionNode {

        String question;
        DecisionNode yesBranch;
        DecisionNode noBranch;

        DecisionNode(String question) {
            this.question = question;
        }
    }

    public static void runDecisionTree(Scanner scanner, DecisionNode node) {
        while (node != null && (node.yesBranch != null || node.noBranch != null)) {
            System.out.println(node.question + " (yes/no)");
            String ans = scanner.nextLine().trim().toLowerCase();
            if (ans.equals("yes")) {
                node = node.yesBranch;
            } else {
                node = node.noBranch;
            }
        }
        if (node != null) {
            System.out.println("💡 決策結果: " + node.question);
        }
    }

    public static void main(String[] args) {

        MultiWayNode root = new MultiWayNode("A");
        MultiWayNode b = new MultiWayNode("B");
        MultiWayNode c = new MultiWayNode("C");
        MultiWayNode d = new MultiWayNode("D");
        MultiWayNode e = new MultiWayNode("E");
        MultiWayNode f = new MultiWayNode("F");

        root.children.addAll(Arrays.asList(b, c, d));
        b.children.add(e);
        d.children.add(f);

        System.out.println("✅ 深度優先走訪 (DFS):");
        dfs(root);
        System.out.println("\n✅ 廣度優先走訪 (BFS):");
        bfs(root);

        System.out.println("\n✅ 樹的高度: " + getHeight(root));
        System.out.println("✅ 每個節點的度數:");
        printDegrees(root);

        System.out.println("\n🔍 模擬決策樹：猜數字遊戲");

        DecisionNode q1 = new DecisionNode("你的數字 > 50?");
        DecisionNode q2 = new DecisionNode("你的數字 > 75?");
        DecisionNode q3 = new DecisionNode("你的數字 > 25?");
        DecisionNode leaf1 = new DecisionNode("你的數字可能是 80");
        DecisionNode leaf2 = new DecisionNode("你的數字可能是 60");
        DecisionNode leaf3 = new DecisionNode("你的數字可能是 30");
        DecisionNode leaf4 = new DecisionNode("你的數字可能是 10");

        q1.yesBranch = q2;
        q1.noBranch = q3;
        q2.yesBranch = leaf1;
        q2.noBranch = leaf2;
        q3.yesBranch = leaf3;
        q3.noBranch = leaf4;

        Scanner scanner = new Scanner(System.in);
        runDecisionTree(scanner, q1);
        scanner.close();
    }
}
