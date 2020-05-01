package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * We have a collection of stones, each stone has a positive integer weight.
 *
 * Each turn, we choose the two heaviest stones and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 *
 *  
 *
 * Example 1:
 *
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/last-stone-weight
 */
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        int max = stones[0];
        for (int i = 1; i < stones.length; i++) {
            if (stones[i] > max) {
                max = stones[i];
            }
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < stones.length; i++) {
            bucket[stones[i]] += 1;
        }
        List<Integer> compareStones = new ArrayList<Integer>();
        for (int i = max; i > 0; i--) {
            while (bucket[i] > 0) {
                compareStones.add(i);
                if (compareStones.size() == 2) {
                    int result = combine(compareStones.get(0), compareStones.get(1));
                    if (result != 0) {
                        bucket[result] += 1;
                    }
                    bucket[compareStones.get(1)] -= 1;
                    compareStones.clear();
                    if (result > i) {
                        i = result;
                    }
                } else {
                    bucket[i] -= 1;
                }
            }
        }

        return compareStones.isEmpty() ? 0 : compareStones.get(0);
    }

    public int combine(int fist, int second) {
        if (fist == second) {
            return 0;
        } else {
            return fist - second;
        }
    }

    public static void main(String[] args) {
        int[] inputs = new int[]{2, 7, 4, 1, 8, 1};
        LastStoneWeight lastStoneWeight = new LastStoneWeight();
        System.out.println(lastStoneWeight.lastStoneWeight(inputs));
    }
}
