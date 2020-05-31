package tree;

import common.TreeNode;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 * 1
 * / \
 * 2   3
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 */
public class BinaryTreeMaximumPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        sum(root);
        return max;
    }

    int sum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int leftSum = sum(root.left);
        int rootValue = root.val;
        int rightSum = sum(root.right);
        if (rootValue >= 0) {
            int left = (leftSum > 0 ? leftSum : 0) + rootValue;
            int right = rootValue + (rightSum > 0 ? rightSum : 0);
            int sum = right + left - rootValue;
            if (sum > max) {
                max = sum;
            }
            return Math.max(left, right);
        } else {
            int sum = Math.max(Math.max(rootValue, Math.max(leftSum, rightSum)),
                rootValue + (leftSum == Integer.MIN_VALUE ? 0 : leftSum)
                    + (rightSum == Integer.MIN_VALUE ? 0 : rightSum));
            if (sum > max) {
                max = sum;
            }
            int over = Math.max((leftSum == Integer.MIN_VALUE ? 0 : leftSum) + rootValue,
                (rightSum == Integer.MIN_VALUE ? 0 : rightSum) + rootValue);
            return over;
        }
    }
}
