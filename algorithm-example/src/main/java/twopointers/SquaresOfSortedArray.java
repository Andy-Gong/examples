package twopointers;

/**
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
 *
 *  
 *
 * Example 1:
 *
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Example 2:
 *
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 */
public class SquaresOfSortedArray {

    public int[] sortedSquares(int[] A) {
        int crossIndex = 0;
        if (A.length == 0) {
            return A;
        }
        if (A.length == 1) {
            return new int[]{A[0] * A[0]};
        }
        if (A[0] < 0) {
            for (int i = 1; i < A.length; i++) {
                if (A[i] >= 0 && A[i - 1] < 0) {
                    crossIndex = i;
                }
            }
        }
        int[] result = new int[A.length];
        for (int i = crossIndex, j = crossIndex - 1, k = 0; k < result.length; k++) {
            if (j < 0) {
                result[k] = A[i] * A[i];
                i++;
            } else if (i >= A.length) {
                result[k] = A[j] * A[j];
                j--;
            } else {
                if (A[i] < -A[j]) {
                    result[k] = A[i] * A[i];
                    i++;
                } else if (A[i] > -A[j]) {
                    result[k] = A[j] * A[j];
                    j--;
                } else {
                    result[k] = A[i] * A[i];
                    result[++k] = A[j] * A[j];
                    i++;
                    j--;
                }
            }
        }
        return result;
    }
}
