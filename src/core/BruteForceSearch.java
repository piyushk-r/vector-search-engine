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
}