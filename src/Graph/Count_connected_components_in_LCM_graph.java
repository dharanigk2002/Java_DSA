/*
You are given an array of integers nums of size n and a positive integer threshold.

There is a graph consisting of n nodes with the ith node having a value of nums[i]. Two nodes i and j in the graph are connected via an undirected edge if lcm(nums[i], nums[j]) <= threshold.

Return the number of connected components in this graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

The term lcm(a, b) denotes the least common multiple of a and b.

https://leetcode.com/problems/count-connected-components-in-lcm-graph/description/
*/

class DSU {
    int[] parent, rank;
    DSU(int n) {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    boolean union(int a, int b) {
        int pa=find(a), pb=find(b);
        if(pa==pb)
            return false;
        if(rank[pa]>rank[pb])
            parent[pb]=pa;
        else if(rank[pb]>rank[pa])
            parent[pa]=pb;
        else {
            parent[pb]=pa;
            rank[pa]++;
        }
        return true;
    }
    int find(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }
}

public int countComponents(int[] nums, int threshold) {
    DSU ds=new DSU(threshold+1);
    int exceed=0;
    for(int n:nums)
        if(n>threshold)
            exceed++;
    for(int n:nums)
        for(int i=2*n;i<=threshold;i+=n)
            ds.union(n, i);
    Set<Integer> roots=new HashSet();
    for(int n:nums)
        if(n<=threshold)
            roots.add(ds.find(n));
    return roots.size()+exceed;
}

void main() {
    int[] nums = {2,4,8,3,9,12};
    int threshold = 10;
    IO.println(countComponents(nums, threshold));
}