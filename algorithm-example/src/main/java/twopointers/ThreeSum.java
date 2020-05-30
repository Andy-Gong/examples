package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 * The solution set must not contain duplicate triplets.
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/3sum
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            List<Integer> first = map.get(nums[i]);
            first.remove(Integer.valueOf(nums[i]));
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int sum = nums[i] + nums[j];
                List<Integer> third = map.get(-sum);
                if (third != null && !third.isEmpty()) {
                    boolean biggerThanJ = false;
                    for (int k = 0; k < third.size(); k++) {
                        if (third.get(k) > j) {
                            biggerThanJ = true;
                        }
                    }
                    if (biggerThanJ) {
                        result.add(Arrays.asList(nums[i], nums[j], -sum));
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = new int[]{-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        threeSum.threeSum(input);
    }
}
