package recursion;

import common.ListNode;

/**
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each
 * occurrence of 1 with 10. Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 *
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 2
 * Output: 1
 *
 * Input: N = 4, K = 5
 * Output: 1
 *
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/k-th-symbol-in-grammar
 */
public class KthSymbolInGrammar {

    public int kthGrammar(int N, int K) {
        if (N == 1) {
            return 0;
        }
        ListNode listNode = new ListNode(0);
        return kthGrammar(N, 2, listNode, K);
    }

    public int kthGrammar(int N, int index, ListNode value, int K) {
        if (index == N + 1) {
            for (int i = 1; i < K; i++) {
                value = value.next;
            }
            return value.val;

        }
        ListNode newValue = null;
        ListNode newTmp = null;
        while (value != null) {
            if (newValue == null) {
                newValue = new ListNode(0);
                newTmp = newValue;
                newTmp.next = new ListNode(1);
                newTmp = newTmp.next;
            } else {
                if (value.val == 0) {
                    newTmp.next = new ListNode(0);
                    newTmp.next.next = new ListNode(1);
                } else {
                    newTmp.next = new ListNode(1);
                    newTmp.next.next = new ListNode(0);
                }
                newTmp = newTmp.next.next;
            }
            value = value.next;
        }
        return kthGrammar(N, ++index, newValue, K);
    }

    public static void main(String[] args) {
        KthSymbolInGrammar kthSymbolInGrammar = new KthSymbolInGrammar();
        System.out.println(kthSymbolInGrammar.kthGrammar(30, 434991989));
    }
}
