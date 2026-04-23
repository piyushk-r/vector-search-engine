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
        double[] query = { 1.5, 2.5 };

        Vector res = brute.nearest(query);
        System.out.println("Nearest Vector Id Using BruteForce : " + res.id);

        long start = System.nanoTime();
        List<Vector> topK = brute.findTopK(query, 2);
        long end = System.nanoTime();

        System.out.println("Nearest Top K Vector Ids Using BruteForce : ");
        for (Vector v : topK) {
            System.out.println("ID: " + v.id);
        }
        System.out.println("Time Taken : " + (end - start) + " ns");

    }
}
