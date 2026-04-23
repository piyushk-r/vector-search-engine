import java.util.ArrayList;
import java.util.List;

public class VectorStore {

    private List<Vector> vectors = new ArrayList<>();

    public void addVector(Vector v) {
        vectors.add(v);
    }

    public List<Vector> getAll() {
        return vectors;
    }
}