package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Note:
 *
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 */
public class IntersectionOfTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> resultArray = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length; ) {
            if (j >= nums2.length) {
                break;
            }
            if (nums1[i] == nums2[j]) {
                resultArray.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] result = new int[resultArray.size()];
        for (int i = 0; i < resultArray.size(); i++) {
            result[i] = resultArray.get(i);
        }
        return result;
    }
}
