package heap;

import java.util.PriorityQueue;

/**
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
 *
 * Example:
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/ugly-number-ii
 */
public class UglyNumberII {

    /**
     * 每次拿出一个最小值，分别以2，3，5为因子计算ugly number
     */
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        } else {
            PriorityQueue<Long> priorityQueue = new PriorityQueue();
            priorityQueue.add(2L);
            priorityQueue.add(3L);
            priorityQueue.add(5L);
            while (n > 2) {
                long min = priorityQueue.poll();
                if (!priorityQueue.contains(min * 2)) {
                    priorityQueue.add(min * 2);
                }
                if (!priorityQueue.contains(min * 3)) {
                    priorityQueue.add(min * 3);
                }
                if (!priorityQueue.contains(min * 5)) {
                    priorityQueue.add(min * 5);
                }
                n--;

            }
            return priorityQueue.poll().intValue();
        }
    }
}
