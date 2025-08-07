
import java.util.*;

public class RecursiveTreePreview {

    static class FileNode {

        String name;
        boolean isFile;
        List<FileNode> children;

        public FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            this.children = new ArrayList<>();
        }

        public void addChild(FileNode child) {
            this.children.add(child);
        }
    }

    public static int countFiles(FileNode node) {
        if (node.isFile) {
            return 1;
        }
        int total = 0;
        for (FileNode child : node.children) {
            total += countFiles(child);
        }
        return total;
    }

    static class MenuItem {

        String title;
        List<MenuItem> subItems;

        public MenuItem(String title) {
            this.title = title;
            this.subItems = new ArrayList<>();
        }

        public void addSubItem(MenuItem item) {
            subItems.add(item);
        }
    }

    public static void printMenu(MenuItem item, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + item.title);
        for (MenuItem sub : item.subItems) {
            printMenu(sub, level + 1);
        }
    }

    public static List<Integer> flatten(List<Object> nested) {
        List<Integer> result = new ArrayList<>();
        for (Object obj : nested) {
            if (obj instanceof Integer) {
                result.add((Integer) obj);
            } else if (obj instanceof List<?>) {
                result.addAll(flatten((List<Object>) obj));
            }
        }
        return result;
    }

    public static int maxDepth(List<Object> nested) {
        int max = 1;
        for (Object obj : nested) {
            if (obj instanceof List<?>) {
                max = Math.max(max, 1 + maxDepth((List<Object>) obj));
            }
        }
        return max;
    }

    public static void main(String[] args) {

        FileNode root = new FileNode("root", false);
        FileNode folder1 = new FileNode("folder1", false);
        FileNode folder2 = new FileNode("folder2", false);
        FileNode file1 = new FileNode("file1.txt", true);
        FileNode file2 = new FileNode("file2.txt", true);
        FileNode file3 = new FileNode("file3.txt", true);
        folder1.addChild(file1);
        folder1.addChild(file2);
        folder2.addChild(file3);
        root.addChild(folder1);
        root.addChild(folder2);
        System.out.println("📁 檔案總數: " + countFiles(root));  // Output: 3

        MenuItem main = new MenuItem("主選單");
        MenuItem file = new MenuItem("檔案");
        file.addSubItem(new MenuItem("開啟"));
        file.addSubItem(new MenuItem("儲存"));
        MenuItem edit = new MenuItem("編輯");
        edit.addSubItem(new MenuItem("剪下"));
        edit.addSubItem(new MenuItem("貼上"));
        main.addSubItem(file);
        main.addSubItem(edit);
        System.out.println("\n📑 多層選單結構:");
        printMenu(main, 0);

        List<Object> nestedArray = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, 4), 5), 6);
        System.out.println("\n📦 展平後: " + flatten(nestedArray));  // Output: [1, 2, 3, 4, 5, 6]

        System.out.println("\n📏 最大巢狀深度: " + maxDepth(nestedArray));  // Output: 3
    }
}
