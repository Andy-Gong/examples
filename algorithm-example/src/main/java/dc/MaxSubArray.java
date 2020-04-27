package dc;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        return dc(nums, 0, nums.length - 1);
    }

    public int dc(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int middle = (left + right) / 2;
        int leftMax = dc(nums, left, middle);
        int rightMax = dc(nums, middle + 1, right);
        int max = Math.max(leftMax, rightMax);

        int middleMaxLeft = nums[middle];
        int middleMaxRight = nums[middle + 1];
        int tmpLeft = nums[middle];
        int tmpRight = nums[middle + 1];
        for (int i = middle - 1; i >= left; i--) {
            tmpLeft += nums[i];
            if (tmpLeft > middleMaxLeft) {
                middleMaxLeft = tmpLeft;
            }
        }
        for (int i = middle + 2; i <= right; i++) {
            tmpRight += nums[i];
            if (tmpRight > middleMaxRight) {
                middleMaxRight = tmpRight;
            }
        }
        if (middleMaxLeft > 0 && middleMaxRight > 0) {
            return Math.max(max, middleMaxLeft + middleMaxRight);
        } else if (middleMaxLeft >= 0 && middleMaxRight < 0) {
            return Math.max(max, middleMaxLeft);
        } else if (middleMaxLeft < 0 && middleMaxRight >= 0) {
            return Math.max(max, middleMaxRight);
        } else {
            return Math.max(max, Math.max(middleMaxLeft, middleMaxRight));
        }
    }
}
