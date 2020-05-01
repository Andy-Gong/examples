package twopointers;

/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 *  
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 *
 * source: LeetCode
 * link：https://leetcode-cn.com/problems/linked-list-cycle
 */
public class LinkedListCycle {

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (true) {
            if (slowNode == null || fastNode == null || fastNode.next == null) {
                return false;
            }
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
            if (slowNode.equals(fastNode)) {
                return true;
            }
        }
    }
}
