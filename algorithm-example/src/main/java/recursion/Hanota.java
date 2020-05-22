package recursion;

import java.util.List;

public class Hanota {

    /**
     * n = 1 时，直接把盘子从 A 移到 C；
     * n > 1 时，
     * 先把上面 n - 1 个盘子从 A 移到 B（子问题，递归）；
     * 再将最大的盘子从 A 移到 C；
     * 再将 B 上 n - 1 个盘子从 B 移到 C（子问题，递归）。
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanota(A.size(), A, B, C);
    }

    /**
     * function: f(N) move the Nth element from A to C
     * jump out condition: n=1
     * equation:
     *    Move N-1 from A to B,
     *    Move Nth from A to C
     *    Move (N-1)th from B to C
     */
    public void hanota(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n == 1) {
            C.add(A.remove(A.size() - 1));
        } else {
            hanota(n - 1, A, C, B);
            C.add(A.remove(A.size() - 1));
            hanota(n - 1, B, A, C);
        }
    }
}
