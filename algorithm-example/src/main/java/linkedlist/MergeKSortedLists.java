package linkedlist;

import common.ListNode;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/merge-k-sorted-lists
 */
public class MergeKSortedLists {

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        while (true) {
            ListNode minNode = new ListNode(Integer.MAX_VALUE);
            int minIndex = -1;
            int emptyLists = 0;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < minNode.val) {
                    minNode = lists[i];
                    minIndex = i;
                } else if (lists[i] == null) {
                    emptyLists++;
                }
            }
            if (emptyLists == lists.length) {
                break;
            }
            tmp.next = minNode;
            tmp = tmp.next;
            lists[minIndex] = lists[minIndex].next;

        }
        return head.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int interval = 1;
        while (interval < lists.length) {
            for (int i = 0; i < lists.length && (i + interval) < lists.length; ) {
                lists[i] = merge2List(lists[i], lists[i + interval]);
                i += 2 * interval;
            }
            interval = 2 * interval;
        }
        return lists[0];
    }

    public ListNode merge2List(ListNode listNode1, ListNode listNode2) {
        if (listNode1 == null) {
            return listNode2;
        }
        ListNode tmp = listNode1;
        ListNode pre = null;
        while (true) {
            if (listNode2 == null) {
                break;
            }
            if (tmp == null) {
                while (listNode2 != null) {
                    pre.next = listNode2;
                    listNode2 = listNode2.next;
                    pre = pre.next;
                }
                break;
            }
            if (listNode2.val < tmp.val) {
                if (pre == null) {
                    ListNode next2 = listNode2.next;
                    listNode2.next = tmp;
                    listNode1 = listNode2;
                    pre = listNode2;
                    listNode2 = next2;
                } else {
                    ListNode next2 = listNode2.next;
                    pre.next = listNode2;
                    listNode2.next = tmp;
                    pre = pre.next;
                    listNode2 = next2;
                }
            } else if (listNode2.val == tmp.val) {
                ListNode next1 = tmp.next;
                ListNode next2 = listNode2.next;
                tmp.next = listNode2;
                listNode2.next = next1;
                pre = tmp;
                tmp = listNode2;
                listNode2 = next2;
            } else {
                pre = tmp;
                tmp = tmp.next;
            }
        }
        return listNode1;
    }

    public static void main(String[] args) {
        /**
         * [[],[-1,5,11],[],[6,10]]
         */
        ListNode listNode1 = new ListNode(-1);
        listNode1.next = new ListNode(5);
        listNode1.next.next = new ListNode(11);
        ListNode listNode2 = new ListNode(6);
        listNode2.next = new ListNode(11);
        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
        mergeKSortedLists.mergeKLists(new ListNode[]{null, listNode1, null, listNode2});
    }
}
