package hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 *
 * Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/lfu-cache
 */
public class LFUCache {

    Map<Integer, DListNode> values = new HashMap<>();
    DList dList;
    int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        dList = new DList();
    }

    public int get(int key) {
        DListNode dListNode = values.get(key);
        if (dListNode == null) {
            return -1;
        } else {
            dListNode.frequent = dListNode.frequent + 1;
            DListNode next = dListNode.next;
            while (dListNode.frequent >= next.frequent) {
                exchange(dListNode, next);
                next = dListNode.next;
            }
            return dListNode.val;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        DListNode dListNode = values.get(key);
        if (dListNode != null) {
            dListNode.val = value;
            dListNode.frequent = dListNode.frequent + 1;
            DListNode next = dListNode.next;
            while (dListNode.frequent >= next.frequent) {
                exchange(dListNode, next);
                next = dListNode.next;
            }
        } else {
            if (values.size() == capacity) {
                //remove the tail firstly
                DListNode headNode = dList.head.next;
                dList.head.next = headNode.next;
                headNode.next.pre = dList.head;
                values.remove(headNode.key);
            }
            //add to the head
            DListNode newNode = new DListNode(value, key, 0);
            values.put(key, newNode);
            dList.head.next.pre = newNode;
            newNode.next = dList.head.next;
            dList.head.next = newNode;
            newNode.pre = dList.head;
            DListNode next = newNode.next;
            while (newNode.frequent >= next.frequent) {
                exchange(newNode, next);
                next = newNode.next;
            }
        }
    }

    public void exchange(DListNode pre, DListNode next) {
        pre.next = next.next;
        next.next.pre = pre;
        pre.pre.next = next;
        next.pre = pre.pre;
        next.next = pre;
        pre.pre = next;
    }

    class DList {

        DListNode head;
        DListNode tail;

        public DList() {
            head = new DListNode(-1, -1, -1);
            tail = new DListNode(-1, -1, Integer.MAX_VALUE);
            head.pre = null;
            head.next = tail;
            tail.pre = head;
        }
    }

    public class DListNode {

        public int val;
        public int key;
        public int frequent;
        public DListNode next;
        public DListNode pre;

        public DListNode(int x, int key, int frequent) {
            this.val = x;
            this.key = key;
            this.frequent = frequent;
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2 /* capacity */);

        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);    // evicts key 2
        cache.put(4, 1);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

    }
}
