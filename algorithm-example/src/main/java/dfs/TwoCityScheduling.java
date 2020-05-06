package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
 *
 * Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 *
 *  
 *
 * Example 1:
 *
 * Input: [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 *
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/two-city-scheduling
 */
public class TwoCityScheduling {

    int minCost = Integer.MAX_VALUE;

    public int twoCitySchedCost(int[][] costs) {
        List<Integer> cityA = new ArrayList();
        dfs(costs, cityA, 0);
        return minCost;
    }

    public void dfs(int[][] costs, List<Integer> cityA, int index) {
        if (cityA.size() == costs.length / 2) {
            System.out.println(cityA);
            int tmpCost = 0;
            for (int i = 0; i < costs.length; i++) {
                if (cityA.contains(i)) {
                    tmpCost += costs[i][0];
                } else {
                    tmpCost += costs[i][1];
                }
            }
            System.out.println(tmpCost);
            if (minCost > tmpCost) {
                minCost = tmpCost;
            }
            return;
        }
        if (index == costs.length) {
            return;
        }
        if (cityA.isEmpty() && index == ((costs.length / 2) - 1)) {
            System.out.println(cityA);
            int tmpCost = 0;
            for (int i = 0; i < costs.length; i++) {
                if (i > index) {
                    tmpCost += costs[i][0];
                } else {
                    tmpCost += costs[i][1];
                }
            }
            if (minCost > tmpCost) {
                minCost = tmpCost;
            }
            return;
        }
        dfs(costs, new ArrayList<Integer>(cityA), index + 1);
        List<Integer> newCityA = new ArrayList<Integer>(cityA);
        newCityA.add(index);
        dfs(costs, newCityA, index + 1);
    }

    public static void main(String[] args) {
        int[][] costs = {{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}};
        TwoCityScheduling twoCityScheduling = new TwoCityScheduling();
        twoCityScheduling.twoCitySchedCost(costs);
        System.out.println(twoCityScheduling.minCost);
    }
}
