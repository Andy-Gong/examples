package recursion;

/**
 * Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.
 *
 * Example 1:
 *
 * Input: a = 2, b = [3]
 * Output: 8
 * Example 2:
 *
 * Input: a = 2, b = [1,0]
 * Output: 1024
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/super-pow
 */
public class SuperPow {

    /**
     * =
     * =
     * ​
     *
     * a
     * [1,5,6,4]
     *
     * a
     * 4
     *  ×a
     * [1,5,6,0]
     *
     * a
     * 4
     *  ×(a
     * [1,5,6]
     *  )
     * 10
     *
     * ​
     * @param a
     * @param b
     * @return
     */
    public int superPow(int a, int[] b) {
        return (int)recursion(a, b, b.length-1) % 1337;
    }

    public long recursion(int a, int[] b, int index) {
        if (index < 0) {
            return 1;
        }
        long value = (long) Math.pow(a, b[index]);
        return (long) (value * Math.pow(recursion(a, b, index - 1), 10)) % 1337;
    }

    public static void main(String[] args){
        SuperPow superPow = new SuperPow();
        System.out.println(superPow.superPow(2147483647,new int[]{2,0,0}));
    }
}
