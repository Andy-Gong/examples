package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * source:LeetCode
 * link:https://leetcode-cn.com/problems/lru-cache
 */
public class LRUCache {

    Map<Integer, Entry> values = null;
    int capacity;
    int count = 0;
    int time = 0;

    public LRUCache(int capacity) {
        values = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Entry entry = values.get(key);
        if (entry == null) {
            return -1;
        } else {
            entry.lastUsedTime = ++time;
            return entry.value;
        }
    }

    public void put(int key, int value) {
        Entry entryValue = values.get(key);
        if (entryValue != null) {
            entryValue.value = value;
            entryValue.lastUsedTime = ++time;
            return;
        }
        if (count == capacity) {
            Entry least = new Entry(0, 0, Integer.MAX_VALUE);
            for (Entry entry : values.values()) {
                if (entry.lastUsedTime < least.lastUsedTime) {
                    least = entry;
                }
            }
            values.remove(least.key);
            count--;
        }
        values.put(key, new Entry(key, value, ++time));
        count++;

    }

    class Entry {

        int key;
        int value;
        int lastUsedTime;

        public Entry(int key, int value, int lastUsedTime) {
            this.key = key;
            this.value = value;
            this.lastUsedTime = lastUsedTime;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }
}
