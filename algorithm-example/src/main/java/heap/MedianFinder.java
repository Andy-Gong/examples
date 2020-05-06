package heap;

import java.util.PriorityQueue;

/**
 * Numbers are randomly generated and passed to a method. Write a program to find and maintain the median value as new values are generated.
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 *
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/continuous-median-lcci
 */
public class MedianFinder {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    /** initialize your data structure here. */
    public MedianFinder() {

    }

    public void addNum(int num) {
        queue.add(num);
    }

    public double findMedian() {
        queue.poll();
    }
}
