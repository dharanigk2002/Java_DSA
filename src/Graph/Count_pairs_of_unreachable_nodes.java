/*
You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.

Return the number of pairs of different nodes that are unreachable from each other.

https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/description/
*/

class DSU {
    int[] parent, size;
    DSU(int n) {
        parent=new int[n];
        size=new int[n];
        for(int i=0;i<n;i++) {
            parent[i]=i;
            size[i]=1;
        }
    }
    boolean union(int u, int v) {
        int pu=find(u), pv=find(v);
        if(pu==pv)
            return true;
        if(size[pu]>size[pv]) {
            parent[pv]=pu;
            size[pu]+=size[pv];
        }
        else {
            parent[pu]=pv;
            size[pv]+=size[pu];
        }
        return false;
    }
    int find(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }
}

public long countPairs(int n, int[][] edges) {
    DSU ds=new DSU(n);
    for(int[] e:edges)
        ds.union(e[0], e[1]);
    long pairs=0, total=0;
    for(int i=0;i<n;i++)
        if(ds.find(i)==i) {
            pairs+=total*ds.size[i];
            total+=ds.size[i];
        }
    return pairs;
}

void main() {
    int n = 7;
    int[][] edges = {{0, 2}, {0, 5}, {2, 4}, {1, 6}, {5, 4}};
    IO.println(countPairs(n, edges));
}