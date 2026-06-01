/*
Alice and Bob have an undirected graph of n nodes and three types of edges:

Type 1: Can be traversed by Alice only.
Type 2: Can be traversed by Bob only.
Type 3: Can be traversed by both Alice and Bob.
Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.

Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse the graph.

https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/description/
*/
class DSU {
    int[] parent, rank;
    DSU(int n) {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    boolean union(int u, int v) {
        int pu=find(u), pv=find(v);
        if(pu==pv)
            return false;
        if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else if(rank[pv]>rank[pu])
            parent[pu]=pv;
        else {
            parent[pv]=pu;
            rank[pu]++;
        }
        return true;
    }
    int find(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }
}

public int maxNumEdgesToRemove(int n, int[][] edges) {
    DSU ds1=new DSU(n+1);
    DSU ds2=new DSU(n+1);
    int remove=0, used=0;
    for(int[] edge:edges)
        if(edge[0]==3)
            if(ds1.union(edge[1], edge[2]) && ds2.union(edge[1], edge[2]))
                used++;
    for(int[] edge:edges)
        if(edge[0]==1 && ds1.union(edge[1], edge[2]))
            used++;
        else if(edge[0]==2 && ds2.union(edge[1], edge[2]))
            used++;
    int alice=ds1.find(1), bob=ds2.find(1);
    for(int i=2;i<=n;i++)
        if(ds1.find(i)!=alice || ds2.find(i)!=bob)
            return -1;
    return edges.length-used;
}

void main() {
    int n = 4;
    int[][] edges = {{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}};
    IO.println(maxNumEdgesToRemove(n, edges));
}