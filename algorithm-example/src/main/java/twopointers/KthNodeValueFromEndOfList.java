package twopointers;

/**
 * Implement an algorithm to find the kth to last element of a singly linked list. Return the value of the element.
 *
 * Note: This problem is slightly different from the original one in the book.
 *
 * Example:
 *
 * Input:  1->2->3->4->5 和 k = 2
 * Output:  4
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci
 */
public class KthNodeValueFromEndOfList {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * using two pointers
     */
    public int kthToLast(ListNode head, int k) {
        ListNode kthNode = head;
        while (k > 1) {
            kthNode = kthNode.next;
            k--;
        }
        while (kthNode.next != null) {
            kthNode = kthNode.next;
            head = head.next;
        }
        return head.val;
    }

    /**
     * using recursion
     */
    public int kthToLast1(ListNode head, int k) {
        ListNode newHead = reverse(head, head.next);
        ListNode kthNode = newHead;
        while (k > 1) {
            kthNode = kthNode.next;
            k--;
        }
        return kthNode.val;
    }

    public ListNode reverse(ListNode head, ListNode next) {
        if (next == null) {
            return head;
        }
        ListNode nextNode = next.next;
        next.next = head;
        return reverse(next, nextNode);
    }
}
