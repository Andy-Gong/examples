package array;

/**
 * Given an array of integers, write a method to find indices m and n such that if you sorted elements m through n, the entire array would be sorted. Minimize n - m (that is, find the smallest such sequence).
 *
 * Return [m,n]. If there are no such m and n (e.g. the array is already sorted), return [-1, -1].
 *
 * Example:
 *
 * Input:  [1,2,4,7,10,11,7,12,6,7,16,18,19]
 * Output:  [3,9]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/sub-sort-lcci
 */
public class SubSort {

    public int[] subSort(int[] array) {
        if (array.length <= 2) {
            return new int[]{-1, -1};
        }
        int peakMid = -1;
        for (int i = array.length - 1; i >= 1; i--) {
            if (peakMid == -1 && array[i] < array[i - 1]) {
                peakMid = i - 1;
            }
            if (peakMid != -1 && array[i] > array[peakMid]) {
                peakMid = i;
            }
        }
        if (peakMid == -1) {
            return new int[]{-1, -1};
        } else {
            int rightIndex = peakMid;
            int rightMinValue = peakMid;
            for (int i = peakMid + 1; i < array.length; i++) {
                if (array[i] < array[peakMid]) {
                    rightIndex = i;
                }
                if (array[i] < array[rightMinValue]) {
                    rightMinValue = i;
                }
            }
            int leftIndex = peakMid;
            for (int i = peakMid; i >= 0; i--) {
                if (array[i] > array[rightMinValue]) {
                    leftIndex = i;
                }
            }
            return new int[]{leftIndex, rightIndex};
        }
    }

    public static void main(String[] args) {
        int[] values = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        SubSort subSort = new SubSort();
        subSort.subSort(values);
    }
}
