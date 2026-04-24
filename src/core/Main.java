import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        VectorStore store = new VectorStore();

        // adding sample vectors
        store.addVector(new Vector(1, new double[] { 1, 2 }));
        store.addVector(new Vector(2, new double[] { 2.0, 3.0 }));
        store.addVector(new Vector(3, new double[] { 12.0, 5 }));
        store.addVector(new Vector(4, new double[] { 15.0, 5 }));
        // lets generate 5000 sample outputs
        for (int i = 5; i <= 5000; i++) {
            store.addVector(new Vector(i, new double[] {
                    Math.random() * 100,
                    Math.random() * 100
            }));
        }

        BruteForceSearch brute = new BruteForceSearch(store);
        double[] query = { 13, 5 };

        Vector res = brute.nearest(query);
        System.out.println("Nearest Vector Id Using BruteForce : " + res.id);

        long start = System.nanoTime();
        List<Vector> topK = new ArrayList<>(brute.findTopK(query, 2));
        long end = System.nanoTime();
        topK.sort((a, b) -> a.id - b.id);

        System.out.println("Nearest Top K Vector Ids Using BruteForce : ");
        for (Vector v : topK) {
            System.out.println("ID: " + v.id);
        }
        System.out.println("Time Taken for Brute Top K: " + (end - start) + " ns");

        KDTree tree = new KDTree(store.getAll());
        KDTreeSearch kdSearch = new KDTreeSearch();
        Vector kdResult = kdSearch.nearest(tree.getRoot(), query);
        System.out.println("Nearest Vector Id Using KDTree : " + kdResult.id);

        long kdStart = System.nanoTime();
        topK = new ArrayList<>(kdSearch.topK(tree.getRoot(), query, 2));
        long kdEnd = System.nanoTime();
        topK.sort((a, b) -> a.id - b.id);
        for (Vector v : topK) {
            System.out.println("ID: " + v.id);
        }
        System.out.println("Time Taken for KDTree Top K: " + (kdEnd - kdStart) + " ns");

        double speedup = (double) (end - start) / (kdEnd - kdStart);
        System.out.println("Speedup: " + speedup + "x");
    }
}
