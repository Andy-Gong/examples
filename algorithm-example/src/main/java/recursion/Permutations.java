package recursion;

import java.util.ArrayList;
import java.util.List;

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

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> numArray = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            numArray.add(nums[i]);
        }
        recursion(new ArrayList<Integer>(), numArray, result);
        return result;
    }

    public void recursion(List<Integer> candidate, List<Integer> numArray, List<List<Integer>> result) {
        if (numArray.isEmpty()) {
            result.add(new ArrayList<Integer>(candidate));
            return;
        }
        for (int i = 0; i < numArray.size(); i++) {
            List<Integer> tmp = new ArrayList<Integer>(candidate);
            tmp.add(numArray.get(i));
            List<Integer> newNums = new ArrayList<Integer>(numArray);
            newNums.remove(numArray.get(i));
            recursion(tmp, newNums, result);
        }
    }
}
