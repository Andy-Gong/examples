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

    public ListNode mergeTwoListsNew(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
        } else {
            head = l2;
        }
        recursion(l1, l2, null);
        return head;
    }

    void recursion(ListNode l1, ListNode l2, ListNode l1Pre) {
        if (l1 == null) {
            l1Pre.next = l2;
            return;
        }
        if (l2 == null) {
            return;
        }
        if (l1.val <= l2.val) {
            recursion(l1.next, l2, l1);
        } else {
            ListNode newL2 = l2.next;
            if (l1Pre != null) {
                l1Pre.next = l2;
            }
            l2.next = l1;
            recursion(l1, newL2, l2);
        }
    }
}
