/*
There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

Intuition: If there is a cycle in a pathe then the node which is connected to that path will also be an unsafe node.

https://leetcode.com/problems/find-eventual-safe-states/description/
*/

boolean dfs(int[][] graph, int node, boolean[] visited, boolean[] path) {
    visited[node]=path[node]=true;
    for(int neigh:graph[node])
        if(!visited[neigh] && dfs(graph, neigh, visited, path) || path[neigh])
            return true;
    return path[node]=false;
}

void main() {
    int[][] graph={{1,2},{2,3},{5},{0},{5},{},{}};
//    int[][] graph={{1}, {2}, {3}, {4, 5}, {6}, {6}, {7}, {}, {1, 9}, {10}, {8}, {9}};
    int n=graph.length;
    boolean[] visited=new boolean[n], path=new boolean[n];
    List<Integer> safeStates=new ArrayList<>();
    for(int i=0;i<n;i++)
        if(visited[i]) continue;
        else dfs(graph, i, visited, path);
    for(int i=0;i<n;i++)
        if(!path[i])
            safeStates.add(i);
    IO.println(safeStates);
}