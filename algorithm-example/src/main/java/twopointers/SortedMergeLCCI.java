package twopointers;

/**
 * You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write a method to merge B into A in sorted order.
 *
 * Initially the number of elements in A and B are m and n respectively.
 *
 * Example:
 *
 * Input:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/sorted-merge-lcci
 */
public class SortedMergeLCCI {

    public void merge(int[] A, int m, int[] B, int n) {
        if (n == 0) {
            return;
        }
        int latestInsertPosition = 0;
        for (int i = 0; i < n; i++) {
            if (m == 0) {
                A[i] = B[i];
                continue;
            }
            if (B[i] >= A[m - 1]) {
                A[m++] = B[i];
                continue;
            }
            for (int j = latestInsertPosition; j < m; j++) {
                if (A[j] >= B[i]) {
                    for (int k = m; k > j; k--) {
                        A[k] = A[k - 1];
                    }
                    A[j] = B[i];
                    m++;
                    latestInsertPosition = j;
                    break;
                }
            }
        }
    }

    public void doublePointers(int[] A, int m, int[] B, int n) {
        for (int i = A.length - 1; i >= 0; i--) {
            if (m < 1 && n >= 1) {
                A[i] = B[n - 1];
                n--;
                continue;
            }
            if (m >= 1 && n < 1) {
                return;
            }
            if (A[m - 1] > B[n - 1]) {
                A[i] = A[m - 1];
                m--;
            } else if (A[m - 1] < B[n - 1]) {
                A[i] = B[n - 1];
                n--;
            } else {
                A[i] = B[n - 1];
                A[i - 1] = A[m - 1];
                m--;
                n--;
                i--;
            }
        }
    }
}
