/*
There is an undirected weighted graph with n vertices labeled from 0 to n - 1.

You are given the integer n and an array edges, where edges[i] = [ui, vi, wi] indicates that there is an edge between vertices ui and vi with a weight of wi.

A walk on a graph is a sequence of vertices and edges. The walk starts and ends with a vertex, and each edge connects the vertex that comes before it and the vertex that comes after it. It's important to note that a walk may visit the same edge or vertex more than once.

The cost of a walk starting at node u and ending at node v is defined as the bitwise AND of the weights of the edges traversed during the walk. In other words, if the sequence of edge weights encountered during the walk is w0, w1, w2, ..., wk, then the cost is calculated as w0 & w1 & w2 & ... & wk, where & denotes the bitwise AND operator.

You are also given a 2D array query, where query[i] = [si, ti]. For each query, you need to find the minimum cost of the walk starting at vertex si and ending at vertex ti. If there exists no such walk, the answer is -1.

Return the array answer, where answer[i] denotes the minimum cost of a walk for query i.

https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph/description/
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
    int find(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }
}

public int[] minimumCost(int n, int[][] edges, int[][] query) {
    DSU ds=new DSU(n);
    int m=query.length;
    int[] dist=new int[n];
    int[] ans=new int[m];
    Arrays.fill(dist, -1);
    for(int[] e:edges)
        ds.union(e[0], e[1]);
    for(int[] e:edges) {
        int root=ds.find(e[0]);
        dist[root]&=e[2];
    }
    for(int i=0;i<m;i++) {
        int[] q=query[i];
        int u=q[0], v=q[1];
        if(u==v)
            ans[i]=0;
        else if(ds.find(u)==ds.find(v))
            ans[i]=dist[ds.find(u)];
        else
            ans[i]=-1;
    }
    return ans;
}

void main() {
    int n = 5;
    int[][] edges = {{0,1,7},{1,3,7},{1,2,1}}, query = {{0,3},{3,4}};
    IO.println(Arrays.toString(minimumCost(n, edges, query)));
}