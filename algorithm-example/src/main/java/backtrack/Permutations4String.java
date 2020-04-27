package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
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
        Map<String, Integer> result = new HashMap<String, Integer>();
        Map<Character, Integer> countMap = new HashMap<Character, Integer>();
        char[] selectable = s.toCharArray();
        for (int i = 0; i < selectable.length; i++) {
            if (countMap.containsKey(selectable[i])) {
                countMap.put(selectable[i], countMap.get(selectable[i]) + 1);
            } else {
                countMap.put(selectable[i], 1);
            }
        }
        Arrays.sort(selectable);
        bt(new ArrayList<Character>(), selectable, countMap, result);
        String[] strings = new String[result.size()];
        int i = 0;
        for (String each : result.keySet()) {
            strings[i] = each;
            i++;
        }
        return strings;
    }

    public void bt(List<Character> selected, char[] selectable, Map<Character, Integer> countMap,
        Map<String, Integer> result) {
        if (selected.size() == selectable.length) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < selected.size(); i++) {
                stringBuilder.append(selected.get(i));
            }
            if (!result.containsKey(stringBuilder.toString())) {
                result.put(stringBuilder.toString(), 1);
            }
            return;
        }
        for (int i = 0; i < selectable.length; i++) {
            Character valueOfI = selectable[i];
            if (selected.contains(valueOfI)) {
                if (countMap.get(valueOfI) == 1) {
                    continue;
                }
                int count = 0;
                for (int j = 0; j < selected.size(); j++) {
                    if (selected.get(j).equals(valueOfI)) {
                        count++;
                    }
                }
                if (count >= countMap.get(valueOfI)) {
                    continue;
                }
            }
            selected.add(valueOfI);
            bt(selected, selectable, countMap, result);
            selected.remove(selected.size() - 1);
        }
    }

    public static void main(String[] args) {
        String input = "vpvptjzh";
        Permutations4String permutations4String = new Permutations4String();
        String[] strings = permutations4String.permutation(input);
        System.out.println(strings.length);
                System.out.println(Arrays.asList(strings));
        //        String[] expected = new String[]{"fkxzxx", "fkxxzx", "fkxxxz", "fkzxxx", "fxkxzx", "fxkxxz", "fxkzxx", "fxzkxx",
        //            "fxzxkx", "fxzxxk", "fxxkxz", "fxxkzx", "fxxzkx", "fxxzxk", "fxxxkz", "fxxxzk", "fzkxxx", "fzxkxx",
        //            "fzxxkx", "fzxxxk", "kfxzxx", "kfxxzx", "kfxxxz", "kfzxxx", "kxfxzx", "kxfxxz", "kxfzxx", "kxzfxx",
        //            "kxzxfx", "kxzxxf", "kxxfxz", "kxxfzx", "kxxzfx", "kxxzxf", "kxxxfz", "kxxxzf", "kzfxxx", "kzxfxx",
        //            "kzxxfx", "kzxxxf", "xfkxzx", "xfkxxz", "xfkzxx", "xfxkxz", "xfxkzx", "xfxzkx", "xfxzxk", "xfxxkz",
        //            "xfxxzk", "xfzkxx", "xfzxkx", "xfzxxk", "xkfxzx", "xkfxxz", "xkfzxx", "xkxfxz", "xkxfzx", "xkxzfx",
        //            "xkxzxf", "xkxxfz", "xkxxzf", "xkzfxx", "xkzxfx", "xkzxxf", "xzfkxx", "xzfxkx", "xzfxxk", "xzkfxx",
        //            "xzkxfx", "xzkxxf", "xzxfkx", "xzxfxk", "xzxkfx", "xzxkxf", "xzxxfk", "xzxxkf", "xxfkxz", "xxfkzx",
        //            "xxfxkz", "xxfxzk", "xxfzkx", "xxfzxk", "xxkfxz", "xxkfzx", "xxkxfz", "xxkxzf", "xxkzfx", "xxkzxf",
        //            "xxzfkx", "xxzfxk", "xxzkfx", "xxzkxf", "xxzxfk", "xxzxkf", "xxxfkz", "xxxfzk", "xxxkfz", "xxxkzf",
        //            "xxxzfk", "xxxzkf", "zfkxxx", "zfxkxx", "zfxxkx", "zfxxxk", "zkfxxx", "zkxfxx", "zkxxfx", "zkxxxf",
        //            "zxfkxx", "zxfxkx", "zxfxxk", "zxkfxx", "zxkxfx", "zxkxxf", "zxxfkx", "zxxfxk", "zxxkfx", "zxxkxf",
        //            "zxxxfk", "zxxxkf"};
        //        String[] result = new String[]{"kzfxxx", "kzxfxx", "kzxxfx", "kzxxxf", "kfzxxx", "kfxzxx", "kfxxzx", "kfxxxz",
        //            "kxzfxx", "kxfzxx", "kxxzfx", "kxxfzx", "kxxxzf", "kxxxfz", "zkfxxx", "zkxfxx", "zkxxfx", "zkxxxf",
        //            "zfkxxx", "zfxkxx", "zfxxkx", "zfxxxk", "zxkfxx", "zxfkxx", "zxxkfx", "zxxfkx", "zxxxkf", "zxxxfk",
        //            "fkzxxx", "fkxzxx", "fkxxzx", "fkxxxz", "fzkxxx", "fzxkxx", "fzxxkx", "fzxxxk", "fxkzxx", "fxzkxx",
        //            "fxxkzx", "fxxzkx", "fxxxkz", "fxxxzk", "xkzfxx", "xzkfxx", "xfkzxx", "xxkzfx", "xxzkfx", "xxfkzx",
        //            "xxxkzf", "xxxkfz", "xxxzkf", "xxxzfk", "xxxfkz", "xxxfzk"};
        //
        //        Arrays.sort(expected);
        //        Arrays.sort(result);
        //        System.out.println(Arrays.asList(expected));
        //        System.out.println(Arrays.asList(result));
    }


}
