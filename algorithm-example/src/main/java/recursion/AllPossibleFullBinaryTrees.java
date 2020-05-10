package recursion;

import java.util.ArrayList;
import java.util.List;

import common.TreeNode;

/**
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 * Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.
 *
 * Each node of each tree in the answer must have node.val = 0.
 *
 * You may return the final list of trees in any order.
 *
 *  
 *
 * Example 1:
 *
 * Input: 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/all-possible-full-binary-trees
 */
public class AllPossibleFullBinaryTrees {

    public List<TreeNode> allPossibleFBT(int N) {
        TreeNode root = new TreeNode(0);
        List<TreeNode> treeNodes = new ArrayList<>();
        recursion(treeNodes, root, null, N - 1);
        return treeNodes;
    }

    public void recursion(List<TreeNode> treeNodes, TreeNode leftNode, TreeNode rightNode, int N) {
        if (N <= 0) {
            treeNodes.add(leftNode);
            return;
        }
        if (leftNode != null) {
            leftNode.left = new TreeNode(0);
            leftNode.right = new TreeNode(0);
            recursion(treeNodes, leftNode.left, null, N - 2);
            recursion(treeNodes, null, leftNode.right, N - 2);
            recursion(treeNodes, leftNode.left, leftNode.right, N - 4);

        }
        if (rightNode != null) {
            rightNode.left = new TreeNode(0);
            rightNode.right = new TreeNode(0);
            recursion(treeNodes, rightNode.left, null, N - 2);
            recursion(treeNodes, null, rightNode.right, N - 2);
            recursion(treeNodes, rightNode.left, rightNode.right, N - 4);
        }
    }
}
