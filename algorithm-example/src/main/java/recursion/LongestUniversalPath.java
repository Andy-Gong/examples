package recursion;

import common.TreeNode;

/**
 * Given a binary tree, find the length of the longest path where each node in the path has the same value.
 * This path may or may not pass through the root. The length of path between two nodes is represented by the number of edges between them.
 * Example 1:
 * Input:
 *
 * 5
 * / \
 * 4   5
 * / \   \
 * 1   1   5
 * Output: 2
 * Example 2:
 * Input:
 *
 * 1
 * / \
 * 4   5
 * / \   \
 * 4   4   5
 * Output: 2
 */
public class LongestUniversalPath {

    private int res;

    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        longest(root);
        return res;
    }

    /**
     * function: f(n) the max length of the TreeNode sequence with same value
     * jump out condition: treeNode is null
     * equation:
     * if n.left.value = n.value, f(n-1).left +=1,
     * if n.right.value = n.value, f(n-1).right +=1,
     * f(n) = Max(f(n-1).left, f(n-1).right)
     */
    private int longest(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //divide into 2 sub-problems, left longest and right longest
        int left = longest(root.left);
        int right = longest(root.right);
        int leftLongest = 0;
        int rightLongest = 0;
        if (root.left != null && root.val == root.left.val) {
            leftLongest = left + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            rightLongest = right + 1;
        }
        int maxValue = Math.max(leftLongest, rightLongest);
        if (leftLongest > 0 && rightLongest > 0) {
            maxValue = Math.max(maxValue, leftLongest + rightLongest);
        } else if (leftLongest > 0 && rightLongest == 0) {
            maxValue = Math.max(leftLongest, right);
        } else if (leftLongest == 0 && rightLongest > 0) {
            maxValue = Math.max(left, rightLongest);
        } else {
            maxValue = Math.max(left, right);
        }
        res = Math.max(res, maxValue);
        return Math.max(leftLongest, rightLongest);
    }
}
