/*
Second Minimum Time to Reach Destination

A city is represented as a bi-directional connected graph with n vertices where each vertex is labeled from 1 to n (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself. The time taken to traverse any edge is time minutes.

Each vertex has a traffic signal which changes its color from green to red and vice versa every change minutes. All signals change at the same time. You can enter a vertex at any time, but can leave a vertex only when the signal is green. You cannot wait at a vertex if the signal is green.

The second minimum value is defined as the smallest value strictly larger than the minimum value.

For example the second minimum value of [2, 3, 4] is 3, and the second minimum value of [2, 2, 4] is 4.
Given n, edges, time, and change, return the second minimum time it will take to go from vertex 1 to vertex n.

Notes:

You can go through any vertex any number of times, including 1 and n.
You can assume that when the journey starts, all signals have just turned green.

https://leetcode.com/problems/second-minimum-time-to-reach-destination/description/
*/

public int secondMinimum(int n, int[][] edges, int time, int change) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++)
        adj.add(new ArrayList<>());
    for (int[] edge : edges) {
        int u = edge[0] - 1, v = edge[1] - 1;
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    int[] dist1 = new int[n];
    int[] dist2 = new int[n];

    for(int i=0;i<n;i++)
        dist1[i]=dist2[i]=Integer.MAX_VALUE;

    Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

    dist1[0] = 0;
    pq.add(new int[]{0, 0});

    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int currentTime = current[0];
        int node = current[1];

        if (currentTime > dist2[node])
            continue;

        // Wait if the traffic signal is red.
        int departureTime = currentTime;

        if ((departureTime / change) % 2 == 1)
            departureTime = (departureTime / change + 1) * change;

        int arrivalTime = departureTime + time;

        for (int next : adj.get(node)) {
            if (arrivalTime < dist1[next]) {
                dist2[next] = dist1[next];
                dist1[next] = arrivalTime;

                pq.add(new int[]{arrivalTime, next});
            } else if (arrivalTime > dist1[next] && arrivalTime < dist2[next]) {
                dist2[next] = arrivalTime;
                pq.add(new int[]{arrivalTime, next});
            }
        }
    }
    return dist2[n - 1];
}

void main() {
    int n = 5, time = 3, change = 5;
    int[][] edges = {{1,2},{1,3},{1,4},{3,4},{4,5}};
    System.out.println(secondMinimum(n, edges, time, change));
}