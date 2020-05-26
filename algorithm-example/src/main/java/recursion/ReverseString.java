package recursion;

/**
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 *  
 *
 * Example 1:
 *
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/reverse-string
 */
public class ReverseString {

    public void reverseString(char[] s) {
        recursion(s, 0);
    }

    public void recursion(char[] s, int index) {
        if (s.length % 2 == 0 && index > (s.length / 2 - 1)) {
            return;
        }
        if (s.length % 2 == 1 && index > ((s.length - 1) / 2 - 1)) {
            return;
        }
        char charIndex = s[index];
        s[index] = s[s.length - 1 - index];
        s[s.length - 1 - index] = charIndex;
        recursion(s, index + 1);
    }
}
