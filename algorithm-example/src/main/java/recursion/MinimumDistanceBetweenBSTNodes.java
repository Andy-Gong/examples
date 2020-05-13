package recursion;

import common.TreeNode;

/**
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.
 *
 * Example :
 *
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 *
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 *
 * 4
 * /   \
 * 2      6
 * / \
 * 1   3
 *
 * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 */
public class MinimumDistanceBetweenBSTNodes {

    int diff = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return diff;
        }
        if (root.left != null) {
            TreeNode tmp = root.left;
            while (tmp.right != null) {
                tmp = tmp.right;
            }
            diff = Math.min(Math.abs(root.val - tmp.val), diff);
        }
        if (root.right != null) {
            TreeNode tmp = root.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            diff = Math.min(Math.abs(root.val - tmp.val), diff);
        }
        minDiffInBST(root.left);
        minDiffInBST(root.right);
        return diff;
    }
}
