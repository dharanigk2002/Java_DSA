/*
Divide Nodes Into the Maximum Number of Groups

You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled from 1 to n.

You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional edge between nodes ai and bi. Notice that the given graph may be disconnected.

Divide the nodes of the graph into m groups (1-indexed) such that:

Each node in the graph belongs to exactly one group.
For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to the group with index x, and bi belongs to the group with index y, then |y - x| = 1.
Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes. Return -1 if it is impossible to group the nodes with the given conditions.

https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/description/
*/

public int magnificentSets(int n, int[][] edges) {
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        int u=edge[0]-1, v=edge[1]-1;
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    int[] color=new int[n];
    List<List<Integer>> components=new ArrayList();
    for(int node=0;node<n;node++) {
        if(color[node]==0) {
            List<Integer> component=new ArrayList();
            if(!isBipartite(adj, node, color, component))
                return -1;
            components.add(component);
        }
    }
    int totalGroups=0;
    for(List<Integer> comp:components) {
        int level=0;
        for(int node:comp)
            level=Math.max(level, getLevel(adj, node));
        totalGroups+=level;
    }
    return totalGroups;
}

private int getLevel(List<List<Integer>> adj, int node) {
    boolean[] vis=new boolean[adj.size()];
    Queue<Integer> q=new LinkedList();
    q.add(node);
    vis[node]=true;
    int level=0;
    while(!q.isEmpty()) {
        for(int i=q.size();i>0;i--) {
            int curr=q.poll();
            for(int next:adj.get(curr))
                if(!vis[next]) {
                    q.add(next);
                    vis[next]=true;
                }
        }
        level++;
    }
    return level;
}

private boolean isBipartite(List<List<Integer>> adj, int node, int[] color, List<Integer> comp) {
    Queue<Integer> q=new LinkedList();
    color[node]=1;
    q.add(node);
    while(!q.isEmpty()) {
        int curr=q.poll();
        comp.add(curr);
        for(int next:adj.get(curr))
            if(color[next]==0) {
                q.add(next);
                color[next]=-color[curr];
            } else if(color[next]==color[curr])
                return false;
    }
    return true;
}

void main() {
    int n = 6;
    int[][]edges = {{1,2},{1,4},{1,5},{2,6},{2,3},{4,6}};
    System.out.println(magnificentSets(n, edges));
}