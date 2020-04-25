package backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 *  
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 *  
 *
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutations4String {


    public String[] permutation(String s) {
        List<String> result = new ArrayList<String>();
        Map<Character, Integer> countMap = new HashMap<Character, Integer>();
        char[] selectable = s.toCharArray();
        for (int i = 0; i < selectable.length; i++) {
            if (countMap.containsKey(selectable[i])) {
                countMap.put(selectable[i], countMap.get(selectable[i]) + 1);
            } else {
                countMap.put(selectable[i], 1);
            }
        }
        bt(new ArrayList<Character>(), s.toCharArray(), countMap, result);
        String[] strings = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            strings[i] = result.get(i);
        }
        return strings;
    }

    public void bt(List<Character> selected, char[] selectable, Map<Character, Integer> countMap, List<String> result) {
        if (selected.size() == selectable.length) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < selected.size(); i++) {
                stringBuilder.append(selected.get(i));
            }
            if (!result.contains(stringBuilder.toString())) {
                result.add(stringBuilder.toString());
            }
            return;
        }
        for (int i = 0; i < selectable.length; i++) {
            if (selected.contains(selectable[i])) {
                int count = 0;
                for (int j = 0; j < selected.size(); j++) {
                    if (selected.get(j).equals(selectable[i])) {
                        count++;
                    }
                }
                if (count >= countMap.get(selectable[i])) {
                    continue;
                }
            }
            selected.add(selectable[i]);
            bt(selected, selectable, countMap, result);
            selected.remove(Character.valueOf(selectable[i]));
        }
    }
}
