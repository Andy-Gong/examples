package recursion;

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
public class KthSymbolInGrammarDC {

    public int kthGrammar(int N, int K) {
        if (N == 1) {
            return 0;
        }
        int[] values = new int[]{0};
        return kthGrammar(N, 2, values, K);
    }

    public int kthGrammar(int N, int index, int[] values, int K) {
        if (index == N + 1) {
            return values[K - 1];
        }
        int[] newValues = recursion(values, 0, values.length - 1);
        return kthGrammar(N, ++index, newValues, K);
    }

    public int[] recursion(int[] values, int start, int end) {
        int middle = (start + end) / 2;
        int[] newValues = new int[values.length * 2];
        int[] perValue = recursion(values, start, middle);
        int[] postValue = recursion(values, middle + 1, end);
        for (int i = 0; i < values.length; i++) {
            newValues[i] = perValue[i];
        }
        for (int i = 0; i < values.length; i++) {
            newValues[values.length + i] = postValue[i];
        }
        return recursion(newValues, middle, end);
    }

    public static void main(String[] args) {
        KthSymbolInGrammarDC kthSymbolInGrammar = new KthSymbolInGrammarDC();
        System.out.println(kthSymbolInGrammar.kthGrammar(30, 434991989));
    }
}
