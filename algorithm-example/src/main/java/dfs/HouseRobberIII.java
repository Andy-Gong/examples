package dfs;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [3,2,3,null,3,null,1]
 *
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/house-robber-iii
 */
public class HouseRobberIII {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int includeRoot = dfs(root.left, true) + dfs(root.right, true);
        int falseFalse = dfs(root.left, false) + dfs(root.right, false) + root.val;
        int falseTrue = dfs(root.left, false) + dfs(root.right, true);
        int trueFlase = dfs(root.left, true) + dfs(root.right, false);
        int trueTrue = dfs(root.left, true) + dfs(root.right, true);

        return Math.max(trueTrue, Math.max(Math.max(includeRoot, falseFalse), Math.max(falseTrue, trueFlase)));
    }

    public int dfs(TreeNode root, boolean include) {
        if (root == null) {
            return 0;
        }
        if (include) {
            int left = dfs(root.left, false);
            int right = dfs(root.right, false);
            return Math.max(left, right) + root.val;
        } else {
            int trueTrue = dfs(root.right, true) + dfs(root.left, true);
            int falseTrue = dfs(root.right, false) + dfs(root.left, true);
            int trueFalse = dfs(root.right, true) + dfs(root.left, false);
            int falseFalse = dfs(root.right, false) + dfs(root.left, false);
            return Math.max(Math.max(trueTrue, falseTrue), Math.max(trueFalse, falseFalse));
        }
    }
    //    public int rob(TreeNode root) {
    //        if (root == null) {
    //            return 0;
    //        }
    //        int includeRoot = dfs(root.left, true) + dfs(root.right, true);
    //        int excludeRoot = dfs(root.left, false) + dfs(root.right, false) + root.val;
    //        return Math.maxLeft(includeRoot, excludeRoot);
    //    }
    //
    //    public int dfs(TreeNode root, boolean include) {
    //        if (root == null) {
    //            return 0;
    //        }
    //        if (include) {
    //            int left = dfs(root.left, false);
    //            int right = dfs(root.right, false);
    //            return Math.maxLeft(left, right) + root.val;
    //        } else {
    //            int trueTrue = dfs(root.right, true)+dfs(root.left, true);
    //            int falseTrue = dfs(root.right, false)+dfs(root.left, true);
    //            int trueFalse = dfs(root.right, true)+dfs(root.left, false);
    //            int falseFalse = dfs(root.left, false)+dfs(root.right, false);
    //            return Math.maxLeft(Math.maxLeft(trueTrue, falseTrue),Math.maxLeft(trueFalse,falseFalse));
    //        }
    //    }
}
