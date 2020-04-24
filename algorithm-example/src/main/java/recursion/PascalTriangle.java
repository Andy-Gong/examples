package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Input: 5
 * Output:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> preLayer = new ArrayList<Integer>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> layer = buildLayer(preLayer, i);
            result.add(layer);
            preLayer = layer;
        }
        return result;
    }

    public List<Integer> buildLayer(List<Integer> preLayer, int currentLayer) {
        if (currentLayer == 1) {
            return Arrays.asList(1);
        }
        if (currentLayer == 2) {
            return Arrays.asList(1, 1);
        }
        List<Integer> values = new ArrayList<Integer>(currentLayer);
        values.add(1);
        for (int i = 1; i < currentLayer - 1; i++) {
            values.add(preLayer.get(i - 1) + preLayer.get(i));
        }
        values.add(1);
        return values;
    }
}
