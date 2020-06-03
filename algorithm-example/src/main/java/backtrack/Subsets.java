package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/subsets
 */
public class Subsets {

    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0, new ArrayList<>());
        return result;
    }

    void backtrack(int[] nums, int index, List<Integer> item) {
        if (index == nums.length) {
            result.add(item);
            return;
        }
        List<Integer> tmp1 = new ArrayList<>(item);
        tmp1.add(nums[index]);
        backtrack(nums, index + 1, tmp1);
        backtrack(nums, index + 1, new ArrayList<>(item));
    }
}
