/*
There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

Intuition: If there is a cycle in a pathe then the node which is connected to that path will also be an unsafe node.

https://leetcode.com/problems/find-eventual-safe-states/description/
*/

void main() {
    int[][] graph={{1,2},{2,3},{5},{0},{5},{},{}};
//    int[][] graph={{1}, {2}, {3}, {4, 5}, {6}, {6}, {7}, {}, {1, 9}, {10}, {8}, {9}};
    int n=graph.length;
    int[] indegree=new int[n];
    List<List<Integer>> adj=new ArrayList<>();
    List<Integer> safeStates=new LinkedList<>();
    Queue<Integer> q=new LinkedList<>();
    // reverse edges because a none is called terminal if it doesn't have any outdegree. In topological sorting, we may traverse based on indegree.
    // So reverse edges
    for(int i=0;i<n;i++)
        adj.add(new ArrayList<>());
    for(int i=0;i<n;i++) {
        for(int node:graph[i]) {
            adj.get(node).add(i);
            indegree[i]++;
        }
    }
    for(int i=0;i<n;i++)
        if(indegree[i]==0)
            q.add(i);
    while(!q.isEmpty()) {
        int node=q.poll();
        safeStates.addFirst(node);
        for(int neigh:adj.get(node)) {
            indegree[neigh]--;
            if(indegree[neigh]==0)
                q.add(neigh);
        }
    }
    safeStates.sort(Integer::compareTo);
    IO.println(safeStates);
}