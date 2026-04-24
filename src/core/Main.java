import java.util.List;

public class Main {
    public static void main(String[] args) {
        VectorStore store = new VectorStore();

        // adding sample vectors
        store.addVector(new Vector(1, new double[] { 1, 2 }));
        store.addVector(new Vector(2, new double[] { 2.0, 3.0 }));
        store.addVector(new Vector(3, new double[] { 12.0, 5 }));
        store.addVector(new Vector(4, new double[] { 15.0, 5 }));

        BruteForceSearch brute = new BruteForceSearch(store);
        double[] query = { 13, 5 };

        Vector res = brute.nearest(query);
        System.out.println("Nearest Vector Id Using BruteForce : " + res.id);

        long start = System.nanoTime();
        List<Vector> topK = brute.findTopK(query, 2);
        long end = System.nanoTime();

        System.out.println("Nearest Top K Vector Ids Using BruteForce : ");
        for (Vector v : topK) {
            System.out.println("ID: " + v.id);
        }
        System.out.println("Time Taken for Brute Top K: " + (end - start) + " ns");

        KDTree tree = new KDTree(store.getAll());
        KDTreeSearch kdSearch = new KDTreeSearch();

        long kdStart = System.nanoTime();
        Vector kdResult = kdSearch.nearest(tree.getRoot(), query);
        long kdEnd = System.nanoTime();
        System.out.println("Nearest Vector Id Using KDTree : " + kdResult.id);
        System.out.println("Time Taken for KDTree: " + (kdEnd - kdStart) + " ns");

        double speedup = (double) (end - start) / (kdEnd - kdStart);
        System.out.println("Speedup: " + speedup + "x");
    }
}
