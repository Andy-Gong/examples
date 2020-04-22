package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinSubsequence {

    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        double half = sum / 2.0;
        List<Integer> result = new ArrayList<Integer>();
        for (int i = nums.length - 1; i >= 0; i--) {
            result.add(nums[i]);
            half -= nums[i];
            if (half < 0) {
                break;
            }
        }
        return result;
    }
}
