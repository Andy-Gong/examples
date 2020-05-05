package linkedlist;

import common.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 */
public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tmp = head;
        ListNode next = head.next;
        while (tmp != null && next != null) {
            if (tmp.val == next.val) {
                tmp.next = next.next;
            } else {
                tmp = next;
            }
            next = next.next;
        }
        return head;
    }
}
