package twopointers;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s.length()<=1){
            return s;
        }
        char[] chars = s.toCharArray();
        int[] indexes = new int[]{0, 0};
        for (int i = 0; i < chars.length - 1; i++) {
            //i is middle
            for (int j = i + 1, k = i - 1; ; ) {
                if (j < chars.length && k >= 0 && chars[j] == chars[k]) {
                    j++;
                    k--;
                    continue;
                }
                if ((j - k - 2) > (indexes[1] - indexes[0])) {
                    indexes[0] = k + 1;
                    indexes[1] = j - 1;
                }
                break;
            }
            //i is not middle
            for (int j = i + 1, k = i; ; ) {
                if (j < chars.length && k >= 0 && chars[j] == chars[k]) {
                    j++;
                    k--;
                    continue;
                }
                if ((j - k - 2) > (indexes[1] - indexes[0])) {
                    indexes[0] = k + 1;
                    indexes[1] = j - 1;
                }
                break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = indexes[0]; i <= indexes[1]; i++) {
            stringBuilder.append(chars[i]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String input = "bb";
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        System.out.println(longestPalindromicSubstring.longestPalindrome(input));
    }
}
