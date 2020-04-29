package dfs;

/**
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its minimum depth = 2.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 */
public class MinimumDepthOfBinaryTree {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depthLeft = minDepth(root.left) + 1;
        int depthRight = minDepth(root.right) + 1;
        if (depthRight == 1 || depthLeft == 1) {
            return Math.max(depthLeft, depthRight);
        }
        return Math.min(depthLeft, depthRight);
    }
}
