package recursion;

/**
 * Write a recursive function to multiply two positive integers without using the * operator. You can use addition, subtraction, and bit shifting, but you should minimize the number of those operations.
 *
 * Example 1:
 *
 * Input: A = 1, B = 10
 * Output: 10
 * Example 2:
 *
 * Input: A = 3, B = 4
 * Output: 12
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/recursive-mulitply-lcci
 */
public class RecursiveMulitply {

    public int multiply0(int A, int B) {
        int result = 0;
        String BStr = B + "";
        int[] b = new int[BStr.length()];
        char[] BChar = BStr.toCharArray();
        for (int i = 0; i < BChar.length; i++) {
            b[i] = Integer.valueOf(BChar[BChar.length - i - 1] + "");
        }
        return multiply(result, A, b, 0);
    }

    public int multiply(int result, int A, int[] b, int index) {
        if (index == b.length) {
            return result;
        }
        int tmp = 0;
        int bIndex = b[index];
        for (int i = 0; i < bIndex; i++) {
            tmp += A;
        }
        String tmpStr = tmp + "";
        for (int i = 0; i < index; i++) {
            tmpStr = tmpStr + "0";
        }
        return multiply(result + Integer.valueOf(tmpStr), A, b, ++index);
    }

    public int multiply(int A, int B) {
        if (B == 1) {
            return A;
        }
        if (A == 1) {
            return B;
        }
        return (multiply(A, B >> 1) << 1) + (B % 2 == 0 ? 0 : A);
    }

    public static void main(String[] args) {
        RecursiveMulitply recursiveMulitply = new RecursiveMulitply();
        System.out.println(recursiveMulitply.multiply(3, 4));
    }
}
