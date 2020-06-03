package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * input n, out put all possible binary combination.
 * For example,
 * input n: 3
 * output :000,001,010,011,100,101,110,111
 */
public class BinaryCombination {

    /**
     * dfs algorithm, each step handles one question
     * @param n
     * @return
     */
    public List<List<Byte>> binary(int n) {
        if (n == 1) {
            List<List<Byte>> result = new ArrayList<>();
            List<Byte> value0 = new ArrayList<>();
            value0.add(new Byte((byte) 0));
            result.add(value0);
            List<Byte> value1 = new ArrayList<>();
            value1.add(new Byte((byte) 1));
            result.add(value1);
            return result;
        }

        List<List<Byte>> bytes = binary(n - 1);
        List<List<Byte>> result = new ArrayList<>();
        for (int i = 0; i < bytes.size(); i++) {
            List<Byte> tmp = bytes.get(i);
            List<Byte> value0 = new ArrayList<>(tmp);
            value0.add(new Byte((byte) 0));
            result.add(value0);
            List<Byte> value1 = new ArrayList<>(tmp);
            value1.add(new Byte((byte) 1));
            result.add(value1);
        }
        return result;
    }

    public static void  main(String[] args){
        BinaryCombination binaryCombination = new BinaryCombination();
        System.out.println(binaryCombination.binary(3));
    }
}
