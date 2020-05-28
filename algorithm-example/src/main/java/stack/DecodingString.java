package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there won't be input like 3a or 2[4].
 *
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/decode-string
 */
public class DecodingString {

    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ']') {
                List<Character> characters = new ArrayList<>();
                while (stack.peek() != '[') {
                    characters.add(stack.pop());
                }
                stack.pop();//pop '['
                StringBuilder countSB = new StringBuilder();
                while (!stack.isEmpty() && isNumber(stack.peek())) {
                    countSB.append(stack.pop());
                }
                Integer count = Integer.valueOf(countSB.reverse().toString());//pop count
                for (int j = 0; j < count; j++) {
                    for (int k = characters.size() - 1; k >= 0; k--) {
                        stack.add(characters.get(k));
                    }
                }
            } else {
                stack.add(chars[i]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }

    List<Character> intCollection = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    boolean isNumber(char c) {
        return intCollection.contains(c);
    }
}
