package recursion;

/**
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 *
 * Example 1:
 *
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 * source: LeetCode
 * link：https://leetcode-cn.com/problems/powx-n
 */
public class PowXN {

    public double myPow(double x, int n) {
        if (n < 0) {
            return 1 / recursion(x, -Long.valueOf(n));
        } else {
            return recursion(x, n);
        }
    }

    public double recursion(double x, long n) {
        if (n == 0L) {
            return 1;
        }
        if (n == 1L) {
            return x;
        }
        double half = recursion(x, n / 2);
        if (n % 2 == 0L) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
