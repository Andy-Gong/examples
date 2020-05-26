package recursion;

import common.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 *  
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 */
public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        swap(null, head);
        return newHead;
    }

    public void swap(ListNode pre, ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode nextNext = head.next.next;
        ListNode next = head.next;
        if (pre != null) {
            pre.next = next;
        }
        next.next = head;
        head.next = nextNext;
        pre = head;
        swap(pre, nextNext);
    }
}
