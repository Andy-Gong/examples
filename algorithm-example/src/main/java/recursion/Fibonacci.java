package recursion;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Fibonacci {

    public int fib(int n) {
        int[] values = new int[n + 1];
        return recursion(values, n);
    }

    public int recursion(int[] values, int k) {
        if (values[k] != 0) {
            return values[k];
        }
        if (k == 0) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int fib2 = recursion(values, k - 2) % 1000000007;
        values[k - 2] = fib2;
        int fib1 = recursion(values, k - 1) % 1000000007;
        values[k - 1] = fib1;
        int value = fib1 + fib2;
        values[k] = value % 1000000007;
        return value % 1000000007;
    }
}
