package linkedlist;

import common.ListNode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Note: Do not modify the linked list.
 *
 *  
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/linked-list-cycle-ii
 */
public class LinkedListCycleII {

    //    public ListNode detectCycle(ListNode head) {
    //        if (head == null || head.next == null) {
    //            return null;
    //        }
    //        ListNode slow = head;
    //        ListNode fast = head;
    //        while (true) {
    //            if (slow == null || fast == null || fast.next == null) {
    //                return null;
    //            }
    //            slow = slow.next;
    //            fast = fast.next.next;
    //            if (slow == fast) {
    //                break;
    //            }
    //        }
    //        /**
    //         * tortoise is slow pointer, hare is fast pointer
    //         * 2⋅distance(tortoise)=distance(hare)
    //         * 2(F+a)=F+a+b+a
    //         * 2F+2a=F+2a+b
    //         * F=b
    //         */
    //        while (true) {
    //            if (head == fast) {
    //                break;
    //            }
    //            head = head.next;
    //            fast = fast.next;
    //        }
    //        return fast;
    //    }
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast == null || slow == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        while (fast != head) {
            fast = fast.next;
            head = head.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        /**
         * [3,2,0,-4]
         */
        ListNode listNode1 = new ListNode(3);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(0);
        listNode1.next.next.next = new ListNode(-4);
        listNode1.next.next.next.next = listNode1.next;

        LinkedListCycleII linkedListCycleII = new LinkedListCycleII();
        linkedListCycleII.detectCycle(listNode1);
    }
}
