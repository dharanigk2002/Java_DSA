/*
Minimum Height Trees

A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

https://leetcode.com/problems/minimum-height-trees/description/
*/

public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    if(n==1)
        return Arrays.asList(0);
    List<List<Integer>> adj=new ArrayList();
    int[] degree=new int[n];
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1];
        degree[u]++;
        degree[v]++;
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    Queue<Integer> q=new LinkedList();
    for(int i=0;i<n;i++)
        if(degree[i]==1)
            q.add(i);
    int remainingNodes=n;
    while(remainingNodes>2) {
        int size=q.size();
        remainingNodes-=size;
        while(size-->0) {
            int node=q.poll();
            for(int next:adj.get(node))
                if(--degree[next]==1)
                    q.add(next);
        }
    }
    return new ArrayList<>(q);
}

void main() {
    int[][] edges = {{3, 0}, {3, 1}, {3, 2} ,{3, 4}, {5, 4}};
    int n = 6;
    IO.println(findMinHeightTrees(n, edges));
}