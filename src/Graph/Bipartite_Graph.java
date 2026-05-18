/*
There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.

https://leetcode.com/problems/is-graph-bipartite/description/
*/

public boolean isBipartite(int[][] graph) {
    int n=graph.length;
    int[] color=new int[n];
    Arrays.fill(color, -1);
    for(int i=0;i<n;i++) {
        if(color[i]!=-1) continue;
        Queue<Integer> q=new LinkedList<>();
        q.add(i);
        color[i]=0;
        while(!q.isEmpty()) {
            int parent=q.poll();
            for(int node:graph[parent]) {
                if(color[node]==-1) {
                    q.add(node);
                    color[node]=1-color[parent];
                } else if(color[parent]==color[node])
                    return false;
            }
        }
    }
    return true;
}

boolean dfs(int[][] graph, int parent, int c, int[] color) {
    color[parent]=c;
    for(int node:graph[parent])
        if(color[node]==-1 && !dfs(graph, node, 1-c, color) || c==color[node])
            return false;
    return true;
}

void main() {
//    int[][] graph = {{1,2,3},{0,2},{0,1,3},{0,2}};
//    int[][] graph={{4,1},{0,2},{1,3},{2,4},{3,0}};
    int[][] graph={{1,3},{0,2},{1,3},{0,2}};
//    boolean is_bipartite=isBipartite(graph);
    int n=graph.length;
    int[] color=new int[n];
    Arrays.fill(color, -1);
    boolean is_bipartite=true;
    for(int i=0;i<n;i++) {
        if(color[i]!=-1) continue;
        if(!dfs(graph, i, 0, color)) {
            is_bipartite = false;
            break;
        }
    }
    IO.println(is_bipartite);
}