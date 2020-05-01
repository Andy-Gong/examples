package greedy;

import java.util.Arrays;

/**
 * Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)
 *
 * Return the largest possible sum of the array after modifying it in this way.
 *
 *  
 *
 * Example 1:
 *
 * Input: A = [4,2,3], K = 1
 * Output: 5
 * Explanation: Choose indices (1,) and A becomes [4,-2,3].
 * Example 2:
 *
 * Input: A = [3,-1,0,2], K = 3
 * Output: 6
 * Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations
 */
public class MaximizeSumOfArrayAfterKNegations {

    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (K > 0) {
                if (A[i] < 0) {
                    A[i] = -A[i];
                    K--;
                } else if (A[i] == 0) {
                    K = 0;
                } else {
                    if (K % 2 != 0) {
                        if (i != 0) {
                            if (A[i - 1] < A[i]) {
                                A[i] = A[i] - 2 * A[i - 1];
                            } else {
                                A[i] = -A[i];
                            }
                        } else {
                            A[i] = -A[i];
                        }
                    }
                    K = 0;
                }
            }
            sum += A[i];
        }
        return sum;
    }
}
