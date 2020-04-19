package devideandconquer;

/**
 * The sorting problem can be divided to sub-problems, n/2 elements sorting, and merge the sorting results.
 */
public class MergedSort {

    public static void sort(int[] e, int start, int end, int[] tmp) {
        if (start >= end) {
            return;
        }
        int middle = (start + end) / 2;
        sort(e, start, middle, tmp);
        sort(e, middle + 1, end, tmp);
        int i = start;
        int j = middle + 1;
        int k = start;
        System.out.println("i:" + i + "j:" + j + "k:" + k);
        while (i <= middle && j <= end) {
            if (e[i] <= e[j]) {
                tmp[k++] = e[i++];
            } else {
                tmp[k++] = e[j++];
            }
        }
        while (i <= middle) {
            tmp[k++] = e[i++];
        }
        while (j <= end) {
            tmp[k++] = e[j++];
        }
        for (int l = start; l <= end; l++) {
            e[l] = tmp[l];
        }
    }

    public static void main(String[] args) {
        int[] e = new int[]{2, 5, 3, 1, 9, 5, 2, 4, 7, 4};
        sort(e, 0, 9, new int[10]);
        for (int i = 0; i < e.length; i++) {
            System.out.print(e[i] + ", ");
        }
    }
}
