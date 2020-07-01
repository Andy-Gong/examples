package linkedlist;


import common.ListNode;

/**
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        return recursion(head, null);
    }

    private ListNode recursion(ListNode current, ListNode prev) {
        if (current == null) {
            return prev;
        }
        ListNode tmp = current.next;
        current.next = prev;
        return recursion(tmp, current);
    }
}
