package linkedlist;

import common.ListNode;

/**
 * Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 *
 * Implement these functions in your linked list class:
 *
 * get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
 * addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
 * addAtTail(val) : Append a node of value val to the last element of the linked list.
 * addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
 * deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
 *  
 *
 * Example:
 *
 * Input:
 * ["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
 * [[],[1],[3],[1,2],[1],[1],[1]]
 * Output:
 * [null,null,null,null,2,null,3]
 *
 * Explanation:
 * MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
 * linkedList.get(1);            // returns 2
 * linkedList.deleteAtIndex(1);  // now the linked list is 1->3
 * linkedList.get(1);            // returns 3
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/design-linked-list
 */
public class DesignLinkedList {

    public static class MyLinkedList {

        int count = 0;
        ListNode head;
        ListNode tail;

        /**
         * Initialize your data structure here.
         */
        public MyLinkedList() {

        }

        /**
         * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
         */
        public int get(int index) {
            if (index < 0 || index >= count || count == 0) {
                return -1;
            } else {
                ListNode tmp = head;
                while (index-- != 0) {
                    tmp = tmp.next;
                }
                return tmp.val;
            }
        }

        /**
         * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
         */
        public void addAtHead(int val) {
            if (head == null) {
                head = new ListNode(val);
                tail = head;
            } else {
                ListNode newNode = new ListNode(val);
                newNode.next = head;
                head = newNode;
            }
            count++;
        }

        /**
         * Append a node of value val to the last element of the linked list.
         */
        public void addAtTail(int val) {
            if (head == null) {
                head = new ListNode(val);
                tail = head;
            } else {
                ListNode newNode = new ListNode(val);
                tail.next = newNode;
                tail = newNode;
            }
            count++;
        }

        /**
         * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
         */
        public void addAtIndex(int index, int val) {
            if (index > count) {
                return;
            }
            if (index == count) {
                addAtTail(val);
                return;
            }
            if (index <= 0) {
                addAtHead(val);
                return;
            }
            ListNode tmp = head;
            while (index-- != 1) {
                tmp = tmp.next;
            }
            ListNode newNode = new ListNode(val);
            ListNode next = tmp.next;
            newNode.next = next;
            tmp.next = newNode;
            count++;
        }

        /**
         * Delete the index-th node in the linked list, if the index is valid.
         */
        public void deleteAtIndex(int index) {
            if (index >= count || index < 0 || count == 0) {
                return;
            }
            if (count >= 1 && index == 0) {
                ListNode next = head.next;
                head.next = null;
                head = next;
            } else {
                ListNode tmp = head;
                while (index-- != 1) {
                    tmp = tmp.next;
                }
                tmp.next = tmp.next.next;
                if (tmp.next == null) {
                    tail = tmp;
                }
            }
            count--;
        }
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
        linkedList.get(1);            // returns 2
        linkedList.deleteAtIndex(1);  // now the linked list is 1->3
        linkedList.get(1);     // returns 3

    }
}
