package recursion;

import common.ListNode;

/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/reverse-linked-list
 */
public class ReverseLinkList {

    public ListNode reverseList(ListNode head) {
        return recursion(null, head);
    }

    public ListNode recursion(ListNode pre, ListNode head) {
        if (head == null) {
            return pre;
        }
        ListNode next = head.next;
        head.next = pre;
        return recursion(head, next);
    }

    public ListNode reverseListNew(ListNode head) {
        ListNode tmp = head;
        ListNode pre = null;
        while (tmp != null) {
            ListNode next = tmp.next;
            tmp.next = pre;
            pre = tmp;
            tmp = next;
        }
        return pre;
    }
}
