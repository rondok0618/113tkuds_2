// 檔名：AVLLeaderboardSystem.java

import java.util.*;

public class AVLLeaderboardSystem {

    private Node root;
    private Map<String, Integer> playerScores = new HashMap<>(); // 玩家名稱 -> 分數

    // 節點類別（擴充版）
    private static class Node {

        int score;       // 分數（作為排序依據）
        Set<String> players; // 該分數的玩家集合
        Node left, right;
        int height;
        int size;        // 子樹節點數（用於排名計算）

        Node(int score, String player) {
            this.score = score;
            this.players = new HashSet<>();
            this.players.add(player);
            this.height = 1;
            this.size = 1;
        }
    }

    // ===== 工具方法 =====
    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getSize(Node node) {
        return (node == null) ? 0 : node.size;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private void updateNode(Node node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            node.size = getSize(node.left) + getSize(node.right) + node.players.size();
        }
    }

    // ===== 旋轉操作 =====
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateNode(x);
        updateNode(y);

        return y;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateNode(y);
        updateNode(x);

        return x;
    }

    // ===== 插入玩家分數 =====
    public void addPlayer(String name, int score) {
        if (playerScores.containsKey(name)) {
            updateScore(name, score); // 若已存在則更新
        } else {
            root = insertNode(root, score, name);
            playerScores.put(name, score);
        }
    }

    private Node insertNode(Node node, int score, String player) {
        if (node == null) {
            return new Node(score, player);
        }

        if (score < node.score) {
            node.left = insertNode(node.left, score, player);
        } else if (score > node.score) {
            node.right = insertNode(node.right, score, player);
        } else {
            node.players.add(player);
        }

        updateNode(node);

        int balance = getBalance(node);

        // LL
        if (balance > 1 && score < node.left.score) {
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && score > node.right.score) {
            return leftRotate(node);
        }
        // LR
        if (balance > 1 && score > node.left.score) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && score < node.right.score) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // ===== 更新玩家分數 =====
    public void updateScore(String name, int newScore) {
        if (!playerScores.containsKey(name)) {
            return;
        }

        int oldScore = playerScores.get(name);
        root = removePlayerFromScore(root, oldScore, name);
        root = insertNode(root, newScore, name);
        playerScores.put(name, newScore);
    }

    // 從節點移除玩家
    private Node removePlayerFromScore(Node node, int score, String player) {
        if (node == null) {
            return null;
        }

        if (score < node.score) {
            node.left = removePlayerFromScore(node.left, score, player);
        } else if (score > node.score) {
            node.right = removePlayerFromScore(node.right, score, player);
        } else {
            node.players.remove(player);
            if (node.players.isEmpty()) {
                // BST 刪除節點
                if (node.left == null) {
                    return node.right;
                }
                if (node.right == null) {
                    return node.left;
                }

                Node successor = findMin(node.right);
                node.score = successor.score;
                node.players = successor.players;
                node.right = removeNode(node.right, successor.score);
            }
        }

        updateNode(node);

        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 刪除整個分數節點
    private Node removeNode(Node node, int score) {
        if (node == null) {
            return null;
        }

        if (score < node.score) {
            node.left = removeNode(node.left, score);
        } else if (score > node.score) {
            node.right = removeNode(node.right, score);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node successor = findMin(node.right);
            node.score = successor.score;
            node.players = successor.players;
            node.right = removeNode(node.right, successor.score);
        }

        updateNode(node);
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ===== 查詢玩家排名（分數高的排名靠前）=====
    public int getRank(String name) {
        if (!playerScores.containsKey(name)) {
            return -1;
        }
        int score = playerScores.get(name);
        return getRankByScore(root, score);
    }

    private int getRankByScore(Node node, int score) {
        if (node == null) {
            return 0;
        }
        if (score < node.score) {
            return getRankByScore(node.left, score) + getSize(node.right) + node.players.size();
        } else if (score > node.score) {
            return getRankByScore(node.right, score);
        } else {
            return getSize(node.right) + 1; // 排名 = 右子樹節點數 + 1
        }
    }

    // ===== 查詢前 K 名玩家 =====
    public List<String> getTopK(int k) {
        List<String> result = new ArrayList<>();
        getTopKHelper(root, k, result);
        return result;
    }

    private void getTopKHelper(Node node, int k, List<String> result) {
        if (node == null || result.size() >= k) {
            return;
        }

        getTopKHelper(node.right, k, result); // 先右（高分）
        if (result.size() < k) {
            for (String p : node.players) {
                if (result.size() < k) {
                    result.add(p);
                }
            }
        }
        getTopKHelper(node.left, k, result);
    }

    // ===== 測試程式 =====
    public static void main(String[] args) {
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();

        lb.addPlayer("Alice", 50);
        lb.addPlayer("Bob", 70);
        lb.addPlayer("Charlie", 60);
        lb.addPlayer("David", 80);
        lb.addPlayer("Eve", 70);

        System.out.println("前 3 名玩家: " + lb.getTopK(3));
        System.out.println("Bob 排名: " + lb.getRank("Bob"));

        lb.updateScore("Alice", 85);
        System.out.println("更新 Alice 後，前 3 名玩家: " + lb.getTopK(3));
        System.out.println("Alice 排名: " + lb.getRank("Alice"));
    }
}
