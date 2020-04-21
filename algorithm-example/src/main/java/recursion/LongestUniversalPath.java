package recursion;

public class LongestUniversalPath {
    private int res;

    public int longestUniversalPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //divide into 2 sub-problems, left longest and right longest
        int left = longestUniversalPath(root.left);
        int right = longestUniversalPath(root.right);
        int leftLongest = 0;
        int rightLongest = 0;
        if (root.left != null && root.val == root.left.val) {
            leftLongest = left + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            rightLongest = right + 1;
        }
        res = Math.max(res, leftLongest + rightLongest);
        return Math.max(leftLongest, rightLongest);
    }

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
