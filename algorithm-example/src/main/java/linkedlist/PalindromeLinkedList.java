package linkedlist;

import common.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 * Example 2:
 *
 * Input: 1->2->2->1
 * Output: true
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/palindrome-linked-list
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        int totalCount = 0;
        ListNode tempt = head;
        while (tempt != null) {
            totalCount++;
            tempt = tempt.next;
        }
        ListNode preHalfNode = null;
        ListNode postHalfNode = head;
        for (int i = 0; i < totalCount / 2; i++) {
            ListNode tmp = postHalfNode.next;
            postHalfNode.next = preHalfNode;
            preHalfNode = postHalfNode;
            postHalfNode = tmp;
        }
        //compare preHalfNode and head
        if (totalCount % 2 == 0) {
            while (preHalfNode != null) {
                if (preHalfNode.val == postHalfNode.val) {
                    preHalfNode = preHalfNode.next;
                    postHalfNode = postHalfNode.next;
                } else {
                    return false;
                }
            }
        } else {
            postHalfNode = postHalfNode.next;
            while (preHalfNode != null) {
                if (preHalfNode.val == postHalfNode.val) {
                    preHalfNode = preHalfNode.next;
                    postHalfNode = postHalfNode.next;
                } else {
                    return false;
                }
            }

        }
        return true;
    }
}
