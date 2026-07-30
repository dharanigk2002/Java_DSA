/*
Minimize the Maximum Edge Weight of Graph

You are given two integers, n and threshold, as well as a directed weighted graph of n nodes numbered from 0 to n - 1. The graph is represented by a 2D integer array edges, where edges[i] = [Ai, Bi, Wi] indicates that there is an edge going from node Ai to node Bi with weight Wi.

You have to remove some edges from this graph (possibly none), so that it satisfies the following conditions:

Node 0 must be reachable from all other nodes.
The maximum edge weight in the resulting graph is minimized.
Each node has at most threshold outgoing edges.
Return the minimum possible value of the maximum edge weight after removing the necessary edges. If it is impossible for all conditions to be satisfied, return -1.

https://leetcode.com/problems/minimize-the-maximum-edge-weight-of-graph/description/
*/
public int minMaxWeight(int n, int[][] edges, int threshold) {
    if(n==1)
        return 0;
    if(threshold==0 || edges.length==0)
        return -1;
    int l=0, r=edges[0][2];
    for(int[] edge:edges)
        r=Math.max(r, edge[2]);
    if(!isPossible(n, edges, r))
        return -1;
    while(l<r) {
        int mid=l+(r-l)/2;
        if(isPossible(n, edges, mid))
            r=mid;
        else
            l=mid+1;
    }
    return l;
}

private boolean isPossible(int n, int[][] edges, int mid) {
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges)
        if(edge[2]<=mid)
            adj.get(edge[1]).add(edge[0]);
    Queue<Integer> q=new LinkedList();
    boolean[] vis=new boolean[n];
    vis[0]=true;
    q.add(0);
    int count=0;
    while(!q.isEmpty()) {
        int node=q.poll();
        count++;
        for(int next:adj.get(node))
            if(!vis[next]) {
                vis[next]=true;
                q.add(next);
            }
    }
    return count==n;
}

void main() {
    int n = 5, threshold = 2;
    int[][] edges = {{1,0,1},{2,0,2},{3,0,1},{4,3,1},{2,1,1}};
    System.out.println(minMaxWeight(n, edges, threshold));
}