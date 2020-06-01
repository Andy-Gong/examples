package linkedlist;

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
//    public ListNode sortList(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode slow = head;
//        ListNode fast = head.next;
//        while (slow != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        ListNode tmp = slow.next;
//        slow.next = null;
//        ListNode left = sortList(head);
//        ListNode right = sortList(tmp);
//        ListNode result = new ListNode(0);
//        ListNode mergeTmp = result;
//        while (left != null && right != null) {
//            if (left.val < right.val) {
//                mergeTmp.next = left;
//                mergeTmp = mergeTmp.next;
//                left = left.next;
//            } else if (left.val > right.val) {
//                mergeTmp.next = right;
//                mergeTmp = mergeTmp.next;
//                right = right.next;
//            } else {
//                mergeTmp.next = right;
//                mergeTmp.next.next=left;
//                mergeTmp = mergeTmp.next.next;
//                right = right.next;
//                left = left.next;
//            }
//        }
//        if (left != null) {
//            mergeTmp.next = left;
//        }
//        if (right != null) {
//            mergeTmp.next = right;
//        }
//        return result.next;
//    }


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tmp = findMiddle(head);
        ListNode rightNext = tmp.next;
        tmp.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(rightNext);
        ListNode result = left.val < right.val ? left : right;
        mergeToList(null, left, right);
        return result;
    }

    ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    void mergeToList(ListNode leftPre, ListNode left, ListNode right) {
        if (left == null) {
            leftPre.next = right;
            return;
        }
        if (right == null) {
            return;
        }
        if (left.val < right.val) {
            mergeToList(left, left.next, right);
        } else {
            ListNode newRight = right.next;
            right.next = left;
            if (leftPre != null) {
                leftPre.next = right;
            }
            mergeToList(right, left, newRight);
        }
    }

    public static void main(String[] args){
        //[4,2,1,3]
        ListNode listNode1 = new ListNode(4);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(1);
        listNode1.next.next.next = new ListNode(3);
        SortList sortList = new SortList();
        sortList.sortList(listNode1);
    }
}
