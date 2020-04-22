package recursion;

public class DecompressRLElist {

    public int[] decompressRLElist(int[] nums) {
        return recursion(nums, 0, nums.length - 1);
    }

    public int[] recursion(int[] nums, int start, int end) {
        if ((end - start) == 1) {
            int[] result = new int[nums[start]];
            for (int j = 0; j < nums[start]; j++) {
                result[j] = nums[end];
            }
            return result;
        }
        if (start >= end) {
            return new int[0];
        }
        int tmp = start + end + 1;
        int middle = 0;
        if (tmp % 2 == 0) {
            middle = 2 * (tmp / 4) - 1;
        } else {
            middle = tmp / 2;
        }
        int[] left = recursion(nums, start, middle);
        int[] right = recursion(nums, middle + 1, end);

        int[] result = new int[left.length + right.length];
        for (int i = 0; i < left.length; i++) {
            result[i] = left[i];
        }
        for (int i = 0; i < right.length; i++) {
            result[i + left.length] = right[i];
        }
        return result;
    }
}
