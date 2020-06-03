package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/subsets-ii
 */
public class SubsetsII {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
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
        if (!(index > 0 && nums[index] == nums[index - 1] && item.size() > 0
            && item.get(item.size() - 1) == nums[index])) {
            backtrack(nums, index + 1, new ArrayList<>(item));
        }
    }
}
