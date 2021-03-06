package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 *
 * from：LeetCode
 * link：https://leetcode-cn.com/problems/combination-sum
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backTrack(new ArrayList<Integer>(), candidates, target, result);
        return result;
    }

    public void backTrack(List<Integer> selected, int[] selectable, int target,
        List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(selected));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = 0; i < selectable.length; i++) {
            if (!selected.isEmpty() && selectable[i] < selected.get(selected.size() - 1)) {
                continue;
            }
            if (selectable[i] > target) {
                continue;
            }
            selected.add(selectable[i]);
            backTrack(selected, selectable, target - selectable[i], result);
            selected.remove(Integer.valueOf(selectable[i]));
        }
    }

    public static void main(String[] args) {
        int[] input = new int[]{2, 3, 6, 7};
        CombinationSum combinationSum = new CombinationSum();
        combinationSum.combinationSum(input, 7);
    }
}
