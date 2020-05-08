package linkedlist;

import common.ListNode;

/**
 * You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.
 *
 *  
 *
 * Example:
 *
 * Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295.
 * Output: 2 -> 1 -> 9. That is, 912.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/sum-lists-lcci
 */
public class SumLists {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tmp = l1;
        boolean carryFlag = false;
        while (l2 != null && tmp != null) {
            int sum = tmp.val + l2.val;
            if (carryFlag) {
                sum++;
                carryFlag = false;
            }
            if (sum >= 10) {
                carryFlag = true;
                tmp.val = sum - 10;
            } else {
                tmp.val = sum;
            }
            if (tmp.next == null && l2.next == null && carryFlag) {
                ListNode newNode = new ListNode(1);
                tmp.next = newNode;
                carryFlag = false;
            }
            tmp = tmp.next;
            l2 = l2.next;
        }
        while (tmp != null && carryFlag) {
            tmp.val = tmp.val + 1;
            if (tmp.val == 10) {
                tmp.val = 0;
                carryFlag = true;
                if (tmp.next == null) {
                    ListNode newNode = new ListNode(1);
                    tmp.next = newNode;
                    tmp = newNode.next;
                } else {
                    tmp = tmp.next;
                }
            } else {
                break;
            }
        }
        if (l2 != null) {
            ListNode tmpl2 = l2;
            while (tmpl2 != null && carryFlag) {
                tmpl2.val = tmpl2.val + 1;
                if (tmpl2.val == 10) {
                    tmpl2.val = 0;
                    carryFlag = true;
                    if (tmpl2.next == null) {
                        ListNode newNode = new ListNode(1);
                        tmpl2.next = newNode;
                        tmpl2 = newNode.next;
                    } else {
                        tmpl2 = tmpl2.next;
                    }
                } else {
                    break;
                }
            }
            ListNode tmp2 = l1;
            while (tmp2.next != null) {
                tmp2 = tmp2.next;
            }
            tmp2.next = l2;
        }
        return l1;
    }

    public static void main(String[] args) {
        /**
         * [8,9,9]
         * [2]
         */
        ListNode l1 = new ListNode(8);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        ListNode l2 = new ListNode(2);
        SumLists sumLists = new SumLists();
        sumLists.addTwoNumbers(l1, l2);
    }
}
