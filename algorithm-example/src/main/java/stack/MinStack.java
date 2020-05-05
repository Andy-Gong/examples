package stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 *  
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinStack {

    final List<Integer> values;
    int minValue = Integer.MAX_VALUE;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        values = new ArrayList<>();
    }

    public void push(int x) {
        if (x <= minValue) {
            values.add(minValue);
            minValue = x;
        }
        values.add(x);
    }

    public void pop() {
        if (values.remove(values.size() - 1) == minValue) {
            minValue = values.remove(values.size() - 1);
        }
    }

    public int top() {
        return values.get(values.size() - 1);
    }

    public int min() {
        return minValue;
    }
}
