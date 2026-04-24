import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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

    public List<Vector> topK(KDNode root, double[] target, int k) {

        PriorityQueue<NodeDist> pq = new PriorityQueue<>((a, b) -> Double.compare(b.distance, a.distance));

        dfs(root, target, k, pq);

        List<Vector> results = new ArrayList<>();
        for (NodeDist nd : pq) {
            results.add(nd.vector);
        }

        return results;
    }

    private void dfs(KDNode node, double[] target, int k, PriorityQueue<NodeDist> pq) {
        if (node == null)
            return;

        double dist = DistanceUtil.euclidean(node.point.values, target);
        int axis = node.axis;

        if (pq.size() < k) {
            pq.offer(new NodeDist(node.point, dist));
        } else if (dist < pq.peek().distance) {
            pq.poll();
            pq.offer(new NodeDist(node.point, dist));
        }

        KDNode near = (target[axis] < node.point.values[axis]) ? node.left : node.right;
        KDNode far = (target[axis] > node.point.values[axis]) ? node.left : node.right;

        dfs(near, target, k, pq);
        // pruning
        if (pq.size() < k || Math.abs(target[axis] - node.point.values[axis]) < pq.peek().distance) {
            dfs(far, target, k, pq);
        }

    }
}