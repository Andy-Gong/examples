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
        buildLayer(result, 1, numRows);
        return result;
    }

    public void buildLayer(List<List<Integer>> layers, int currentLayer, int totalLayer) {
        if (currentLayer > totalLayer) {
            return;
        }
        if (currentLayer == 1) {
            layers.add(Arrays.asList(1));
        } else {
            List<Integer> values = new ArrayList<Integer>(currentLayer);
            values.add(1);
            List<Integer> preLayer = layers.get(layers.size() - 1);
            for (int i = 1; i < currentLayer - 1; i++) {
                values.add(preLayer.get(i - 1) + preLayer.get(i));
            }
            values.add(1);
            layers.add(values);
        }
        buildLayer(layers, currentLayer + 1, totalLayer);
    }
}
