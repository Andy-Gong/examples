package bf;

import java.util.ArrayList;
import java.util.List;

/**
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:
 *
 * The number at the ith position is divisible by i.
 * i is divisible by the number at the ith position.
 *  
 *
 * Now given N, how many beautiful arrangements can you construct?
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation:
 *
 * The first beautiful arrangement is [1, 2]:
 *
 * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 *
 * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 *
 * The second beautiful arrangement is [2, 1]:
 *
 * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 *
 * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 */
public class BeautifulArrangement {

    int totalCount = 0;

    public int countArrangement(int N) {
        /**
         * phase 1 has n chooses
         * phase 2 has n-1 chooses
         * phase 3 has n-2 chooses
         * ...
         * phase n has 1 chooses
         * back conditions: if value[i]/i != 0, or i/value[i] != 0
         * return condition: if length == N, return true
         */
        List<Integer> arrayValue = new ArrayList<Integer>();
        for (int i = 1; i <= N; i++) {
            arrayValue.add(i);
        }
        bf(new ArrayList<Integer>(), arrayValue, N);
        return totalCount;
    }

    public void bf(List<Integer> selected, List<Integer> selectable, int N) {
        if (selected.size() == N) {
            totalCount++;
            return;
        }
        if (!selected.isEmpty() && selected.get(selected.size() - 1) % (selected.size() + 1) != 0
            && ((selected.size() + 1) % selected.get(selected.size() - 1) != 0)) {
            return;
        }
        for (int i = 0; i < selectable.size(); i++) {
            if (selected.contains(selectable.get(i))) {
                continue;
            }
            selected.add(selectable.get(i));
            bf(selected, selectable, N);
            selected.remove(selectable.get(i));
        }
    }

    public static void main(String[] args) {
        BeautifulArrangement beautifulArrangement = new BeautifulArrangement();
        System.out.println(beautifulArrangement.countArrangement(2));
    }
}
