package greedy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * At a lemonade stand, each lemonade costs $5. 
 *
 * Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).
 *
 * Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.  You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.
 *
 * Note that you don't have any change in hand at first.
 *
 * Return true if and only if you can provide every customer with correct change.
 *
 *  
 *
 * Example 1:
 *
 * Input: [5,5,5,10,20]
 * Output: true
 * Explanation:
 * From the first 3 customers, we collect three $5 bills in order.
 * From the fourth customer, we collect a $10 bill and give back a $5.
 * From the fifth customer, we give a $10 bill and a $5 bill.
 * Since all customers got correct change, we output true.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/lemonade-change
 */
public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] > 5) {
                if (priorityQueue.isEmpty()) {
                    return false;
                } else {
                    List<Integer> removeBills = new ArrayList<>();
                    int change = bills[i] - 5;
                    Iterator<Integer> iterator = priorityQueue.iterator();
                    while (iterator.hasNext()) {
                        if (change == 0) {
                            break;
                        }
                        Integer value = iterator.next();
                        if (value > change) {
                            continue;
                        } else {
                            removeBills.add(value);
                            change -= value;
                        }
                    }
                    if (change!=0){
                        return false;
                    }
                    removeBills.forEach(removeBill -> priorityQueue.remove(removeBill));
                    priorityQueue.add(Integer.valueOf(bills[i]));
                }
            } else {
                priorityQueue.add(bills[i]);
            }
        }
        return true;
    }

    public static void main(String[] args){
        int[] bills = new int[]{5,5,5,10,20};
        LemonadeChange lemonadeChange = new LemonadeChange();
        System.out.println(lemonadeChange.lemonadeChange(bills));
    }
}
