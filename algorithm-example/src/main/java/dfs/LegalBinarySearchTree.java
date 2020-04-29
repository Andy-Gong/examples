package dfs;

public class LegalBinarySearchTree {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.right != null && root.val >= root.right.val) {
            return false;
        }
        if (root.left != null && root.val <= root.left.val) {
            return false;
        }
        if (root.left != null) {
            int leftMax = maxLeft(root.left, root.left.val);
            if (root.val <= leftMax) {
                return false;
            }
        }
        if (root.right != null) {
            int rightMin = minRight(root.right, root.right.val);
            if (root.val >= rightMin) {
                return false;
            }
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    public int minRight(TreeNode root, int min) {
        if (root == null) {
            return min;
        }
        while (root.left != null) {
            min = root.left.val;
            root = root.left;
        }
        return min;
    }

    public int maxLeft(TreeNode root, int max) {
        if (root == null) {
            return max;
        }
        while (root.right != null) {
            max = root.right.val;
            root = root.right;
        }
        return max;
    }
}
