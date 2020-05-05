package linkedlist;

import java.util.ArrayList;
import java.util.List;

import common.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/sort-list
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode result = new ListNode(0);
        ListNode mergeTmp = result;
        while (left != null && right != null) {
            if (left.val < right.val) {
                mergeTmp.next = left;
                mergeTmp = mergeTmp.next;
                left = left.next;
            } else if (left.val > right.val) {
                mergeTmp.next = right;
                mergeTmp = mergeTmp.next;
                right = right.next;
            } else {
                mergeTmp.next = right;
                mergeTmp.next.next=left;
                mergeTmp = mergeTmp.next.next;
                right = right.next;
                left = left.next;
            }
        }
        if (left != null) {
            mergeTmp.next = left;
        }
        if (right != null) {
            mergeTmp.next = right;
        }
        return result.next;
    }


    public ListNode mergedSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = mergedSort(head);
        ListNode right = mergedSort(tmp);
        ListNode result = new ListNode(0);
        ListNode mergeTmp = result;
        while (left != null && right != null) {
            if (left.val > right.val) {
                mergeTmp.next = left;
                mergeTmp = mergeTmp.next;
                left = left.next;
            } else if (left.val < right.val) {
                mergeTmp.next = right;
                mergeTmp = mergeTmp.next;
                right = right.next;
            }
        }
        while (left != null) {
            mergeTmp.next = left;
            left = left.next;
        }
        while (right != null) {
            mergeTmp.next = right;
            right = right.next;
        }
        return result.next;
    }
    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<ListNode> nodeList = new ArrayList<>();
        while (head != null) {
            ListNode tmpNext = head.next;
            head.next = null;
            nodeList.add(head);
            head = tmpNext;

        }
        nodeList.sort((o1, o2) -> o1.val - o2.val);
        ListNode result = nodeList.get(0);
        ListNode tmp = result;
        for (int i = 1; i < nodeList.size(); i++) {
            tmp.next = nodeList.get(i);
            tmp = tmp.next;
        }
        return result;
    }

}
