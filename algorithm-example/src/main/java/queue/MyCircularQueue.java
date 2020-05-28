package queue;

import java.util.ArrayList;
import java.util.List;

public class MyCircularQueue {

    private List<Integer> queue = null;
    private int start = 0;
    private int count = 0;
    private int size = 0;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue(int k) {
        size = k;
        queue = new ArrayList();
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (count == size) {
            return false;
        }
        if ((start + count) < size) {
            queue.add(start + count, value);
        } else {
            int index = (start + count) % size;
            queue.add(index, value);
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
        } else {
            start++;
            if (start == size) {
                start = 0;
            }
            count--;
            return true;
        }

    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (count == 0) {
            return -1;
        }
        return queue.get(start);

    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (count == 0) {
            return -1;
        }
        int index = (start + count - 1) % size;
        return queue.get(index);
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
        return count == size;
    }

    public static void main(String[] args) {
        /**
         * ["MyCircularQueue","enQueue","Rear","Rear","deQueue","enQueue","Rear","deQueue","Front","deQueue","deQueue","deQueue"]
         * [[6],[6],[],[],[],[5],[],[],[],[],[],[]]
         */
        MyCircularQueue circularQueue = new MyCircularQueue(6); // 设置长度为 3

        System.out.println(circularQueue.enQueue(6));  // 返回 true

        System.out.println(circularQueue.Rear());  // 返回 true

        System.out.println(circularQueue.Rear());  // 返回 true

        System.out.println(circularQueue.deQueue());  // 返回 false，队列已满

        System.out.println(circularQueue.enQueue(5));  // 返回 3

        System.out.println(circularQueue.Rear());  // 返回 true

        System.out.println(circularQueue.deQueue());  // 返回 true

        System.out.println(circularQueue.Front());  // 返回 true

        System.out.println(circularQueue.deQueue());  // 返回 4
        System.out.println(circularQueue.deQueue());  // 返回 4
        System.out.println(circularQueue.deQueue());  // 返回 4
        /**
         * ["MyCircularQueue","enQueue","enQueue","Rear","Rear","enQueue","enQueue","Rear","enQueue","deQueue","enQueue","Rear","deQueue","deQueue","deQueue","Rear","Front","enQueue","Front","Rear","Front","enQueue","enQueue","deQueue","Front","enQueue","deQueue","deQueue","Rear","isFull","enQueue","isEmpty","enQueue","Front","Front","deQueue","Front","deQueue","deQueue","deQueue","enQueue","deQueue","Rear","deQueue","Rear","Rear","Front","enQueue","Front","isEmpty","enQueue","enQueue","enQueue","enQueue","deQueue","enQueue","deQueue","enQueue","enQueue","Rear","enQueue","Front","enQueue","enQueue","enQueue","isEmpty","Front","enQueue","enQueue","deQueue","isFull","Front","Rear","deQueue","enQueue","Rear","isEmpty","enQueue","isEmpty","enQueue","enQueue","Front","enQueue","enQueue","Rear","Front","Rear","deQueue","enQueue","deQueue","enQueue","Rear","isEmpty","deQueue","deQueue","Front","enQueue","enQueue","enQueue","enQueue","enQueue","enQueue"]
         * [[26],[74],[39],[],[],[58],[26],[],[80],[],[98],[],[],[],[],[],[],[72],[],[],[],[41],[38],[],[],[74],[],[],[],[],[44],[],[60],[],[],[],[],[],[],[],[58],[],[],[],[],[],[],[18],[],[],[37],[69],[27],[48],[],[12],[],[76],[1],[],[54],[],[31],[35],[41],[],[],[39],[49],[],[],[],[],[],[50],[],[],[47],[],[49],[11],[],[54],[97],[],[],[],[],[15],[],[12],[],[],[],[],[],[34],[2],[4],[51],[39],[97]]
         *
         *
         * [null,true,true,39,39,true,true,26,true,true,true,98,true,true,true,98,80,true,80,72,80,true,true,true,98,true,true,true,74,false,true,false,true,41,41,true,38,true,true,true,true,true,58,true,-1,-1,-1,true,18,false,true,true,true,true,true,true,true,true,true,1,true,69,true,true,true,false,69,true,true,true,false,69,49,true,true,50,false,true,false,true,true,58,true,true,97,44,97,true,true,true,true,12,false,true,true,58,true,true,true,true,true,true]
         *
         * [null,true,true,39,39,true,true,26,true,true,true,98,true,true,true,98,80,true,80,72,80,true,true,true,98,true,true,true,74,false,true,false,true,41,41,true,38,true,true,true,true,true,58,true,-1,-1,-1,true,18,false,true,true,true,true,true,true,true,true,true,1,true,69,true,true,true,false,69,true,true,true,false,27,49,true,true,50,false,true,false,true,true,48,true,true,97,48,97,true,true,true,true,12,false,true,true,54,true,true,true,true,true,true]
         */

    }
}
