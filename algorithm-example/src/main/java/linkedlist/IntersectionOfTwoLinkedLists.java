package linkedlist;

import java.util.HashMap;

import common.ListNode;

/**
 * Given two (singly) linked lists, determine if the two lists intersect. Return the inter­ secting node. Note that the intersection is defined based on reference, not value. That is, if the kth node of the first linked list is the exact same node (by reference) as the jth node of the second linked list, then they are intersecting.
 *
 * Example 1:
 *
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * Output: Reference of the node with value = 8
 * Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci
 */
public class IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode, Integer> AMap = new HashMap<>();
        while (headA != null) {
            AMap.put(headA, headA.val);
            headA = headA.next;
        }
        while (headB != null) {
            if (AMap.containsKey(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}
