package queue;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Design an algorithm to find the kth number such that the only prime factors are 3, 5, and 7. Note that 3, 5, and 7 do not have to be factors, but it should not have any other prime factors. For example, the first several multiples would be (in order) 1, 3, 5, 7, 9, 15, 21.
 *
 * Example 1:
 *
 * Input: k = 5
 *
 * Output: 9
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/get-kth-magic-number-lcci
 */
public class GetKthMagicNumber {

    /**
     * using heap
     */
    public int getKthMagicNumber(int k) {
        Set<Long> values = new HashSet<>();
        Queue<Long> queue = new PriorityQueue();
        queue.add(1L);
        while (true) {
            Long value = queue.poll();
            if (!values.contains(value)) {
                values.add(value);
                queue.add(value * 3);
                queue.add(value * 5);
                queue.add(value * 7);
            }
            if (values.size() == k) {
                return value.intValue();
            }
        }
    }

    /**
     * using 3 pointers.
     * 不难发现，一个丑数总是由前面的某一个丑数 x3 / x5 / x7 得到。
     * 反过来说也是一样的，一个丑数 x3 / x5 / x7 就会得到某一个更大的丑数。
     *
     * 如果把丑数数列叫做 ugly[i]，那么考虑一下三个数列：
     * 1. ugly[0]*3,ugly[1]*3,ugly[2]*3,ugly[3]*3,ugly[4]*3,ugly[5]*3……
     * 2. ugly[0]*5,ugly[1]*5,ugly[2]*5,ugly[3]*5,ugly[4]*5,ugly[5]*5……
     * 3. ugly[0]*7,ugly[1]*7,ugly[2]*7,ugly[3]*7,ugly[4]*7,ugly[5]*7……
     *
     * 上面这个三个数列合在一起就形成了新的、更长的丑数数列。
     *
     * 如果合在一起呢？这其实就是一个合并有序线性表的问题。
     *
     * 定义三个index 分别指向上面三个数列，下一个丑数一定是三个 index 代表的值中最小的那个。然后相应 index++ 即可。
     *
     * 举个例子
     * 初始值 ugly[0]=1; index1=0; index2=0; index3=0
     *
     * ugly[1]=Min(ugly[index1]*3,ugly[index2]*5,ugly[index3]*7)
     * =Min(1*3,1*5,1*7)
     * =3
     * 于是 index1++;
     *
     * ugly[2]=Min(ugly[index1]*3,ugly[index2]*5,ugly[index3]*7)
     * =Min(3*3,1*5,1*7)
     * =5
     * 于是 index2++;
     * 以此类推
     */
    public int getKthMagicNumber1(int k) {
        int[] dp = new int[k];
        dp[0] = 1;
        int p3 = 0, p5 = 0, p7 = 0;
        int count = 0;
        while (true) {
            int value = Math.min(Math.min(dp[p3] * 3, dp[p5] * 5), dp[p7] * 7);
            if (value == dp[p3] * 3) {
                p3++;
            }
            if (value == dp[p5] * 5) {
                p5++;
            }
            if (value == dp[p7] * 7) {
                p7++;
            }
            count++;
            if (count == k) {
                return value;
            }
            dp[count] = value;
        }
    }

    public static void main(String[] args) {
        GetKthMagicNumber getKthMagicNumber = new GetKthMagicNumber();
        System.out.println(getKthMagicNumber.getKthMagicNumber(643));
    }
}
