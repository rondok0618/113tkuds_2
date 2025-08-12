
import java.util.*;

public class PriorityQueueWithHeap {

    // 任務類別
    static class Task {

        String name;
        int priority;
        long timestamp; // 用於保持同優先級的先進先出

        Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return name + " (優先級: " + priority + ")";
        }
    }

    private PriorityQueue<Task> heap;
    private Map<String, Task> taskMap; // 用於快速查找任務
    private long counter = 0; // 用於記錄添加順序

    public PriorityQueueWithHeap() {
        heap = new PriorityQueue<>((a, b) -> {
            if (b.priority != a.priority) {
                return Integer.compare(b.priority, a.priority); // 高優先級先
            } else {
                return Long.compare(a.timestamp, b.timestamp); // 先加入的先
            }
        });
        taskMap = new HashMap<>();
    }

    // 添加任務
    public void addTask(String name, int priority) {
        if (taskMap.containsKey(name)) {
            System.out.println("任務已存在，請使用 changePriority 修改優先級");
            return;
        }
        Task task = new Task(name, priority, counter++);
        heap.offer(task);
        taskMap.put(name, task);
    }

    // 執行最高優先級任務
    public Task executeNext() {
        if (heap.isEmpty()) {
            System.out.println("沒有任務可執行");
            return null;
        }
        Task task = heap.poll();
        taskMap.remove(task.name);
        return task;
    }

    // 查看下一個要執行的任務
    public Task peek() {
        return heap.peek();
    }

    // 修改任務優先級
    public void changePriority(String name, int newPriority) {
        if (!taskMap.containsKey(name)) {
            System.out.println("任務不存在");
            return;
        }
        Task oldTask = taskMap.get(name);
        heap.remove(oldTask); // 從 heap 中移除
        Task newTask = new Task(name, newPriority, oldTask.timestamp);
        heap.offer(newTask);
        taskMap.put(name, newTask);
    }

    // 測試
    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("執行順序應為: 緊急修復 → 更新 → 備份");
        while (pq.peek() != null) {
            System.out.println("執行: " + pq.executeNext());
        }
    }
}
