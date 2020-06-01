package twopointers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/longest-common-prefix
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        List<char[]> charList = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() == 0) {
                return "";
            }
            charList.add(strs[i].toCharArray());
        }
        int index = 0;
        while (true) {
            Set<Character> values = new HashSet<>();
            boolean end=false;
            for (int i = 0; i < charList.size(); i++) {
                if (index == charList.get(i).length) {
                    end=true;
                    break;
                }
                values.add(charList.get(i)[index]);
            }
            if (values.size() > 1 || end) {
                break;
            }
            index++;
        }
        if (index == 0) {
            return "";
        }
        return strs[0].substring(0, index);
    }
}
