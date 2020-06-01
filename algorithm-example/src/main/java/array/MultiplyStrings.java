package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/multiply-strings
 */
public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        List<List<Integer>> result = new ArrayList<>();
        char[] num1Chars = num1.toCharArray();
        char[] num2Chars = num2.toCharArray();
        for (int i = num1Chars.length - 1; i >= 0; i--) {
            List<Integer> values = new ArrayList<>();
            for (int j = 0; j < num1Chars.length - 1 - i; j++) {
                values.add(0);
            }
            int carry = 0;
            for (int j = num2Chars.length - 1; j >= 0; j--) {
                int value =
                    Integer.valueOf(String.valueOf(num1Chars[i])) * Integer.valueOf(String.valueOf(num2Chars[j]))
                        + carry;
                values.add(value % 10);
                carry = value / 10;
            }
            if (carry > 0) {
                values.add(carry);
            }
            result.add(values);
        }
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        int carry = 0;
        while (true) {
            int sum = carry;
            List<List<Integer>> removeLis = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                if (index == result.get(i).size()) {
                    removeLis.add(result.get(i));
                    continue;
                }
                sum += result.get(i).get(index);
            }
            for (List remove : removeLis) {
                result.remove(remove);
            }
            if (result.isEmpty()) {
                break;
            }
            stringBuilder.append(sum % 10);
            carry = sum / 10;
            index++;
        }
        if (carry > 0) {
            stringBuilder.append(carry);
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        MultiplyStrings multiplyStrings = new MultiplyStrings();
        System.out.println(multiplyStrings.multiply("123", "456"));
    }
}
