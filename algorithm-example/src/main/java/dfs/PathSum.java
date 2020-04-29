package dfs;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/path-sum
 */
public class PathSum {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (sum == root.val && (root.left != null || root.right != null)) {
            return false;
        }
        return dfs(root, sum);
    }

    boolean dfs(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (sum == root.val) {
            if (root.left == null && root.right == null) {
                if (sum == root.val) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        boolean hasLeftPath = dfs(root.left, sum - root.val);
        if (hasLeftPath) {
            return true;
        }
        boolean hasRightPath = dfs(root.right, sum - root.val);
        if (hasRightPath) {
            return true;
        }
        return false;
    }
}
