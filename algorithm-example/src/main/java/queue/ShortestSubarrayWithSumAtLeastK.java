package queue;

/**
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 *
 * If there is no non-empty subarray with sum at least K, return -1.
 *
 *  
 *
 * Example 1:
 *
 * Input: A = [1], K = 1
 * Output: 1
 * Example 2:
 *
 * Input: A = [1,2], K = 4
 * Output: -1
 * Example 3:
 *
 * Input: A = [2,-1,2], K = 3
 * Output: 3
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k
 */
public class ShortestSubarrayWithSumAtLeastK {

    public int shortestSubarray(int[] A, int K) {
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            int sum = 0;
            for (int j = i; j < A.length; j++) {
                sum += A[j];
                if (sum >= K) {
                    shortest = Math.min(j - i + 1, shortest);
                    break;
                }
            }
        }
        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }

    /**
     * 前缀和
     */
    public int shortestSubarrayNew(int[] A, int K) {
        int[] sumA = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if (i == 0) {
                sumA[i] = A[i];
            } else {
                sumA[i] = sumA[i - 1] + A[i];
            }
        }
        int shortest = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < A.length; ++i) {
            while (sumA[j] - sumA[i - 1] >= K){
                shortest = Math.min(j - i + 1, shortest);
            }
            while (sumA[i]>sumA[j]){
                i=j;
            }
        }
        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }

    public static void main(String[] args) {
        int[] A = new int[]{48, 99, 37, 4, -31};
        ShortestSubarrayWithSumAtLeastK shortestSubarrayWithSumAtLeastK = new ShortestSubarrayWithSumAtLeastK();
        System.out.println(shortestSubarrayWithSumAtLeastK.shortestSubarray(A, 140));
    }
}
