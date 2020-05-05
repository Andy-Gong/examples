package linkedlist;

import java.util.HashSet;
import java.util.Set;

import common.ListNode;

/**
 * Write code to remove duplicates from an unsorted linked list.
 *
 * Example1:
 *
 *  Input: [1, 2, 3, 3, 2, 1]
 *  Output: [1, 2, 3]
 * Example2:
 *
 *  Input: [1, 1, 1, 1, 2]
 *  Output: [1, 2]
 * Note:
 *
 * The length of the list is within the range[0, 20000].
 * The values of the list elements are within the range [0, 20000].
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/remove-duplicate-node-lcci
 */
public class RemoveDuplicateNode {
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode tmp = head;
        ListNode tmpPre = null;
        Set<Integer> existedValues = new HashSet<>();
        while (tmp != null) {
            if (existedValues.contains(tmp.val)) {
                tmpPre.next = tmp.next;
                tmp = tmp.next;
            } else {
                existedValues.add(tmp.val);
                tmpPre = tmp;
                tmp = tmp.next;
            }
        }
        return head;
    }
}
