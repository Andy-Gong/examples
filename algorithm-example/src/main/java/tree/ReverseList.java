package tree;


/**
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class ReverseList {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        return recursion(head, null);
    }

    private ListNode recursion(ListNode head, ListNode next) {
        if (head == null) {
            return next;
        }
        ListNode tmp = head.next;
        if (next == null) {
            head.next = null;
        } else {
            head.next = next;

        }
        return recursion(tmp, head);
    }
}
