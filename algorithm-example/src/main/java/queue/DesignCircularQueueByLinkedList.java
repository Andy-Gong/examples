package queue;

import common.ListNode;

/**
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 *
 * Your implementation should support following operations:
 *
 * MyCircularQueue(k): Constructor, set the size of the queue to be k.
 * Front: Get the front item from the queue. If the queue is empty, return -1.
 * Rear: Get the last item from the queue. If the queue is empty, return -1.
 * enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
 * deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
 * isEmpty(): Checks whether the circular queue is empty or not.
 * isFull(): Checks whether the circular queue is full or not.
 *  
 *
 * Example:
 *
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
 * circularQueue.enQueue(1);  // return true
 * circularQueue.enQueue(2);  // return true
 * circularQueue.enQueue(3);  // return true
 * circularQueue.enQueue(4);  // return false, the queue is full
 * circularQueue.Rear();  // return 3
 * circularQueue.isFull();  // return true
 * circularQueue.deQueue();  // return true
 * circularQueue.enQueue(4);  // return true
 * circularQueue.Rear();  // return 4
 *  
 * Note:
 *
 * All values will be in the range of [0, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in Queue library.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/design-circular-queue
 */
public class DesignCircularQueueByLinkedList {

    public static class MyCircularQueue {


        ListNode headNode;
        ListNode tailNode;
        int count = 0;
        final int maxCount;

        /**
         * Initialize your data structure here. Set the size of the queue to be k.
         */
        public MyCircularQueue(int k) {
            headNode = new ListNode(0);
            headNode.next = tailNode;
            maxCount = k;
        }

        /**
         * Insert an element into the circular queue. Return true if the operation is successful.
         */
        public boolean enQueue(int value) {
            if (count == maxCount) {
                return false;
            }
            if (count == 0) {
                ListNode tmp = new ListNode(value);
                headNode.next = tmp;
                tailNode = tmp;
            } else {
                tailNode.next = new ListNode(value);
                tailNode = tailNode.next;
            }
            count++;
            return true;
        }

        /**
         * Delete an element from the circular queue. Return true if the operation is successful.
         */
        public boolean deQueue() {
            if (count == 0) {
                return false;
            }
            headNode.next = headNode.next.next;
            count--;
            return true;
        }

        /**
         * Get the front item from the queue.
         */
        public int Front() {
            if (count == 0) {
                return -1;
            }
            return headNode.next.val;
        }

        /**
         * Get the last item from the queue.
         */
        public int Rear() {
            if (count == 0) {
                return -1;
            }
            return tailNode.val;
        }

        /**
         * Checks whether the circular queue is empty or not.
         */
        public boolean isEmpty() {
            return count == 0;
        }

        /**
         * Checks whether the circular queue is full or not.
         */
        public boolean isFull() {
            return count == maxCount;
        }
    }

    public static void main(String[] args) {
        /**
         * ["MyCircularQueue","enQueue","Rear","enQueue","deQueue","Front","deQueue","deQueue","isEmpty","deQueue","enQueue","enQueue"]
         * [[2],[4],[],[9],[],[],[],[],[],[],[6],[4]]
         *
         * [null,true,4,true,true,4,true,false,true,false,true,true]
         * [null,true,4,true,true,9,true,false,true,false,true,true]
         */
        MyCircularQueue circularQueue = new MyCircularQueue(2); // 设置长度为 3
        circularQueue.enQueue(4); // 返回 true
        //        circularQueue.enQueue(2); // 返回 true
        //        circularQueue.enQueue(3); // 返回 true
        //        circularQueue.enQueue(4); // 返回 false，队列已满
        circularQueue.Rear(); // 返回 3
        circularQueue.enQueue(9); // 返回 false，队列已满
        circularQueue.deQueue(); // 返回 true
        circularQueue.Front();
        //        circularQueue.isFull(); // 返回 true
        //        circularQueue.deQueue(); // 返回 true
        //        circularQueue.enQueue(4); // 返回 true
        //        circularQueue.Rear(); // 返回 4
    }
}
