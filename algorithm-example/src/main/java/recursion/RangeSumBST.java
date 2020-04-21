package recursion;

public class RangeSumBST {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int sum;

    public int rangeSumBST(TreeNode root, int L, int R) {
        sum = 0;
        recursion(root, L, R);
        return sum;
    }

    void recursion(TreeNode root, int L, int R) {
        if (root == null) {
            return;
        }
        if (root.val >= L && root.val <= R) {
            sum += root.val;
        }
        recursion(root.left, L, R);
        recursion(root.right, L, R);
    }


}
