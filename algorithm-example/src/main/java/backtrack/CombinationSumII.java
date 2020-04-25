package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/combination-sum-ii
 */
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        bt(new ArrayList<Integer>(), candidates, target, 0, result);
        return result;
    }

    public void bt(List<Integer> selected, int[] candidates, int target, int layer, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(selected));
            return;
        }
        if (layer == candidates.length) {
            return;
        }
        for (int i = layer; i < candidates.length; i++) {
            if (target < candidates[i]) {
                continue;
            }
            boolean processedSameCandidate = false;
            for (int j = layer; j < i; j++) {
                if (candidates[i] == candidates[j]) {
                    processedSameCandidate = true;
                    break;
                }
            }
            //handle repeat candidate which may cause the duplicate result
            if (processedSameCandidate) {
                continue;
            }
            //handle the duplicate result as the candidates order are different
            if (!selected.isEmpty() && selected.get(selected.size() - 1) > candidates[i]) {
                continue;
            }
            selected.add(candidates[i]);
            bt(selected, candidates, target - candidates[i], i + 1, result);
            selected.remove(Integer.valueOf(candidates[i]));
        }
    }
}
