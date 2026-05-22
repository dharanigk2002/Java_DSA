/*
You are given a graph with n vertices (0 to n-1) and m edges. You can remove one edge from anywhere and add that edge between any two vertices in one operation. Find the minimum number of operations that will be required to connect the graph.
If it is not possible to connect the graph, return -1.

https://www.geeksforgeeks.org/problems/connecting-the-graph/1
*/

class DSU {
    int[] rank, parent;
    DSU(int n) {
        rank=new int[n];
        parent=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    public boolean union(int u, int v) {
        int pu=findParent(u), pv=findParent(v);
        if(pu==pv)
            return true;
        if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else if(rank[pv]>rank[pu])
            parent[pu]=pv;
        else {
            parent[pv]=pu;
            rank[pu]++;
        }
        return false;
    }
    public int findParent(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=findParent(parent[node]);
    }
}

public int Solve(int n, int[][] edge) {
    DSU ds=new DSU(n);
    int extraEdges=0, components=0;
    for(int[] e:edge)
        if(ds.union(e[0], e[1]))
            extraEdges++;
    for(int i=0;i<n;i++)
        if(i==ds.findParent(i))
            components++;
    return components-1 <= extraEdges ? components-1 : -1;
}
// Explanation:
// Remove edge between (1,2) and(0,3) and add edge between (1,4) and (3,5)
void main() {
    int n = 6;
    int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
    int minEdges=Solve(n, edges);
    IO.println(minEdges);
}