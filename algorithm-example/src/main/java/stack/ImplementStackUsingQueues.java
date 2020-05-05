package stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement the following operations of a stack using queues.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * Example:
 *
 * MyStack stack = new MyStack();
 *
 * stack.push(1);
 * stack.push(2);
 * stack.top();   // returns 2
 * stack.pop();   // returns 2
 * stack.empty(); // returns false
 * Notes:
 *
 * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 * Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 * You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 */
public class ImplementStackUsingQueues {

    class MyStack {

        final Queue<Integer> popQueue;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            popQueue = new LinkedList();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            popQueue.add(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            int count = popQueue.size();
            while (count > 1) {
                popQueue.add(popQueue.poll());
                count--;
            }
            return popQueue.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            int count = popQueue.size();
            while (count > 1) {
                popQueue.add(popQueue.poll());
                count--;
            }
            int result = popQueue.peek();
            popQueue.add(popQueue.poll());
            return result;
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return popQueue.isEmpty();
        }
    }

}
