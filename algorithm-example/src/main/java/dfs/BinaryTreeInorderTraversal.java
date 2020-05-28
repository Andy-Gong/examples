package dfs;

import java.util.ArrayList;
import java.util.List;

import common.TreeNode;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 *
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * link：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 */
public class BinaryTreeInorderTraversal {

    List<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        dfs(root);
        return result;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result.add(root.val);
            return;
        }
        inorderTraversal(root.left);
        result.add(root.val);
        inorderTraversal(root.right);
    }
}
