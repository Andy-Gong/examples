package dp;

/**
 * 找零案例，有一组不同额度的钱，给定一个数值，求换钱有多少种方法.
 */
public class MoneyExample {

    public static int recursion(int[] e, int index, int sum) {
        if (index == e.length - 1) {
            if (sum == 0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (sum == 0) {
                return 1;
            } else if (sum < 0) {
                return 0;
            }
        }
        int count = 0;
        for (int i = 0; e[index] * i <= sum; i++) {
            count += recursion(e, index + 1, sum - (e[index] * i));
        }
        return count;
    }

    public static int dynamic(int[] e, int index, int sum) {
        if (index == e.length - 1) {
            if (sum == 0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (sum == 0) {
                return 1;
            } else if (sum < 0) {
                return 0;
            }
        }
        int count = 0;
        for (int i = 0; e[index] * i <= sum; i++) {
            count += recursion(e, index + 1, sum - (e[index] * i));
        }
        return count;
    }

    public static void main(String[] args) {
        int[] e = new int[]{1, 5, 10, 50, 100, 20};
        System.out.println(recursion(e, 0, 5));
        System.out.println(recursion(e, 0, 10));
        System.out.println(recursion(e, 0, 20));
    }
}
