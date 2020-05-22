package recursion;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：n = 7
 * 输出：21
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
