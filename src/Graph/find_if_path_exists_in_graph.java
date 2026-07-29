/*
There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

https://leetcode.com/problems/find-if-path-exists-in-graph/description/
*/

class DSU {
    private final int[] parent;
    private final int[] rank;

    public DSU(int n) {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }

    public boolean union(int u, int v) {
        int pu=find(u), pv=find(v);
        if(pu==pv)
            return true;
        if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else if(rank[pu]<rank[pv])
            parent[pu]=pv;
        else {
            parent[pv]=pu;
            rank[pu]++;
        }
        return false;
    }

    public int find(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }
}

public boolean validPath(int n, int[][] edges, int source, int destination) {
    DSU ds=new DSU(n);
    for(int[] edge:edges)
        ds.union(edge[0], edge[1]);
    return ds.union(source, destination);
}

void main() {
    int n = 6, source = 0, destination = 5;
    int[][] edges = {{0,1},{0,2},{3,5},{5,4},{4,3}};
    System.out.println(validPath(n, edges, source, destination));
}