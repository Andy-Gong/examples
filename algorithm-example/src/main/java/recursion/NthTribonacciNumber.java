package recursion;

/**
 * The Tribonacci sequence Tn is defined as follows: 
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * Given n, return the value of Tn.
 *
 * Example 1:
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * Example 2:
 * Input: n = 25
 * Output: 1389537
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/n-th-tribonacci-number
 */
public class NthTribonacciNumber {

    int[] calculatedValue = null;

    //recursion + dp
    public int tribonacci(int n) {
        if (calculatedValue == null) {
            calculatedValue = new int[n];
        }
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        }
        if (calculatedValue[n - 1] != 0) {
            return calculatedValue[n - 1];
        }
        int N1 = tribonacci(n - 1);
        calculatedValue[n - 2] = N1;
        int N2 = tribonacci(n - 2);
        calculatedValue[n - 3] = N2;
        int N3 = tribonacci(n - 3);
        if (n - 4 >= 0) {
            calculatedValue[n - 4] = N3;

        }
        return N1 + N2 + N3;
    }

    public static void main(String[] args) {
        NthTribonacciNumber nthTribonacciNumber = new NthTribonacciNumber();
        nthTribonacciNumber.tribonacci(3);
    }
}
