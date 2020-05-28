package array;

/**
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/implement-strstr
 */
public class ImplementstrStr {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        char[] haystackChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();
        for (int i = 0; i < haystackChars.length; i++) {
            boolean find = true;
            for (int j = 0, k = i; j < needleChars.length; j++) {
                if (k >= haystackChars.length) {
                    return -1;
                }
                if (haystackChars[k] != needleChars[j]) {
                    find = false;
                    break;
                }
                k++;
            }
            if (find == true) {
                return i;
            }
        }
        return -1;
    }
}
