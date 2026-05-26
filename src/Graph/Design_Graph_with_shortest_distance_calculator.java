/*
There is a directed weighted graph that consists of n nodes numbered from 0 to n - 1. The edges of the graph are initially represented by the given array edges where edges[i] = [fromi, toi, edgeCosti] meaning that there is an edge from fromi to toi with the cost edgeCosti.

Implement the Graph class:

Graph(int n, int[][] edges) initializes the object with n nodes and the given edges.
addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to, edgeCost]. It is guaranteed that there is no edge between the two nodes before adding this one.
int shortestPath(int node1, int node2) returns the minimum cost of a path from node1 to node2. If no path exists, return -1. The cost of a path is the sum of the costs of the edges in the path.

https://leetcode.com/problems/design-graph-with-shortest-path-calculator/description/
*/

class Graph {
    private int[][] dist=null;
    private int n;
    public Graph(int n, int[][] edges) {
        this.n=n;
        dist=new int[n][n];
        for(int i=0;i<n;i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i]=0;
        }
        for(int[] edge:edges)
            addEdge(edge);
    }

    public void addEdge(int[] edge) {
        int u=edge[0], v=edge[1], w=edge[2];
        if(w>=dist[u][v])
            return;
        dist[u][v]=w;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(dist[i][u]!=Integer.MAX_VALUE && dist[v][j]!=Integer.MAX_VALUE)
                    dist[i][j]=Math.min(dist[i][j], dist[i][u]+w+dist[v][j]);
    }

    public int shortestPath(int node1, int node2) {
        return dist[node1][node2]!=Integer.MAX_VALUE ? dist[node1][node2] : -1;
    }
}

/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */

void main() {
    int n = 4;
    int[][] edges = {{0, 2, 5}, {0, 1, 2}, {1, 2, 1}, {3, 0, 3}};
    Graph g=new Graph(n, edges);
    IO.println(g.shortestPath(3, 2));
    IO.println(g.shortestPath(0, 3));
    g.addEdge(new int[]{1, 3, 4});
    IO.println(g.shortestPath(0, 3));
}