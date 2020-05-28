package hashmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a HashMap without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 * get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 *
 * Example:
 *
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);          
 * hashMap.put(2, 2);        
 * hashMap.get(1);            // returns 1
 * hashMap.get(3);            // returns -1 (not found)
 * hashMap.put(2, 1);          // update the existing value
 * hashMap.get(2);            // returns 1
 * hashMap.remove(2);          // remove the mapping for 2
 * hashMap.get(2);            // returns -1 (not found)
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/design-hashmap
 */
public class DesignHashMap {

    class MyHashMap {

        List<Entry>[] values = null;

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
            values = new List[1024];
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            int hash = hashCode(key);
            if (values[hash] == null) {
                values[hash] = new ArrayList<>();
                values[hash].add(new Entry(key, value));
                return;
            } else {
                for (int i = 0; i < values[hash].size(); i++) {
                    if (values[hash].get(i).key == key) {
                        values[hash].get(i).value = value;
                        return;
                    }
                }
                values[hash].add(new Entry(key, value));
            }

        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            int hash = hashCode(key);
            if (values[hash] == null) {
                return -1;
            }
            for (int i = 0; i < values[hash].size(); i++) {
                if (values[hash].get(i).key==key){
                    return values[hash].get(i).value;
                }
            }
            return -1;
        }
        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            int hash = hashCode(key);
            if (values[hash] == null) {
                return;
            }
            for (int i = 0; i < values[hash].size(); i++) {
                if (values[hash].get(i).key == key) {
                    values[hash].remove(i);
                    break;
                }
            }
        }

        int hashCode(int key) {
            return key % values.length;
        }

        public class Entry {

            int key;
            int value;

            public Entry(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
