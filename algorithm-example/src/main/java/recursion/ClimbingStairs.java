package recursion;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 */
public class ClimbingStairs {

    public int numWays(int n) {
        long[] counts = new long[n];
        long totalCount = recursion(n, counts);
        return Integer.valueOf(String.valueOf(totalCount % 1000000007));
    }

    /**
     * recursion + dp
     */
    public long recursion(int k, long[] counts) {
        if (k == 0) {
            return 1L;
        }
        if (k < 0) {
            return 0;
        }
        if (counts[k - 1] != 0) {
            return counts[k - 1];
        }
        long count1 = recursion(k - 1, counts) % 1000000007L;
        long count2 = recursion(k - 2, counts) % 1000000007L;
        counts[k - 1] = count1 + count2;
        return count1 + count2;
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.numWays(92));
    }
}
