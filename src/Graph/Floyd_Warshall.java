/*
You are given a weighted directed graph, represented by an adjacency matrix, dist[][] of size n x n, where dist[i][j] represents the weight of the edge from node i to node j. If there is no direct edge, dist[i][j] is set to a large value (i.e., 108) to represent infinity.
The graph may contain negative edge weights, but it does not contain any negative weight cycles.

Your task is to find the shortest distance between every pair of nodes i and j in the graph.

Note: Modify the distances for every pair in place.

https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
*/

final int INF=(int)1e8;
public void floydWarshall(int[][] dist) {
    int n=dist.length;
    for(int k=0;k<n;k++)
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(dist[i][k]!=INF && dist[k][j]!=INF)
                    dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);

}

void main() {
    int[][] graph = {{0, 4, INF, 5, INF}, {INF, 0, 1, INF, 6}, {2, INF, 0, 3, INF}, {INF, INF, 1, 0, 2}, {1, INF, INF, 4, 0}};
    floydWarshall(graph);
    for(int[] edge:graph)
        IO.println(Arrays.toString(edge));
}