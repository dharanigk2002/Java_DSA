/*
There is an undirected tree with n nodes labeled from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

You are also given a 0-indexed integer array values of length n, where values[i] is the value associated with the ith node, and an integer k.

A valid split of the tree is obtained by removing any set of edges, possibly empty, from the tree such that the resulting components all have values that are divisible by k, where the value of a connected component is the sum of the values of its nodes.

Return the maximum number of components in any valid split.

https://leetcode.com/problems/maximum-number-of-k-divisible-components/description/
*/

public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
    if(n<2)
        return 1;
    long[] nodes=new long[n];
    int[] indegree=new int[n];
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++) {
        nodes[i]=values[i];
        adj.add(new ArrayList());
    }
    for(int[] edge:edges) {
        indegree[edge[0]]++;
        indegree[edge[1]]++;
        adj.get(edge[0]).add(edge[1]);
        adj.get(edge[1]).add(edge[0]);
    }
    Queue<Integer> q=new LinkedList();
    for(int i=0;i<n;i++)
        if(indegree[i]==1)
            q.add(i);
    int comp=0;
    while(!q.isEmpty()) {
        int node=q.poll();
        long val=(nodes[node]%k==0) ? 0 : nodes[node];
        if(val==0)
            comp++;
        for(int next:adj.get(node)) {
            indegree[next]--;
            if(indegree[next]==1)
                q.add(next);
            nodes[next]+=val;
        }
    }
    return comp;
}

void main() {
    int n = 5;
    int[][] edges = {{0,2},{1,2},{1,3},{2,4}};
    int[] values = {1,8,1,4,4};
    int k = 6;
    IO.println(maxKDivisibleComponents(n, edges, values, k));
}