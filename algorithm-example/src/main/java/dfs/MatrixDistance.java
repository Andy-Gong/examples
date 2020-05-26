package dfs;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 * Input:
 * [[0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Example 2:
 * Input:
 * [[0,0,0],
 * [0,1,0],
 * [1,1,1]]
 * Output:
 * [[0,0,0],
 * [0,1,0],
 * [1,2,1]]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/01-matrix
 */
public class MatrixDistance {

    public int[][] updateMatrix(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = dfs(matrix, i, j, i, j);
            }
        }
        return result;
    }

    int dfs(int[][] matrix, int rowH, int rowV, int h, int v) {
        if (h < 0 || v < 0 || h >= matrix.length || v >= matrix[0].length) {
            return Integer.MAX_VALUE;
        }
        if (matrix[h][v] == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int up = 0;
        int down = 0;
        if (v <= rowV) {
            left = dfs(matrix, h, v, h, v - 1);
        }
        if (v >= rowV) {
            right = dfs(matrix, h, v, h, v + 1);
        }
        if (h <= rowH) {
            up = dfs(matrix, h, v, h - 1, v);
        }
        if (h >= rowH) {
            down = dfs(matrix, h, v, h + 1, v);
        }
        int value = Math.min(Math.min(Math.min(left, right), up), down) + 1;
        return value;
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{{0, 1, 1, 0, 0}, {0, 1, 1, 0, 0}, {0, 1, 0, 0, 1}, {1, 1, 1, 1, 0},
            {1, 0, 0, 1, 0}};
        MatrixDistance matrixDistance = new MatrixDistance();
        matrixDistance.updateMatrix(input);
    }
}
