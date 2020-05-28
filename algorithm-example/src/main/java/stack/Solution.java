package stack;

import java.util.Stack;

public class Solution {

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack();
        for (Character c : chars) {
            if (stack.isEmpty()) {
                stack.add(c);
            } else {
                if (isPeer(c, stack.peek())) {
                    stack.pop();
                } else {
                    stack.add(c);
                }
            }
        }
        return stack.isEmpty();
    }

    boolean isPeer(char c1, char c2) {
        if (c1 == '(' && c2 == ')') {
            return true;
        } else if (c1 == '{' && c2 == '}') {
            return true;
        } else if (c1 == '[' && c2 == ']') {
            return true;
        } else {
            return false;
        }
    }

    public int[] dailyTemperatures(int[] T) {
        for (int i = 0; i < T.length; i++) {
            boolean has = false;
            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    has = true;
                    T[i] = j - i;
                    break;
                }
            }
            if (!has) {
                T[i] = 0;
            }
        }
        return T;
    }
}
