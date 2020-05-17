package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Distance> pointsDistance = new PriorityQueue<>(Comparator.comparing(Distance::getDistance));
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            pointsDistance.add(new Distance(point, point[0] * point[0] + point[1] * point[1]));
        }
        int[][] closest = new int[K][2];
        for (int i = 0; i < K; i++) {
            closest[i] = pointsDistance.poll().point;
        }
        return closest;
    }

    static class Distance {

        int[] point;
        int distance;

        public Distance(int[] point, int distance) {
            this.point = point;
            this.distance = distance;
        }

        public int[] getPoint() {
            return point;
        }

        public int getDistance() {
            return distance;
        }
    }
}
