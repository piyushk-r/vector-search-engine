import java.util.List;

public class BruteForceSearch {
    public static Vector nearest(List<Vector> vectors, double[] target) {
        Vector best = null;
        double dist = Double.MAX_VALUE;
        for (Vector v : vectors) {
            double vectorDist = DistanceUtil.euclidean(v.values, target);
            if (vectorDist < dist) {
                best = v;
                dist = vectorDist;
            }
        }
        return best;
    }
}