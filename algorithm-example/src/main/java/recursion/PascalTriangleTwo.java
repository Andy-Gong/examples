package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 3
 * Output: [1,3,3,1]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/pascals-triangle-ii
 */
public class PascalTriangleTwo {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = Arrays.asList(1);
        return buildLayer(result, 1, rowIndex);
    }

    public List<Integer> buildLayer(List<Integer> preLayer, int currentLayer, int totalLayer) {
        if (currentLayer == totalLayer) {
            return preLayer;
        }
        List<Integer> values = new ArrayList();
        values.add(1);
        for (int i = 1; i < currentLayer; i++) {
            values.add(preLayer.get(i - 1) + preLayer.get(i));
        }
        values.add(1);
        return buildLayer(values, currentLayer + 1, totalLayer);
    }
}
