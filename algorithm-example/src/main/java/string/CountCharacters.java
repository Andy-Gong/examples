package string;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入：words = ["cat","recursion","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 */
public class CountCharacters {

    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> charsMap = new HashMap();
        char[] charsArray = chars.toCharArray();
        for (int i = 0; i < chars.length(); i++) {
            if (charsMap.containsKey(charsArray[i])) {
                charsMap.put(charsArray[i], charsMap.get(charsArray[i]) + 1);
            } else {
                charsMap.put(charsArray[i], 1);

            }
        }
        int totalLength = 0;
        for (int i = 0; i < words.length; i++) {
            Map<Character,Integer> tmpMap = (Map<Character, Integer>) ((HashMap<Character, Integer>) charsMap).clone();
            char[] charsTmp = words[i].toCharArray();
            boolean include = true;
            for (int j = 0; j < charsTmp.length; j++) {
                if (tmpMap.containsKey(charsTmp[j])) {
                    Integer value = tmpMap.get(charsTmp[j]);
                    if (value == 0) {
                        include = false;
                        break;
                    }else{
                        tmpMap.put(charsTmp[j], value - 1);
                    }
                } else {
                    include = false;
                    break;
                }
            }
            if (include) {
                totalLength += charsTmp.length;
            }

        }
        return totalLength;
    }
}
