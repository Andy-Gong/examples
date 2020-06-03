package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input:
 *
 *   1
 * /   \
 * 2     3
 * \
 * 5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/binary-tree-paths
 */
public class BinaryTreePaths {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList();
        binaryTreePaths(root, paths, new StringBuilder());
        return paths;
    }

    //dfs + back track
    public void binaryTreePaths(TreeNode root, List<String> paths, StringBuilder path) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (path.length() == 0) {
                paths.add(path.append(root.val).toString());
            } else {
                paths.add(path.append("->").append(root.val).toString());
            }
            return;
        }
        int length = path.length();
        if (path.length() == 0) {
            binaryTreePaths(root.left, paths, path.append(root.val));
            path.delete(length, path.length());
            binaryTreePaths(root.right, paths, path.append(root.val));
        } else {
            binaryTreePaths(root.left, paths, path.append("->").append(root.val));
            path.delete(length, path.length());
            binaryTreePaths(root.right, paths, path.append("->").append(root.val));
        }
    }
}
