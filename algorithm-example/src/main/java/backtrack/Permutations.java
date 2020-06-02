package backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/permutations
 */
public class Permutations {

    //    public List<List<Integer>> permute(int[] nums) {
    //        List<List<Integer>> result = new ArrayList<List<Integer>>();
    //        bt(new ArrayList<Integer>(), nums, result);
    //        return result;
    //    }
    //
    //    public void bt(List<Integer> selected, int[] selectable, List<List<Integer>> result) {
    //        if (selected.size() == selectable.length) {
    //            result.add(new ArrayList<Integer>(selected));
    //            return;
    //        }
    //        for (int i = 0; i < selectable.length; i++) {
    //            if (selected.contains(selectable[i])) {
    //                continue;
    //            }
    //            selected.add(selectable[i]);
    //            bt(selected, selectable, result);
    //            selected.remove(Integer.valueOf(selectable[i]));
    //        }
    //    }

    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums,new HashSet<>(), new ArrayList<>());
        return result;
    }

    void backtrack(int[] nums, Set<Integer> selected, List<Integer> item) {
        if (nums.length == item.size()) {
            result.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!selected.contains(nums[i])) {
                item.add(nums[i]);
                selected.add(nums[i]);
                backtrack(nums, selected, item);
                item.remove(Integer.valueOf(nums[i]));
                selected.remove(nums[i]);
            }
        }
    }
}
