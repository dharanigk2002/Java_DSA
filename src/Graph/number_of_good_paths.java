/*
Number of Good Paths

There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges.

You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.

A good path is a simple path that satisfies the following conditions:

The starting node and the ending node have the same value.
All nodes between the starting node and the ending node have values less than or equal to the starting node (i.e. the starting node's value should be the maximum value along the path).
Return the number of distinct good paths.

Note that a path and its reverse are counted as the same path. For example, 0 -> 1 is considered to be the same as 1 -> 0. A single node is also considered as a valid path.

https://leetcode.com/problems/number-of-good-paths/description/
*/

public int numberOfGoodPaths(int[] vals, int[][] edges) {
    int n=vals.length;
    DSU ds=new DSU(n);
    List<List<Integer>> adj=new ArrayList();
    Map<Integer, List<Integer>> map=new TreeMap<>();
    for(int i=0;i<n;i++) {
        adj.add(new ArrayList());
        map.computeIfAbsent(vals[i], k->new ArrayList()).add(i);
    }
    for(int[] edge:edges) {
        adj.get(edge[0]).add(edge[1]);
        adj.get(edge[1]).add(edge[0]);
    }
    boolean[] active=new boolean[n];
    int answer=n;
    for(Map.Entry<Integer, List<Integer>> entry:map.entrySet()) {
        List<Integer> list=entry.getValue();
        for(int node:list) {
            active[node]=true;
            for(int nei:adj.get(node))
                if(active[nei])
                    ds.union(node, nei);
        }
        Map<Integer, Integer> freq=new HashMap();
        for(int node:list)
            freq.merge(ds.find(node), 1, Integer::sum);
        for(int val:freq.values()) {
            answer+=val*(val-1)/2;
        }
    }
    return answer;
}

private static class DSU {
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

    public int find(int u) {
        if(u==parent[u])
            return u;
        return parent[u]=find(parent[u]);
    }
}

void main() {
    int[] vals = {1,3,2,1,3};
    int[][] edges = {{0,1},{0,2},{2,3},{2,4}};
    System.out.println(numberOfGoodPaths(vals, edges));
}