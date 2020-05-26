package stack;

import java.util.Stack;

/**
 * 根据逆波兰表示法，求表达式的值。
 *
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 *
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 *
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        int result = 0;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (isOperator(tokens[i])) {
                int second = Integer.valueOf(stack.pop());
                int first = Integer.valueOf(stack.pop());
                String tmp = null;
                if (tokens[i].equals("+")) {
                    tmp = String.valueOf(first + second);
                } else if (tokens[i].equals("-")) {
                    tmp = String.valueOf(first - second);
                } else if (tokens[i].equals("/")) {
                    tmp = String.valueOf(first / second);
                } else {
                    tmp = String.valueOf(first * second);
                }
                stack.add(tmp);
            } else {
                stack.add(tokens[i]);
            }
        }
        return Integer.valueOf(stack.pop());
    }

    boolean isOperator(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        String[] strings = new String[]{"2","1","+","3","*"};
        EvalRPN evalRPN = new EvalRPN();
        evalRPN.evalRPN(strings);
    }

}
