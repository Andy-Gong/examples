package dc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestSubSequence {

    public static List<Integer> longestSub(int[] e, int left, int right) {
        if (left == right) {
            return new ArrayList<Integer>(Arrays.asList(left, right));
        }
        //divide it to sub-problems
        int middle = (left + right) / 2;
        List<Integer> maxRange = new ArrayList();
        List<Integer> leftRange = longestSub(e, left, middle);
        List<Integer> rightRange = longestSub(e, middle + 1, right);
        int sumLeft = 0;
        int sumRight = 0;
        int maxSum;
        for (int i = leftRange.get(0); i <= leftRange.get(1); i++) {
            sumLeft += e[i];
        }
        for (int i = rightRange.get(0); i <= rightRange.get(1); i++) {
            sumRight += e[i];
        }
        if (sumLeft >= sumRight) {
            maxRange.addAll(leftRange);
            maxSum = sumLeft;
        } else {
            maxRange.addAll(rightRange);
            maxSum = sumRight;
        }
        /**start to calculate middle sub sequence*/
        int sumMiddleLeft = 0;
        int middleLeft = 0;
        int tmpLeft = 0;
        for (int i = middle; i >= left; i--) {
            tmpLeft = tmpLeft + e[i];
            if (tmpLeft >= sumMiddleLeft) {
                sumMiddleLeft = tmpLeft;
                middleLeft = i;
            }
        }
        int sumMiddleRight = 0;
        int middleRight = 0;
        int tmpRight = 0;
        for (int i = middle + 1; i <= right; i++) {
            tmpRight = tmpRight + e[i];
            if (tmpRight >= sumMiddleRight) {
                sumMiddleRight = tmpRight;
                middleRight = i;
            }
        }
        /**end to calculate middle sub sequence*/

        //merge the results of sub-problems
        if (maxSum < (sumMiddleLeft + sumMiddleRight)) {
            maxRange.clear();
            maxRange.add(middleLeft);
            maxRange.add(middleRight);
            maxSum = sumMiddleLeft + sumMiddleRight;
        }
        System.out.println("sum:" + maxSum + ", left: " + maxRange.get(0) + ", right: " + maxRange.get(1));
        return maxRange;
    }

    public static void main(String[] args) {
        int[] e = new int[]{1, -2, 5, 3, 7, -3, 4, -5, 6};
        List<Integer> result = longestSub(e, 0, 8);
        System.out.println("left: " + result.get(0) + ", right: " + result.get(1));
    }
}
