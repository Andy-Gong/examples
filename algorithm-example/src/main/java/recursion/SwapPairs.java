package recursion;

import common.ListNode;

public class SwapPairs {

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
        swap(head, nextNext);
    }
}
