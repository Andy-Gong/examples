package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/combinations
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        bt(new ArrayList<Integer>(), n, k,1, result);
        return result;
    }

    public void bt(List<Integer> selected, int n, int size, int begin, List<List<Integer>> result) {
        if (selected.size() == size) {
            result.add(new ArrayList<Integer>(selected));
            return;
        }
        for (int i = begin; i <= n; i++) {
            if (selected.contains(i)) {
                continue;
            }
            selected.add(i);
            bt(selected, n, size, selected.get(selected.size() - 1) + 1, result);
            selected.remove(Integer.valueOf(i));
        }
    }


    public static void main(String[] args) {
        Combinations combinationSum = new Combinations();
        System.out.println(combinationSum.combine(4, 2));
    }
}
