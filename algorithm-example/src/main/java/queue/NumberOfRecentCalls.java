package queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Write a class RecentCounter to count recent requests.
 *
 * It has only one method: ping(int t), where t represents some time in milliseconds.
 *
 * Return the number of pings that have been made from 3000 milliseconds ago until now.
 *
 * Any ping with time in [t - 3000, t] will count, including the current ping.
 *
 * It is guaranteed that every call to ping uses a strictly larger value of t than before.
 *
 *  
 *
 * Example 1:
 *
 * Input: inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
 * Output: [null,1,2,3,3]
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/number-of-recent-calls
 */
public class NumberOfRecentCalls {

    class RecentCounter {
        private Queue<Integer> queue = new ConcurrentLinkedQueue();
        public RecentCounter() {

        }

        public int ping(int t) {
            queue.add(t);
            int oldestTime = t - 3000;
            if (oldestTime > 0) {
                while (queue.peek() < oldestTime) {
                    queue.poll();
                }
            }
            return queue.size();
        }
    }

    class RecentCounter1 {
        private LinkedList<Integer> list = new LinkedList<>();
        public RecentCounter1() {

        }

        public int ping(int t) {
            list.add(t);
            int oldestTime = t - 3000;
            if (oldestTime > 0) {
                while (list.peek() < oldestTime) {
                    list.poll();
                }
            }
            return list.size();
        }
    }
}
