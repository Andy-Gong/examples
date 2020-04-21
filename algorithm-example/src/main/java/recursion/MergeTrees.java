package recursion;

public class MergeTrees {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        return merge(t1, t2);
    }

    public TreeNode merge(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 == null && t2 != null) {
            return t2;
        }
        if (t1 != null && t2 == null) {
            return t1;
        }
        TreeNode left = merge(t1.left, t2.left);
        TreeNode right = merge(t1.right, t2.right);
        TreeNode newNode = new TreeNode(t1.val + t2.val);
        newNode.left = left;
        newNode.right = right;
        return newNode;
    }
}
