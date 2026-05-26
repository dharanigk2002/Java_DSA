/*
There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.

You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.

A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.

Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.

https://leetcode.com/problems/largest-color-value-in-a-directed-graph/description/
*/

public int largestPathValue(String colors, int[][] edges) {
    int n=colors.length();
    int[][] dp=new int[n][26];
    int[] indegree=new int[n];
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        adj.get(edge[0]).add(edge[1]);
        indegree[edge[1]]++;
    }
    Queue<Integer> q=new LinkedList();
    for(int i=0;i<n;i++)
        if(indegree[i]==0)
            q.add(i);
    int ans=0, visited=0;
    while(!q.isEmpty()) {
        int node=q.poll();
        int color=colors.charAt(node)-97;
        visited++;
        dp[node][color]++;
        ans=Math.max(dp[node][color], ans);
        for(int nei:adj.get(node)) {
            for(int c=0;c<26;c++)
                dp[nei][c]=Math.max(dp[nei][c], dp[node][c]);
            if(--indegree[nei]==0)
                q.add(nei);
        }
    }
    if(visited!=n)
        return -1;
    return ans;
}

void main() {
    String colors = "abaca";
    int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {3, 4}};
    int largestPath = largestPathValue(colors, edges);
    IO.println(largestPath);
}