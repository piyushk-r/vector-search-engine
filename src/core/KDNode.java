public class KDNode {
    Vector point;
    KDNode left, right;
    int axis;

    public KDNode(Vector point, int axis) {
        this.point = point;
        this.axis = axis;
    }
}