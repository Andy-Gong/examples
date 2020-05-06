package heap;

import java.util.PriorityQueue;

/**
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 *
 * Example:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 */
public class KthLargest {

    private int kth;
    private PriorityQueue<Integer> valuesQueue = new PriorityQueue();

    public KthLargest(int k, int[] nums) {
        this.kth = k;
        for (int i = 0; i < nums.length; i++) {
            this.add(nums[i]);
        }
    }

    public int add(int val) {
        if (valuesQueue.size() < kth) {
            valuesQueue.add(val);
        } else if (val > valuesQueue.peek()) {
            valuesQueue.poll();
            valuesQueue.add(val);
        }
        return valuesQueue.peek();
    }
}
