package twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 *
 * Note:
 *
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * Example:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 */
public class TwoSumII {

    public int[] twoSum(int[] numbers, int target) {
        int[] indexes = new int[2];
        Map<Integer, Integer> values = new HashMap();
        for (int i = 0; i < numbers.length; i++) {
            values.put(Integer.valueOf(numbers[i]), i);
        }
        for (int i = 0; i < numbers.length; i++) {
            if (values.containsKey(Integer.valueOf(target - numbers[i]))) {
                indexes[0] = i + 1;
                indexes[1] = values.get(Integer.valueOf(target - numbers[i])) + 1;
                break;
            }
        }
        return indexes;
    }
}
