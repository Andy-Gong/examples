package twopointers;

/**
 * Implement an algorithm to find the kth to last element of a singly linked list. Return the node.
 *
 * Note: This problem is slightly different from the original one in the book.
 *
 * Example:
 *
 * Input:  1->2->3->4->5 , k = 2
 * Output:  4->5
 *
 */
public class KthNodeFromEndOfList {

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
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode kthNode = head;
        while (k > 1) {
            kthNode = kthNode.next;
            k--;
        }
        while (kthNode.next != null) {
            kthNode = kthNode.next;
            head = head.next;
        }
        return head;
    }
}
