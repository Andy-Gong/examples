package twopointers;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *  
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 */
public class TwoDimensionArray {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        if (matrix.length == 0||matrix[0].length==0) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] < target) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    } else if (matrix[i][j] > target) {
                        break;
                    }
                }
            } else if (matrix[i][0] == target) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    public static void main(String[] args){
        int[][] matrix=new int[][]{};
        TwoDimensionArray twoDimensionArray = new TwoDimensionArray();
        twoDimensionArray.findNumberIn2DArray(matrix,1);
    }
}
