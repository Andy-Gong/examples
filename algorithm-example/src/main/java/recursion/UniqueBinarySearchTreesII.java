package recursion;

import java.util.ArrayList;
import java.util.List;

import common.TreeNode;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 *
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 */
public class UniqueBinarySearchTreesII {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return recursion(1, n);
    }

    List<TreeNode> recursion(int start, int end) {
        if (start > end) {
            List<TreeNode> result = new ArrayList<>();
            result.add(null);
            return result;
        }
        List<TreeNode> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = recursion(start, i - 1);
            List<TreeNode> right = recursion(i + 1, end);
            for (int j = 0; j < left.size(); j++) {
                for (int k = 0; k < right.size(); k++) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = left.get(j);
                    treeNode.right = right.get(k);
                    result.add(treeNode);
                }
            }
        }
        return result;
    }
}
