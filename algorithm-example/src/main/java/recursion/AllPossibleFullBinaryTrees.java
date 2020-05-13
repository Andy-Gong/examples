package recursion;

import java.util.ArrayList;
import java.util.List;

import common.TreeNode;

/**
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 * Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.
 * Each node of each tree in the answer must have node.val = 0.
 * You may return the final list of trees in any order.
 *
 * Example 1:
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
        recursion(treeNodes, root, N - 1);
        return treeNodes;
    }

    public void recursion(List<TreeNode> treeNodes, TreeNode node, int N) {
        if (N == 0) {
            treeNodes.add(node);
            return;
        }
        node.left = new TreeNode(0);
        N--;
        node.right = new TreeNode(0);
        N--;
        if (N == 0) {
            treeNodes.add(node);
            return;
        }

        for (int i = 0; i <= N; i += 2) {
            recursion(treeNodes, node.left, i);
            recursion(treeNodes, node.right, N - i);
        }
    }

    public static void main(String[] args) {
        AllPossibleFullBinaryTrees allPossibleFullBinaryTrees = new AllPossibleFullBinaryTrees();
        System.out.println(allPossibleFullBinaryTrees.allPossibleFBT(7).size());
    }
}
