package heap;

import java.util.PriorityQueue;

/**
 * Design an algorithm to find the smallest K numbers in an array.
 *
 * Example:
 *
 * Input:  arr = [1,3,5,7,2,4,6,8], k = 4
 * Output:  [1,2,3,4]
 * Note:
 *
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/smallest-k-lcci
 */
public class SmallestK {


    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> valueQueue = new PriorityQueue<>();
        int[] kValues = new int[k];
        for (int i = 0; i < arr.length; i++) {
            valueQueue.add(arr[i]);
        }
        for (int i = 0; i < k; i++) {
            kValues[i]=valueQueue.poll();
        }
        return kValues;
    }
}
