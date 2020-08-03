package string;

import org.junit.jupiter.api.Test;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {
        int addOne = 0;
        int i = num1.length() - 1, j = num2.length() - 1;
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int iValue = (i >= 0 ? num1.charAt(i) - '0' : 0) + (j >= 0 ? num2.charAt(j) - '0' : 0) + addOne;
            if (iValue >= 10) {
                iValue = iValue % 10;
                addOne = 1;
            } else {
                addOne = 0;
            }
            stringBuilder.append((char) (iValue + '0'));
            i--;
            j--;
            if (i < 0 && j < 0) {
                break;
            }
        }
        if (addOne == 1) {
            stringBuilder.append('1');
        }
        return stringBuilder.reverse().toString();
    }

    @Test
    public void test() {
        System.out.println(this.addStrings("0", "0"));
    }
}
