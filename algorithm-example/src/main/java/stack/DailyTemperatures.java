package stack;

import java.util.Stack;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input,
 * tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/daily-temperatures
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        Stack<Entry> stack = new Stack();
        for (int i = 0; i < T.length; i++) {
            if (stack.isEmpty()) {
                stack.add(new Entry(i, T[i]));
            } else {
                int value = T[i];
                while (true) {
                    if (stack.isEmpty()) {
                        stack.add(new Entry(i, T[i]));
                        break;
                    }
                    Entry topValue = stack.peek();
                    if (topValue.value < value) {
                        T[topValue.index] = i - topValue.index;
                        stack.pop();
                    } else {
                        stack.add(new Entry(i, T[i]));
                        break;
                    }
                }
            }
        }
        if (!stack.isEmpty()) {
            while (!stack.isEmpty()) {
                Entry topValue = stack.pop();
                T[topValue.index] = 0;
            }
        }
        return T;
    }

    public static class Entry {

        int index;
        int value;

        public Entry(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
