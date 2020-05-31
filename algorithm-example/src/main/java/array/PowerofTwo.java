package array;

/**
 * Given an integer, write a function to determine if it is a power of two.
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 * Explanation: 20 = 1
 * Example 2:
 *
 * Input: 16
 * Output: true
 * Explanation: 24 = 16
 * Example 3:
 *
 * Input: 218
 * Output: false
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/power-of-two
 */
public class PowerofTwo {

    public boolean isPowerOfTwo(int n) {
        if (n % 2 == 1) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        int next = n / 2;
        return isPowerOfTwo(next);
    }
}
