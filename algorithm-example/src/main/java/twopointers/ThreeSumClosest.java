package twopointers;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 *  
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *  
 *
 * Constraints:
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/3sum-closest
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                if (sum > target) {
                    if (Math.abs(sum - target) < closest) {
                        result = sum;
                        closest = Math.abs(sum - target);
                    }
                    right--;
                } else if (sum < target) {
                    if (Math.abs(sum - target) < closest) {
                        result = sum;
                        closest = Math.abs(sum - target);
                    }
                    left++;
                } else {
                    return target;
                }
            }
        }
        return result;
    }
}
