package twopointers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Note:
 *
 * Each element in the result must be unique.
 * The result can be in any order.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/intersection-of-two-arrays
 */
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            nums1Set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (nums1Set.contains(nums2[i])){
                result.add(nums2[i]);
            }
        }
        int[] resultInts = new int[result.size()];
        Iterator<Integer> iterator = result.iterator();
        for (int i = 0; i < result.size(); i++) {
            resultInts[i]=iterator.next();
        }
        return resultInts;

    }
}
