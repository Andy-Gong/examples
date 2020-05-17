package heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

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
            Set<Long> valueSet = new HashSet<>();
            PriorityQueue<Long> priorityQueue = new PriorityQueue();
            priorityQueue.add(1L);
            while (n > 1) {
                long min = priorityQueue.poll();
                long value2 = min * 2;
                if (!valueSet.contains(value2)) {
                    priorityQueue.add(value2);
                    valueSet.add(value2);
                }
                long value3 = min * 3;
                if (!valueSet.contains(value3)) {
                    priorityQueue.add(value3);
                    valueSet.add(value3);
                }
                long value5 = min * 5;
                if (!valueSet.contains(value5)) {
                    priorityQueue.add(value5);
                    valueSet.add(value5);
                }
                n--;

            }
            return priorityQueue.poll().intValue();
        }
    }

    public static void main(String[] args) {
        UglyNumberII uglyNumberII = new UglyNumberII();
        uglyNumberII.nthUglyNumber(4);
    }
}
