package recursion;

/**
 * You are building a diving board by placing a bunch of planks of wood end-to-end.
 * There are two types of planks, one of length shorter and one of length longer.
 * You must use exactly K planks of wood. Write a method to generate all possible lengths for the diving board.
 * return all lengths in non-decreasing order.
 *
 * Example:
 * Input:
 * shorter = 1
 * longer = 2
 * k = 3
 * Output:  {3,4,5,6}
 * Note:
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/diving-board-lcci
 */
public class DivingBoard {

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            int[] boards = new int[1];
            boards[0] = k * shorter;
            return boards;
        }
        int[] result = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            result[i] = longer * i + shorter * (k - i);
        }
        return result;
    }
}
