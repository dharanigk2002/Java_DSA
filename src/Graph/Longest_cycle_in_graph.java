/*
You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.

The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from node i, then edges[i] == -1.

Return the length of the longest cycle in the graph. If no cycle exists, return -1.

A cycle is a path that starts and ends at the same node.

https://leetcode.com/problems/longest-cycle-in-a-graph/description/
*/

public int longestCycle(int[] edges) {
    int timer=1, max=-1;
    int n=edges.length;
    boolean[] visited=new boolean[n];
    int[] time=new int[n];
    for(int i=0;i<n;i++) {
        if(visited[i])
            continue;
        int node=i, startTime=timer;
        while(node!=-1 && time[node]==0) {
            time[node]=timer++;
            node=edges[node];
        }
        if(node!=-1 && !visited[node] && time[node]>=startTime)
            max=Math.max(max, timer-time[node]);
        node=i;
        while(node!=-1 && !visited[node]) {
            visited[node]=true;
            node=edges[node];
        }
    }
    return max;
}

void main() {
    int[] edges = {3,3,4,2,3};
    IO.println(longestCycle(edges));
}