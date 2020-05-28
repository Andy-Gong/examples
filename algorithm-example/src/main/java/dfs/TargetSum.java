package dfs;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/target-sum
 */
public class TargetSum {

    int count = 0;

    public int findTargetSumWays(int[] nums, int S) {
        dfs(nums, S, 0);
        return count;
    }

    public void dfs(int[] nums, int S, int index) {
        if (nums.length == index && S == 0) {
            count++;
            return;
        }
        if (nums.length == index && S != 0) {
            return;
        }
        int value = nums[index];
        dfs(nums, S - value, index + 1);
        dfs(nums, S + value, index + 1);

    }
}
