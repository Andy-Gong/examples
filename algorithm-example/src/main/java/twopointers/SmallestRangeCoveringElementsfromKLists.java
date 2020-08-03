package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 * 示例 1:
 *
 * 输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出: [20,24]
 * 解释:
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SmallestRangeCoveringElementsfromKLists {

    /**
     * 思路：起点最小的区间我们是可以知道的。就是从k个数组中找最小的元素和最大元素组成的区间，我们称其为起始区间。如果之后没有长度比起始区间长度更短的，那么起始区间
     * 就是我们所求的最小区间。
     * 然后，每次都将当前区间中最小的元素丢弃，换成其原始数组中的下一个元素。就是说，如果当前我们从每个区间中选取的元素分别是a1_1,a2_1,a3_1
     * ...ak_1(前面的数字代表来自第几个数组，后面的数字表示该数是该数组的第几个元素),若此时最小的元素是ai_1,最大元素是aj_1,那么区间就为[ai_1, aj_1],区间中最小
     * 元素是ai_1,那么我们就将ai_1丢弃,将ai_2拿出来放进去。基于现有元素计算起始区间，如果区间小于现有的最小区间，则赋值该区间为最小区间，否则重复前面步骤。
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        int maxValue = Integer.MIN_VALUE;
        int start = 0;
        int end = Integer.MAX_VALUE;
        PriorityQueue<Entry> priorityQueue = new PriorityQueue(Comparator.comparingInt(Entry::getValue));
        for (int i = 0; i < nums.size(); i++) {
            priorityQueue.add(new Entry(i, 0, nums.get(i).get(0)));
            maxValue = Math.max(maxValue, nums.get(i).get(0));
        }
        while (true) {
            Entry min = priorityQueue.poll();
            if (end - start > maxValue - min.value) {
                start = min.value;
                end = maxValue;
            }
            int index = min.index;
            if (index + 1 >= nums.get(min.ith).size()) {
                break;
            }
            priorityQueue.add(new Entry(min.ith, index + 1, nums.get(min.ith).get(index + 1)));
            maxValue = Math.max(maxValue,nums.get(min.ith).get(index + 1));
        }
        return new int[]{start, end};
    }

    public static class Entry {

        int ith;
        int index;
        int value;

        public Entry(int ith, int index, int value) {
            this.ith = ith;
            this.index = index;
            this.value = value;
        }

        public int getIth() {
            return ith;
        }

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args){
        //[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(4,10,15,24,26));
        lists.add(Arrays.asList(0,9,12,20));
        lists.add(Arrays.asList(5,18,22,30));
        SmallestRangeCoveringElementsfromKLists kLists = new SmallestRangeCoveringElementsfromKLists();
        int[] result = kLists.smallestRange(lists);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
