package recursion;

import common.TreeNode;

/**
 * The data structure TreeNode is used for binary tree, but it can also used to represent a single linked list (where left is null, and right is the next node in the list).
 * Implement a method to convert a binary search tree (implemented with TreeNode) into a single linked list.
 * The values should be kept in order and the operation should be performed in place (that is, on the original data structure).
 *
 * Return the head node of the linked list after converting.
 *
 * Note: This problem is slightly different from the original one in the book.
 *
 *  
 *
 * Example:
 *
 * Input:  [4,2,5,1,3,null,6,0]
 * Output:  [0,null,1,null,2,null,3,null,4,null,5,null,6]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/binode-lcci
 */
public class BiNode {

    public TreeNode convertBiNode(TreeNode root) {
        TreeNode dump = new TreeNode(0);
        recursion(dump, root);
        return dump.right;
    }

    public TreeNode recursion(TreeNode result, TreeNode root) {
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            result.right = new TreeNode(root.val);
            return result.right;
        }
        TreeNode leftResult = recursion(result, root.left);
        leftResult.right = new TreeNode(root.val);
        return recursion(leftResult.right, root.right);
    }
}
