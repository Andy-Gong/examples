package linkedlist;


import common.ListNode;

/**
 * Implement an algorithm to delete a node in the middle (i.e., any node but the first and last node, not necessarily the exact middle) of a singly linked list, given only access to that node.
 *
 *  
 *
 * Example:
 *
 * Input: the node c from the linked list a->b->c->d->e->f
 * Output: nothing is returned, but the new linked list looks like a->b->d->e->f
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/delete-middle-node-lcci
 */
public class DeleteMiddleNode {


    public void deleteNode(ListNode node) {
        if (node.next == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
