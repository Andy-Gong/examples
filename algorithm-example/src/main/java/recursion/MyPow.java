package recursion;

/**
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 */
public class MyPow {

    /**
     * 分治
     */
    public double myPow(double x, int n) {
        if (x == 1 || n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / recursion(x, -n);
        }
        return recursion(x, n);
    }

    public double recursion(double x, long k) {
        if (k == 0) {
            return 1;
        }
        if (k == 1) {
            return x;
        }
        if (k % 2 == 0) {
            return recursion(x, k / 2) * recursion(x, k / 2);
        } else {
            return recursion(x, (k - 1) / 2) * recursion(x, (k - 1) / 2) * x;
        }
    }

    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        System.out.println(myPow.myPow(2.0, -2147483648));
    }
}
