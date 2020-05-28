package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * Example 1:
 *
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 *
 * Input:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/spiral-matrix
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        return recursion(matrix, 0);
    }

    List<Integer> recursion(int[][] matrix, int index) {
        if ((index > (matrix.length - 1) / 2) || (index > (matrix[0].length - 1) / 2)) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        for (int i = index; i < matrix[0].length - index; i++) {
            result.add(matrix[index][i]);
        }
        for (int i = index + 1; i < matrix.length - index; i++) {
            result.add(matrix[i][matrix[0].length - index - 1]);
        }
        for (int i = matrix[0].length - index - 2; i >= index; i--) {
            if (index != matrix.length - index - 1) {
                result.add(matrix[matrix.length - index - 1][i]);
            }
        }
        for (int i = matrix.length - index - 2; i >= index + 1; i--) {
            if (index != matrix[0].length - index - 1) {
                result.add(matrix[i][index]);
            }
        }
        result.addAll(recursion(matrix, index + 1));
        return result;
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{{2, 5, 8}, {4, 0, -1}};
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        spiralMatrix.spiralOrder(input);
    }
}
