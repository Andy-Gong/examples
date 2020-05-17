package twopointers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
 *
 * Example:
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/ugly-number-ii
 */
public class UglyNumberII {

    /**
     * three pointers
     */
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        } else {
            Set<Integer> existedValues = new HashSet();
            List<Integer> values = new ArrayList<>();
            int pointer2 = 0;
            int pointer3 = 0;
            int pointer5 = 0;
            values.add(1);
            existedValues.add(1);
            while (true) {
                int value2 = values.get(pointer2) * 2;
                int value3 = values.get(pointer3) * 3;
                int value5 = values.get(pointer5) * 5;
                int value = Math.min(Math.min(value2, value3), value5);
                if (value == value2) {
                    pointer2++;
                } else if (value == value3) {
                    pointer3++;
                } else {
                    pointer5++;
                }
                if (!existedValues.contains(value)) {
                    values.add(value);
                    existedValues.add(value);
                }
                if (values.size() == n) {
                    return values.get(n - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        UglyNumberII uglyNumberII = new UglyNumberII();
        System.out.println(uglyNumberII.nthUglyNumber(10));
    }
}
