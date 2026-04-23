import java.util.List;

public class BruteForceSearch {

    private VectorStore vectors;

    public BruteForceSearch(VectorStore vectors) {
        this.vectors = vectors;
    }

    public Vector nearest(double[] target) {
        Vector best = null;
        double dist = Double.MAX_VALUE;
        for (Vector v : vectors.getAll()) {
            double vectorDist = DistanceUtil.euclidean(v.values, target);
            if (vectorDist < dist) {
                best = v;
                dist = vectorDist;
            }
        }
        return best;
    }

    public List<Vector> findTopK(double[] target, int k) {
        List<Vector> all = vectors.getAll();
        return all.stream().sorted((a, b) -> Double.compare(
                DistanceUtil.euclidean(a.values, target),
                DistanceUtil.euclidean(b.values, target))).limit(k).toList();
    }
}