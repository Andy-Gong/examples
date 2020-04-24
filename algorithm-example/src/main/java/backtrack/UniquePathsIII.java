package backtrack;

/**
 * On a 2-dimensional grid, there are 4 types of squares:
 *
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 * Example 1:
 *
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 *
 * from：LeetCode
 * link：https://leetcode-cn.com/problems/unique-paths-iii
 */
public class UniquePathsIII {

    public int uniquePathsIII(int[][] grid) {
        //find the 1 as starting square
        //4-directional walks
        //satisfy condition: selectable square walks and last square value is 2
        //break condition: square value is -1
        return 0;
    }

    public void backTrack(int[][] selected, int[][] selectable, int x, int y) {

        for (int i = 0; i < 4; i++) {

        }
    }
}
