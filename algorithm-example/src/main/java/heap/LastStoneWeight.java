package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

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
        PriorityQueue<Integer> priorityQueue = new PriorityQueue((Comparator<Integer>) (o1, o2) -> o2 - o1);
        for (int i = 0; i < stones.length; i++) {
            priorityQueue.add(stones[i]);
        }
        while (priorityQueue.size() >= 2) {
            int first = priorityQueue.poll();
            int second = priorityQueue.poll();
            if (first != second) {
                priorityQueue.add(first - second);
            }
        }
        if (priorityQueue.isEmpty()) {
            return 0;
        } else {
            return priorityQueue.poll().intValue();
        }
    }


    public static void main(String[] args) {
        int[] inputs = new int[]{2, 7, 4, 1, 8, 1};
        LastStoneWeight lastStoneWeight = new LastStoneWeight();
        System.out.println(lastStoneWeight.lastStoneWeight(inputs));
    }
}
