package array;

/**
 * Given two binary strings, return their sum (also a binary string).
 *
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *  
 *
 * Constraints:
 *
 * Each string consists only of '0' or '1' characters.
 * 1 <= a.length, b.length <= 10^4
 * Each string is either "0" or doesn't contain any leading zero.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/add-binary
 */
public class BinarySum {

    public String addBinary(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        sum(reverse(a.toCharArray()), reverse(b.toCharArray()), 0, 0, stringBuilder);
        return stringBuilder.reverse().toString();
    }

    void sum(char[] a, char[] b, int index, int forward, StringBuilder result) {
        if (index >= Math.max(a.length, b.length)) {
            if (forward == 1) {
                result.append(1);
            }
            return;
        }
        int valueA = 0;
        int valueB = 0;
        if (a.length > index) {
            valueA = Integer.valueOf(String.valueOf(a[index]));
        }
        if (b.length > index) {
            valueB = Integer.valueOf(String.valueOf(b[index]));
        }
        int value = valueA + valueB + forward;
        forward = value / 2;
        result.append(value % 2);
        sum(a, b, index + 1, forward, result);
    }

    char[] reverse(char[] chars) {
        for (int i = 0; i < chars.length / 2; i++) {
            char tmp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = tmp;
        }
        return chars;
    }

    public static void main(String[] args) {
        //"1010"
        //"1011"
        BinarySum binarySum = new BinarySum();
        binarySum.addBinary("1010", "1011");
    }
}
