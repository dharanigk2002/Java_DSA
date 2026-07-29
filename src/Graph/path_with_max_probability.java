/*
Path with Maximum Probability

You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.

https://leetcode.com/problems/path-with-maximum-probability/description/
*/

record Pair<K, V>(K key, V value) {}

public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
    double[] ans = new double[n];
    List<List<Pair<Integer, Double>>> adj = new ArrayList();
    for (int i = 0; i < n; i++)
        adj.add(new ArrayList());

    n = edges.length;
    for (int i = 0; i < n; i++) {
        int[] edge = edges[i];
        double succ = succProb[i];
        adj.get(edge[0]).add(new Pair<>(edge[1], succ));
        adj.get(edge[1]).add(new Pair<>(edge[0], succ));
    }
    Arrays.fill(ans, 0.0);
    ans[start_node] = 1.0;
    Queue<Pair<Integer, Double>> q = new PriorityQueue<>((a, b) -> Double.compare(b.value(), a.value()));
    q.add(new Pair<>(start_node, 1.0));
    while (!q.isEmpty()) {
        Pair<Integer, Double> node = q.poll();
        if (node.value() < ans[node.key()])
            continue;
        for (Pair<Integer, Double> next : adj.get(node.key())) {
            double succ = next.value() * node.value();
            if (ans[next.key()] < succ) {
                ans[next.key()] = succ;
                q.add(new Pair<>(next.key(), succ));
            }
        }
    }
    return ans[end_node];
}

void main() {
    int n = 3;
    int[][] edges = {{0,1},{1,2},{0,2}};
    double[] succProb = {0.5,0.5,0.2};
    int start = 0, end = 2;

    System.out.println(maxProbability(n, edges, succProb, start, end));
}