package linkedlist;

import common.ListNode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tmp1 = l1;
        ListNode tmp2 = l2;
        int carry = 0;
        ListNode result = tmp2;
        while (true) {
            if (tmp1 == null || tmp2 == null) {
                break;
            }
            int value = tmp2.val + tmp1.val + carry;
            if (value >= 10) {
                carry = 1;
                value = value - 10;
            } else {
                carry = 0;
            }
            tmp2.val = value;
            if (tmp1.next == null || tmp2.next == null) {
                break;
            }
            tmp1 = tmp1.next;
            tmp2 = tmp2.next;
        }
        if (tmp1.next != null) {
            tmp2.next = tmp1.next;
        } else if (tmp1.next == null && tmp2.next == null && carry == 1) {
            tmp2.next = new ListNode(carry);
            carry = 0;
        }
        while (carry == 1) {
            if (tmp2.next == null) {
                tmp2.next = new ListNode(carry);
                break;
            }
            int value = tmp2.next.val + carry;
            if (value >= 10) {
                tmp2.next.val = value - 10;
            } else {
                tmp2.next.val = value;
                carry = 0;
            }
            tmp2 = tmp2.next;
        }
        return result;
    }

    public static void main(String[] args) {
        /**
         * [2,4,3]
         * [5,6,4]
         */
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        ListNode node21 = new ListNode(5);
        ListNode node22 = new ListNode(6);
        ListNode node23 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode result = addTwoNumbers.addTwoNumbers(node1, node21);
    }
}
