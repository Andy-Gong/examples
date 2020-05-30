package twopointers;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 */
public class MedianofTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int sumLength = nums1.length + nums2.length;
        int middle = (sumLength - 1) / 2;
        int i = 0;
        int j = 0;
        int count = 0;
        int tmp = 0;
        int middleValue = 0;
        while (true) {
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] <= nums2[j]) {
                    tmp = nums1[i];
                    i++;
                } else {
                    tmp = nums2[j];
                    j++;
                }
            } else if (i == nums1.length && j < nums2.length) {
                tmp = nums2[j];
                j++;
            } else {
                tmp = nums1[i];
                i++;
            }
            if (count == middle && (sumLength - 1) % 2 == 0) {
                return tmp;
            }
            if (((sumLength - 1) % 2 == 1) && count == middle) {
                middleValue = tmp;
            } else if ((sumLength - 1) % 2 == 1 && count == (middle + 1)) {
                return (middleValue + tmp) / 2D;
            }
            count++;
        }
    }
}
