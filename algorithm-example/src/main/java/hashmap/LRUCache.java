package hashmap;

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

    Map<Integer, DListNode> values = null;
    int capacity;
    DList dList;

    public LRUCache(int capacity) {
        this.values = new HashMap<>(capacity);
        this.capacity = capacity;
        this.dList = new DList();
    }

    public int get(int key) {
        DListNode entry = values.get(key);
        if (entry == null) {
            return -1;
        } else {
            entry.next.pre = entry.pre;
            entry.pre.next = entry.next;
            dList.head.next.pre=entry;
            entry.next=dList.head.next;
            dList.head.next=entry;
            entry.pre=dList.head;
            return entry.val;
        }
    }

    public void put(int key, int value) {
        DListNode entryValue = values.get(key);
        if (entryValue != null) {
            entryValue.val = value;
            entryValue.next.pre = entryValue.pre;
            entryValue.pre.next = entryValue.next;
            dList.head.next.pre=entryValue;
            entryValue.next=dList.head.next;
            dList.head.next=entryValue;
            entryValue.pre=dList.head;
            return;
        }
        if (values.size() == capacity) {
            DListNode pre = dList.tail.pre;
            pre.pre.next = dList.tail;
            dList.tail.pre = pre.pre;
            values.remove(pre.key);
        }
        DListNode dListNode = new DListNode(value, key);
        dListNode.next = dList.head.next;
        dList.head.next.pre = dListNode;
        dListNode.pre = dList.head;
        dList.head.next = dListNode;
        values.put(key, dListNode);
    }

    class DList {

        DListNode head;
        DListNode tail;

        public DList() {
            head = new DListNode(-1, -1);
            tail = new DListNode(-1, -1);
            head.pre = null;
            head.next = tail;
            tail.pre = head;
        }
    }

    public class DListNode {

        public int val;
        public int key;
        public DListNode next;
        public DListNode pre;

        public DListNode(int x, int key) {
            val = x;
            this.key = key;
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
