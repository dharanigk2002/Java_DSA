/*
Maximize Spanning Tree Stability with Upgrades

You are given an integer n, representing n nodes numbered from 0 to n - 1 and a list of edges, where edges[i] = [ui, vi, si, musti]:

ui and vi indicates an undirected edge between nodes ui and vi.
si is the strength of the edge.
musti is an integer (0 or 1). If musti == 1, the edge must be included in the spanning tree. These edges cannot be upgraded.
You are also given an integer k, the maximum number of upgrades you can perform. Each upgrade doubles the strength of an edge, and each eligible edge (with musti == 0) can be upgraded at most once.

The stability of a spanning tree is defined as the minimum strength score among all edges included in it.

Return the maximum possible stability of any valid spanning tree. If it is impossible to connect all nodes, return -1.

Note: A spanning tree of a graph with n nodes is a subset of the edges that connects all nodes together (i.e. the graph is connected) without forming any cycles, and uses exactly n - 1 edges.

https://leetcode.com/problems/maximize-spanning-tree-stability-with-upgrades/description/
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

    public int find(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }

    public boolean union(int u, int v) {
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
}

public int maxStability(int n, int[][] edges, int k) {
    int low=edges[0][2], high=edges[0][2];
    DSU ds=new DSU(n);
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1], w=edge[2], m=edge[3];
        if(m==1 && !ds.union(u, v))
            return -1;
        low=Math.min(low, w);
        high=Math.max(high, m==1?w:2*w);
    }
    int answer=-1;
    while(low<=high) {
        int mid=low+(high-low)/2;
        if(isPossible(edges, k, mid, n)) {
            answer=mid;
            low=mid+1;
        } else
            high=mid-1;
    }
    return answer;
}

private boolean isPossible(int[][] edges, int k, int mid, int n) {
    DSU ds=new DSU(n);
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1], w=edge[2], m=edge[3];
        if(m==1) {
            if(w<mid)
                return false;
            ds.union(u, v);
        } else if(w>=mid)
            ds.union(u, v);
    }
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1], w=edge[2], m=edge[3];
        if(m==0 && w<mid && 2l*w>=mid && ds.union(u, v)) {
            if(k==0)
                return false;
            k--;
        }
    }
    int root=ds.find(0);
    for(int i=1;i<n;i++)
        if(root!=ds.find(i))
            return false;
    return true;
}

void main() {
    int  n = 3, k = 1;
    int[][] edges = {{0,1,2,1},{1,2,3,0}};

    System.out.println(maxStability(n, edges, k));
}