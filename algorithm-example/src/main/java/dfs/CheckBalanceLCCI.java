package dfs;

/**
 * Implement a function to check if a binary tree is balanced. For the purposes of this question,
 * a balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.
 *
 *
 * Example 1:
 *
 * Given tree [3,9,20,null,null,15,7]
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return true.
 *
 * source：（LeetCode）
 * link：https://leetcode-cn.com/problems/check-balance-lcci
 */
public class CheckBalanceLCCI {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(high(root.left) - high(root.right)) > 1) {
            return false;
        }
        boolean leftBalance = isBalanced(root.left);
        boolean rightBalance = isBalanced(root.right);
        return leftBalance && rightBalance;
    }

    int high(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int highLeft = high(root.left);
        int highRight = high(root.right);
        return Math.max(highLeft, highRight) + 1;
    }
}
