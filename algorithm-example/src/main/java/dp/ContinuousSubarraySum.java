package dp;

import java.util.HashMap;

/**
 * 给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[23,2,4,6,7], k = 6
 * 输出：True
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。
 * 示例 2：
 *
 * 输入：[23,2,6,4,7], k = 6
 * 输出：True
 * 解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContinuousSubarraySum {

    /**
     * 使用dp，复杂度为O(n*n)
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            int sum = prefixSum[i];
            for (int j = 0; j < i; j++) {
                if ((k == 0 && sum == 0) || (k != 0 && sum % k == 0)) {
                    return true;
                }
                sum -= nums[j];
            }
        }
        return false;
    }

    /**
     * 使用HashMap算法，HashMap存储前缀和%k，key: sum[i]%k，value：index，如果存在相同的key，且value之差大于1，则返回true
     * 时间复杂度O(n)
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        //key=0覆盖所有余数为0的case
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }
}
