package dfs;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 *
 * link: https://leetcode-cn.com/problems/number-of-islands/
 */
public class NumIslands {

    int count = 0;

    public int numIslands(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                char value = grid[i][j];
                if (value == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int horizontal, int vertical) {
        char value = grid[horizontal][vertical];
        if (value == '0') {
            return;
        }
        grid[horizontal][vertical] = '0';
        if (horizontal < grid.length) {
            dfs(grid, horizontal + 1, vertical);
        }
        if (vertical < grid.length) {
            dfs(grid, horizontal, vertical + 1);
        }
        if (vertical > 0) {
            dfs(grid, horizontal, vertical - 1);
        }
        if (horizontal > 0) {
            dfs(grid, horizontal - 1, vertical);
        }
    }

    public static void main(String[] args) {
        //        char[][] grid = new char[][]{{"1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]
        //}
    }
}
