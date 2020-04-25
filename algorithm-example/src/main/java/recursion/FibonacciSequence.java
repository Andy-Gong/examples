package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].
 *
 * Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
 *
 * 0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
 * F.length >= 3;
 * and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
 * Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.
 *
 * Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.
 *
 * Example 1:
 *
 * Input: "123456579"
 * Output: [123,456,579]
 * Example 2:
 *
 * Input: "11235813"
 * Output: [1,1,2,3,5,8,13]
 * Example 3:
 *
 * Input: "112358130"
 * Output: []
 * Explanation: The task is impossible.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence
 */
public class FibonacciSequence {

    public List<Integer> splitIntoFibonacci(String S) {
        int[] candidates = new int[S.length()];
        char[] chars = S.toCharArray();
        for (int i = 0; i < S.length(); i++) {
            candidates[i] = Integer.valueOf(String.valueOf(chars[i]));
        }
        List<Integer> result = new ArrayList<Integer>();
        StringBuilder first = new StringBuilder();
        for (int i = 0; i < candidates.length / 2; i++) {
            first.append(candidates[i]);
            String firstStr = first.toString();
            if (firstStr.length() > 1 && firstStr.substring(0,1).equals("0")) {
                break;
            }
            Long firstValue = Long.valueOf(firstStr);
            if (firstValue > Integer.MAX_VALUE) {
                break;
            }
            StringBuilder second = new StringBuilder();
            for (int j = i + 1; j < candidates.length; j++) {
                second.append(candidates[j]);
                Long secondValue = Long.valueOf(second.toString());
                if (secondValue > Integer.MAX_VALUE) {
                    break;
                }
                result.add(firstValue.intValue());
                result.add(secondValue.intValue());
                List<Integer> tmpResult = recursion(candidates, result, j + 1);
                if (!tmpResult.isEmpty()) {
                    return tmpResult;
                }
                result.clear();
            }
        }
        return new ArrayList<Integer>();
    }

    public List<Integer> recursion(int[] candidates, List<Integer> selected, int begin) {
        if (begin > candidates.length) {
            return Collections.emptyList();
        }
        if (begin == candidates.length) {
            if (selected.size() >= 3) {
                return selected;
            } else {
                return Collections.emptyList();
            }
        }
        int nextValue = selected.get(selected.size() - 2) + selected.get(selected.size() - 1);
        int nextLength = String.valueOf(nextValue).length();
        if (nextLength + begin > candidates.length) {
            return Collections.emptyList();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = begin; i < nextLength + begin; i++) {
            sb.append(candidates[i]);
        }
        Long candidateValue = Long.valueOf(sb.toString());
        if (candidateValue > Integer.MAX_VALUE) {
            return Collections.emptyList();
        }
        if (candidateValue.intValue() != nextValue) {
            return Collections.emptyList();
        }
        selected.add(candidateValue.intValue());
        return recursion(candidates, selected, begin + nextLength);
    }

    public static void main(String[] args) {
        String input = "123456579";
        FibonacciSequence fibonacciSequence = new FibonacciSequence();
        System.out.println(fibonacciSequence.splitIntoFibonacci(input));
    }
}
