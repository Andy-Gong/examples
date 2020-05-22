package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [[7], [2,2,3]]
 *
 * from：LeetCode
 * link：https://leetcode-cn.com/problems/combination-sum
 */
public class CombinationSum {

    //dfs + cut, 92.16%
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        recursion(candidates, target, 0, result, new ArrayList<Integer>());
        return result;
    }

    /**
     * function: f(n) find candidates whose sum is target
     * jump condition:
     *      sum <= 0 or layer = candidates.size,
     *      if sum=0, find one solution
     * equation: f(n-1) = f(n) - candidates[i]*j, j is the count of candidates[i]
     * @param candidates
     * @param target
     * @param layer
     * @param result
     * @param candidate
     */
    public void recursion(int[] candidates, int target, int layer, List<List<Integer>> result,
        List<Integer> candidate) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(candidate);
            return;
        }
        if (layer == candidates.length) {
            return;
        }
        if (candidates[layer] == target) {
            candidate.add(candidates[layer]);
            result.add(candidate);
            return;
        } else if (candidates[layer] > target) {
            return;
        }
        int value = candidates[layer];
        for (int i = 0; i <= target / value; i++) {
            if (i == 0) {
                recursion(candidates, target, layer + 1, result, new ArrayList<Integer>(candidate));
            } else {
                candidate.add(value);
                recursion(candidates, target - i * value, layer + 1, result, new ArrayList<Integer>(candidate));
            }
        }
    }
}
