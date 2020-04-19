package dynamic;

public class CutRodExample {

    public static int cutRod(int[] p, int n) {
        if (n == 0) {
            return 0;
        }
        int value = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int tmp = p[i - 1] + cutRod(p, n - i);
            if (value < tmp) {
                value = tmp;
            }
        }
        return value;
    }

    public static int cutRodCache(int[] p, int n, int[] r) {
        if (n == 0) {
            return 0;
        }
        if (r[n - 1] > Integer.MIN_VALUE) {
            return r[n - 1];
        }
        int value = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int tmp = p[i - 1] + cutRodCache(p, n - i, r);
            if (value < tmp) {
                value = tmp;
            }
        }
        r[n - 1] = value;
        return value;
    }

    public static void main(String[] args) {
        int[] p = new int[]{1, 5, 8, 9, 10, 17, 17, 17};
        int[] r = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
            Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        long start = System.currentTimeMillis();
        System.out.println(cutRod(p, 8) + " total time: " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        System.out.println(cutRodCache(p, 8, r) + " total time: " + (System.currentTimeMillis() - start));
    }

}
