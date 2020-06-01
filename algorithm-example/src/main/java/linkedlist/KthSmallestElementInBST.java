package linkedlist;

import common.TreeNode;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 *  
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 *    2
 * Output: 1
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * Output: 3
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 *
 *  
 *
 * Constraints:
 *
 * The number of elements of the BST is between 1 to 10^4.
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 */
public class KthSmallestElementInBST {

    TreeNode kthSmalliest = null;

    public int kthSmallest(TreeNode root, int k) {
        recursion(root, k);
        return kthSmalliest.val;
    }

    int recursion(TreeNode root, int k) {
        if (root == null) {
            return k;
        }
        if (root.left == null && root.right == null) {
            int newK = k - 1;
            if (newK == 0) {
                kthSmalliest = root;
            }
            return newK;
        }
        if (k == 0) {
            kthSmalliest = root;
            return k;
        }
        int valueLeft = recursion(root.left, k);
        valueLeft = valueLeft - 1;
        if (valueLeft == 0) {
            kthSmalliest = root;
            return valueLeft;
        }
        int valueRight = recursion(root.right, valueLeft);
        return valueRight;
    }
}
