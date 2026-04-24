public class KDTreeSearch {
    private Vector best;
    private double bestDist = Double.MAX_VALUE;

    public Vector nearest(KDNode node, double[] target) {
        if (node == null)
            return best;

        double dist = DistanceUtil.euclidean(node.point.values, target);
        int axis = node.axis;

        if (dist < bestDist) {
            best = node.point;
            bestDist = dist;
        }

        KDNode next = (target[axis] < node.point.values[axis]) ? node.left : node.right;
        KDNode other = (target[axis] > node.point.values[axis]) ? node.left : node.right;
        nearest(next, target);

        if (Math.abs(target[axis] - node.point.values[axis]) < bestDist)
            nearest(other, target);

        return best;
    }
}