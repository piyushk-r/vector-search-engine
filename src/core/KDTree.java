import java.util.List;

public class KDTree {
    private KDNode root;

    public KDTree(List<Vector> points) {
        this.root = build(points, 0);
    }

    private KDNode build(List<Vector> points, int depth) {
        if (points == null || points.isEmpty())
            return null;
        int axis = depth % points.get(0).values.length;

        points.sort((a, b) -> Double.compare(a.values[axis], b.values[axis]));
        int median = points.size() / 2;
        KDNode node = new KDNode(points.get(median), axis);
        node.left = build(points.subList(0, median), depth + 1);
        node.right = build(points.subList(median + 1, points.size()), depth + 1);

        return node;
    }

    public KDNode getRoot() {
        return root;
    }
}
