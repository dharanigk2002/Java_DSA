/*
You are given an integer n and a 2D integer array queries.

There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.

queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each query, you need to find the length of the shortest path from city 0 to city n - 1.

Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.

https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i/description/
*/

int[] shortestDistanceAfterQueries(int n, int[][] queries) {
    // Build adjacency list — start with the initial chain
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    for (int i = 0; i < n - 1; i++) adj.get(i).add(i + 1);

    int[] result = new int[queries.length];

    for (int q = 0; q < queries.length; q++) {
        // Add the new shortcut road
        adj.get(queries[q][0]).add(queries[q][1]);

        // Run BFS from 0 to find shortest distance to n-1
        result[q] = bfs(adj, n);
    }

    return result;
}

int bfs(List<List<Integer>> adj, int n) {
    int[] dist = new int[n];
    Arrays.fill(dist, -1);
    dist[0] = 0;

    Queue<Integer> queue = new LinkedList<>();
    queue.offer(0);

    while (!queue.isEmpty()) {
        int city = queue.poll();

        for (int neighbor : adj.get(city)) {
            if (dist[neighbor] == -1) {
                dist[neighbor] = dist[city] + 1;
                if (neighbor == n - 1) return dist[neighbor]; // early exit
                queue.offer(neighbor);
            }
        }
    }

    return dist[n - 1];
}

void main() {
    int n = 5;
    int[][] queries = {{2, 4}, {0, 2}, {0, 4}};
    IO.println(Arrays.toString(shortestDistanceAfterQueries(n, queries)));
}