package stack;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 * Input: "()"
 * Output: true
 *
 * Example 2:
 * Input: "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: "(]"
 * Output: false
 *
 * Example 4:
 * Input: "([)]"
 * Output: false
 *
 * Example 5:
 * Input: "{[]}"
 * Output: true
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/valid-parentheses
 */
public class ValidParentheses {

//    public boolean isValid(String s) {
//        if (s == null || s.length() == 0) {
//            return true;
//        }
//        if (s.length() % 2 != 0) {
//            return false;
//        }
//        char[] chars = s.toCharArray();
//        Stack<Character> charsStack = new Stack();
//        charsStack.push(chars[0]);
//        for (int i = 1; i < chars.length; i++) {
//            if (!charsStack.isEmpty() && isPeer(charsStack.peek(), chars[i])) {
//                charsStack.pop();
//            } else {
//                charsStack.push(chars[i]);
//            }
//        }
//        return charsStack.isEmpty();
//    }
//
//    public boolean isPeer(char char1, char char2) {
//        if (char1 == '(' && char2 == ')') {
//            return true;
//        } else if (char1 == '{' && char2 == '}') {
//            return true;
//        } else if (char1 == '[' && char2 == ']') {
//            return true;
//        }else {
//            return false;
//        }
//    }

    public boolean isValid(String s) {
        if (s == null || s.equals("")) {
            return true;
        }
        Stack<Character> stack = new Stack();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (stack.isEmpty()) {
                stack.add(c);
            } else {
                char latest = stack.peek();
                if (isPeer(latest, c)) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * '(', ')', '{', '}', '[' and ']'
     */
    public boolean isPeer(char c1, char c2) {
        if (c1 == '(' && c2 == ')') {
            return true;
        } else if (c1 == '{' && c2 == '}') {
            return true;
        } else if (c1 == '[' && c2 == ']') {
            return true;
        }
        return false;
    }
}
