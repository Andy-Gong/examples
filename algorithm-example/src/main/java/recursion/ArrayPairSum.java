package recursion;

import java.util.Arrays;

public class ArrayPairSum {

    public int arrayPairSum(int[] nums) {
//        int sum=0;
//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length; i+=2) {
//            sum+=nums[i];
//        }
        Arrays.sort(nums);
        return recursion(nums, 0, nums.length-1);
    }

    public int recursion(int[] nums, int left, int right) {
        if (right - left == 1) {
            return nums[left];
        }
        int middle = left + 2*((right - left + 1) / 4);
        int leftSum = recursion(nums, left, middle - 1);
        int rightSum = recursion(nums, middle, right);
        return leftSum + rightSum;
    }
}
