package dfs;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class ValidateBinarySearchTree {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.right != null && root.val >= root.right.val) {
            return false;
        }
        if (root.left != null && root.val <= root.left.val) {
            return false;
        }
        if (root.left != null) {
            int leftMax = maxLeft(root.left, root.left.val);
            if (root.val <= leftMax) {
                return false;
            }
        }
        if (root.right != null) {
            int rightMin = minRight(root.right, root.right.val);
            if (root.val >= rightMin) {
                return false;
            }
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    public int minRight(TreeNode root, int min) {
        if (root == null) {
            return min;
        }
        while (root.left != null) {
            min = root.left.val;
            root = root.left;
        }
        return min;
    }

    public int maxLeft(TreeNode root, int max) {
        if (root == null) {
            return max;
        }
        while (root.right != null) {
            max = root.right.val;
            root = root.right;
        }
        return max;
    }
}
