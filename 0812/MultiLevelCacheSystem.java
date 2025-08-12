
import java.util.*;

public class MultiLevelCacheSystem {

    private static class CacheItem {

        int key;
        String value;
        int freq;
        long lastUsed;

        CacheItem(int key, String value) {
            this.key = key;
            this.value = value;
            this.freq = 0;
            touch();
        }

        void touch() {
            freq++;
            lastUsed = System.nanoTime();
        }
    }

    private static class CacheLevel {

        int capacity;
        LinkedHashMap<Integer, CacheItem> map; // LRU order

        CacheLevel(int capacity) {
            this.capacity = capacity;
            this.map = new LinkedHashMap<>(capacity, 0.75f, true);
        }

        boolean contains(int key) {
            return map.containsKey(key);
        }

        CacheItem get(int key) {
            return map.get(key);
        }

        void put(CacheItem item) {
            if (map.size() >= capacity) {
                Iterator<Map.Entry<Integer, CacheItem>> it = map.entrySet().iterator();
                if (it.hasNext()) {
                    it.next();
                    it.remove(); // evict LRU
                }
            }
            map.put(item.key, item);
        }

        void remove(int key) {
            map.remove(key);
        }

        List<Integer> keys() {
            return new ArrayList<>(map.keySet());
        }
    }

    private CacheLevel L1 = new CacheLevel(2);
    private CacheLevel L2 = new CacheLevel(5);
    private CacheLevel L3 = new CacheLevel(10);

    public void put(int key, String value) {
        if (L1.contains(key)) {
            L1.get(key).value = value;
            L1.get(key).touch();
            return;
        }
        if (L2.contains(key)) {
            L2.get(key).value = value;
            L2.get(key).touch();
            promote(L2, L1, key);
            return;
        }
        if (L3.contains(key)) {
            L3.get(key).value = value;
            L3.get(key).touch();
            promote(L3, L2, key);
            return;
        }

        CacheItem item = new CacheItem(key, value);
        insertIntoLevel(L1, item);
    }

    public String get(int key) {
        if (L1.contains(key)) {
            L1.get(key).touch();
            return L1.get(key).value;
        }
        if (L2.contains(key)) {
            L2.get(key).touch();
            if (L2.get(key).freq > 1) {
                promote(L2, L1, key);
            }
            return L2.get(key).value;
        }
        if (L3.contains(key)) {
            L3.get(key).touch();
            if (L3.get(key).freq > 1) {
                promote(L3, L2, key);
            }
            return L3.get(key).value;
        }
        return null;
    }

    private void promote(CacheLevel from, CacheLevel to, int key) {
        CacheItem item = from.get(key);
        from.remove(key);
        insertIntoLevel(to, item);
    }

    private void insertIntoLevel(CacheLevel level, CacheItem item) {
        if (level == L1) {
            if (L1.keys().size() >= L1.capacity) {
                int evictKey = L1.keys().get(0);
                CacheItem evicted = L1.get(evictKey);
                L1.remove(evictKey);
                insertIntoLevel(L2, evicted);
            }
            L1.put(item);
        } else if (level == L2) {
            if (L2.keys().size() >= L2.capacity) {
                int evictKey = L2.keys().get(0);
                CacheItem evicted = L2.get(evictKey);
                L2.remove(evictKey);
                insertIntoLevel(L3, evicted);
            }
            L2.put(item);
        } else {
            if (L3.keys().size() >= L3.capacity) {
                int evictKey = L3.keys().get(0);
                L3.remove(evictKey);
            }
            L3.put(item);
        }
    }

    public void printState() {
        System.out.println("L1: " + L1.keys());
        System.out.println("L2: " + L2.keys());
        System.out.println("L3: " + L3.keys());
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printState(); // L1: [2,3], L2: [1], L3: []

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printState(); // L1: [1,2], L2: [3], L3: []

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printState(); // L1: [1,4], L2: [3,5], L3: [6]
    }
}
