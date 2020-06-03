package stack;

import java.util.Stack;

/**
 * Implement a MyQueue class which implements a queue using two stacks.
 *
 * Example:
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // return 1
 * queue.pop();   // return 1
 * queue.empty(); // return false
 *
 * Notes:
 * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/implement-queue-using-stacks-lcci
 */
public class ImplementQueueUsingStacks {

    class MyQueue {

        final Stack<Integer> stack;
        final Stack<Integer> popStack;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            stack = new Stack<>();
            popStack = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            stack.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (popStack.isEmpty()) {
                while (!stack.isEmpty()) {
                    popStack.push(stack.pop());
                }
            }
            return popStack.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (popStack.isEmpty()) {
                while (!stack.isEmpty()) {
                    popStack.push(stack.pop());
                }
            }
            return popStack.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return stack.isEmpty() && popStack.isEmpty();
        }

        public void appendTail(int value) {
            stack.push(value);
        }

        public int deleteHead() {
            if (stack.isEmpty() && popStack.isEmpty()) {
                return -1;
            }
            if (popStack.isEmpty()) {
                while (!stack.isEmpty()) {
                    popStack.push(stack.pop());
                }
            }
            return popStack.pop();
        }
    }
}
