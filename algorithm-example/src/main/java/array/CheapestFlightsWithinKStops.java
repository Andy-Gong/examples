package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 *
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
 */
public class CheapestFlightsWithinKStops {

    int cheapest = Integer.MAX_VALUE;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> srcFlights = new HashMap<>();
        for (int i = 0; i < flights.length; i++) {
            if (srcFlights.get(flights[i][0]) == null) {
                srcFlights.put(flights[i][0], new ArrayList<>());
            }
            srcFlights.get(flights[i][0]).add(flights[i]);
        }
        dfs(n, srcFlights, src, dst, K + 1, 0);
        return cheapest == Integer.MAX_VALUE ? -1 : cheapest;
    }


    public void dfs(int n, Map<Integer, List<int[]>> srcFlights, int src, int dst, int K, int fee) {
        if (K < 0) {
            return;
        }
        if (src == dst) {
            if (fee < cheapest) {
                cheapest = fee;
            }
            return;
        }
        if (fee > cheapest) {
            return;
        }
        List<int[]> tmp = srcFlights.get(src);
        if (tmp!=null) {
            for (int i = 0; i < tmp.size(); i++) {
                int[] flight = tmp.get(i);
                if (flight[0] == src) {
                    dfs(n, srcFlights, flight[1], dst, K - 1, fee + flight[2]);
                }
            }
        }
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops cheapestFlightsWithinKStops = new CheapestFlightsWithinKStops();
        int[][] flights = new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        System.out.println(cheapestFlightsWithinKStops.findCheapestPrice(3, flights, 0, 2, 0));
    }
}
