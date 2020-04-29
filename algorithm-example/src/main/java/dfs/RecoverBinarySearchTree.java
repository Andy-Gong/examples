package dfs;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Example 1:
 *
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/recover-binary-search-tree
 */
public class RecoverBinarySearchTree {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public void recoverTree(TreeNode root) {
        dfs(null, root);
    }

    public void dfs(TreeNode parent, TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.right != null && root.left != null && root.val > root.right.val && root.val < root.left.val) {
            swap(root.left, root.right);
        }
        if (root.left != null && root.val < root.left.val) {
            swap(root, root.left);
            return;
        }
        if (root.left != null && root.left.right != null && root.val < root.left.right.val) {
            swap(root, root.left.right);
        }
        if (root.right != null && root.val > root.right.val) {
            swap(root, root.right);
        }
        if (root.right != null && root.right.left != null && root.val > root.right.left.val) {
            swap(root, root.right.left);
        }
        if (parent != null) {
            if (root.equals(parent.left)) {
                if (parent.val < root.val) {
                    swap(parent, root);
                }
            }
            if (root.equals(parent.right)) {
                if (parent.val > root.val) {
                    swap(parent, root);
                }
            }
        }
        dfs(root, root.left);
        dfs(root, root.right);
    }

    void swap(TreeNode node1, TreeNode node2) {
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }
}
