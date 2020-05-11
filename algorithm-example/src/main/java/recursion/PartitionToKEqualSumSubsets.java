package recursion;

import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *  
 *
 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets
 */
public class PartitionToKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int subSum = sum / k;
        int[] subsets = new int[k];
        Arrays.sort(nums);
        if (nums[nums.length - 1] > subSum) {
            return false;
        }
        return recursion(subsets, nums, 0, subSum);
    }

    public boolean recursion(int[] subsets, int[] nums, int index, int subSum) {
        if (index == nums.length) {
            for (int i = 0; i < subsets.length; i++) {
                if (subsets[i] != subSum) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < subsets.length; i++) {
            if (subsets[i] >= subSum) {
                continue;
            }
            if ((subsets[i] + nums[index]) <= subSum) {
                subsets[i] += nums[index];
                boolean result = recursion(subsets, nums, ++index, subSum);
                if (result) {
                    return true;
                } else {
                    index--;
                    subsets[i] -= nums[index];
                }
            }
            if (subsets[i] == 0) {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /**
         * [7628, 3147, 7137, 2578, 7742, 2746, 4264, 7704, 9532, 9679, 8963, 3223, 2133, 7792, 5911, 3979]
         * 6
         */
        PartitionToKEqualSumSubsets partitionToKEqualSumSubsets = new PartitionToKEqualSumSubsets();
        int[] sum = new int[]{7628, 3147, 7137, 2578, 7742, 2746, 4264, 7704, 9532, 9679, 8963, 3223, 2133, 7792, 5911,
            3979};
        System.out.println(partitionToKEqualSumSubsets.canPartitionKSubsets(sum, 6));
    }
}
