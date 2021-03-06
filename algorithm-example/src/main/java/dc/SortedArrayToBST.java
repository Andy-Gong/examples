package dc;

/**
 * sorted array build binary search tree
 */
public class SortedArrayToBST {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //[-10,-3,0,5,9]
    public TreeNode sortedArrayToBST(int[] nums) {
        return dc(nums, 0, nums.length - 1);
    }

    public TreeNode dc(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        System.out.println("start: " + start + ", end: " + end);
        int middle = start + (end - start) / 2;
        TreeNode leftTree = dc(nums, start, middle - 1);
        TreeNode rightTree = dc(nums, middle + 1, end);
        TreeNode treeNode = new TreeNode(nums[middle]);
        treeNode.left = leftTree;
        treeNode.right = rightTree;
        return treeNode;
    }

    public static void main(String[] args) {
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        sortedArrayToBST.sortedArrayToBST(nums);
    }
}
