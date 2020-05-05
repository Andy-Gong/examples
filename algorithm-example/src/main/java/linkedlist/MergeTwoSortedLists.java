package linkedlist;

import common.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/merge-two-sorted-lists
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode resultNext = null;
        ListNode resultHead = null;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                if (resultHead == null) {
                    resultHead = new ListNode(l2.val);
                    resultNext = resultHead;
                } else {
                    ListNode tmp = new ListNode(l2.val);
                    resultNext.next = tmp;
                    resultNext = tmp;
                }
                l2 = l2.next;
            } else if (l1.val < l2.val) {
                if (resultHead == null) {
                    resultHead = new ListNode(l1.val);
                    resultNext = resultHead;
                } else {
                    ListNode tmp = new ListNode(l1.val);
                    resultNext.next = tmp;
                    resultNext = tmp;
                }
                l1 = l1.next;
            } else {
                if (resultHead == null) {
                    resultHead = new ListNode(l1.val);
                    ListNode tmp = new ListNode(l2.val);
                    resultHead.next = tmp;
                    resultNext = tmp;
                } else {
                    ListNode tmp1 = new ListNode(l1.val);
                    ListNode tmp2 = new ListNode(l2.val);
                    resultNext.next = tmp1;
                    resultNext.next.next = tmp2;
                    resultNext = tmp2;
                }
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        while (l1 != null) {
            if (resultHead == null) {
                resultHead = new ListNode(l1.val);
                resultNext = resultHead;
            } else {
                ListNode tmp = new ListNode(l1.val);
                resultNext.next = tmp;
                resultNext = tmp;
            }
            l1 = l1.next;
        }
        while (l2 != null) {
            if (resultHead == null) {
                resultHead = new ListNode(l2.val);
                resultNext = resultHead;
            } else {
                ListNode tmp = new ListNode(l2.val);
                resultNext.next = tmp;
                resultNext = tmp;
            }
            l2 = l2.next;
        }
        return resultHead;
    }
}
