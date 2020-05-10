package hashmap;

import java.util.LinkedList;

/**
 * Design a HashSet without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 * add(value): Insert a value into the HashSet. 
 * contains(value) : Return whether the value exists in the HashSet or not.
 * remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
 *
 * Example:
 *
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);        
 * hashSet.add(2);        
 * hashSet.contains(1);    // returns true
 * hashSet.contains(3);    // returns false (not found)
 * hashSet.add(2);          
 * hashSet.contains(2);    // returns true
 * hashSet.remove(2);          
 * hashSet.contains(2);    // returns false (already removed)
 *
 * source：LeetCode
 * link: https://leetcode-cn.com/problems/design-hashset
 */
public class DesignHashSet705 {

    class MyHashSet {

        LinkedList[] entries;
        int bucketSize;

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {
            bucketSize = 512;
            entries = new LinkedList[bucketSize];
        }

        public void add(int key) {
            LinkedList value = entries[hash(key)];
            if (value == null) {
                value = new LinkedList();
                value.add(Integer.valueOf(key));
                entries[hash(key)] = value;
            } else if (!value.contains(Integer.valueOf(key))) {
                value.add(Integer.valueOf(key));
            }
        }

        public void remove(int key) {
            LinkedList value = entries[hash(key)];
            if (value != null && value.contains(Integer.valueOf(key))) {
                value.remove(Integer.valueOf(key));
            }
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            LinkedList value = entries[hash(key)];
            return value != null && value.contains(Integer.valueOf(key));
        }

        private int hash(int key) {
            return key % 512;
        }
    }
}
