/*
You are given an undirected weighted graph of n nodes numbered from 0 to n - 1. The graph consists of m edges represented by a 2D array edges, where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and bi with weight wi.

Consider all the shortest paths from node 0 to node n - 1 in the graph. You need to find a boolean array answer where answer[i] is true if the edge edges[i] is part of at least one shortest path. Otherwise, answer[i] is false.

Return the array answer.

Note that the graph may not be connected.

https://leetcode.com/problems/find-edges-in-shortest-paths/description/
*/

public boolean[] findAnswer(int n, int[][] edges) {
    List<List<int[]>> adj=new ArrayList();
    int m=edges.length;
    boolean[] ans=new boolean[m];
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
    }
    long[] distS=dijikstra(adj, 0);
    long[] distT=dijikstra(adj, n-1);
    for(int i=0;i<m;i++)
        ans[i]=(distT[0]==distS[edges[i][0]]+edges[i][2]+distT[edges[i][1]]) ||
                (distT[0]==distS[edges[i][1]]+edges[i][2]+distT[edges[i][0]]);
    return ans;
}
private long[] dijikstra(List<List<int[]>> adj, int node) {
    int n=adj.size();
    long[] dist=new long[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[node]=0;
    Queue<long[]> q=new PriorityQueue<>((a, b)->Long.compare(a[1], b[1]));
    q.add(new long[]{node, 0});
    while(!q.isEmpty()) {
        node=(int)q.peek()[0];
        long cost=q.poll()[1];
        if(cost>dist[node]) continue;
        for(int[] next:adj.get(node))
            if(dist[next[0]]>next[1]+cost) {
                dist[next[0]]=next[1]+cost;
                q.add(new long[]{next[0], dist[next[0]]});
            }
    }
    return dist;
}

void main() {
    int n=6;
    int[][] edges = {
            {0, 1, 4},
            {0, 2, 1},
            {1, 3, 2},
            {1, 4, 3},
            {1, 5, 1},
            {2, 3, 1},
            {3, 5, 3},
            {4, 5, 2}
    };
    IO.println(Arrays.toString(findAnswer(n, edges)));
}