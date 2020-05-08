package queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        //group by task
        Map<Character, Integer> tasksCount = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            Integer count = tasksCount.get(tasks[i]);
            if (count == null) {
                tasksCount.put(tasks[i], 1);
            } else {
                tasksCount.put(tasks[i], ++count);
            }
        }
        //put all grouped task into queue
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (Integer count : tasksCount.values()) {
            queue.add(count);
        }
        //process tasks
        int intervals = 0;
        List<Integer> selectedCount = new ArrayList<>();
        while (true) {
            selectedCount.clear();
            while (!queue.isEmpty() && selectedCount.size() <= n) {
                Integer count = queue.poll();
                if (count > 0) {
                    selectedCount.add(count);
                }
            }
            for (Integer count : selectedCount) {
                count--;
                if (count > 0) {
                    queue.add(count);
                }
            }
            if (queue.isEmpty()) {
                intervals += selectedCount.size();
                break;
            } else {
                intervals += n + 1;
            }
        }
        return intervals;
    }
}
