/*
You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.

Return the number of complete connected components of the graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

A connected component is said to be complete if there exists an edge between every pair of its vertices.

https://leetcode.com/problems/count-the-number-of-complete-components/description/
*/

public int countCompleteComponents(int n, int[][] edges) {
    boolean[] visited=new boolean[n];
    int count=0;
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        adj.get(edge[0]).add(edge[1]);
        adj.get(edge[1]).add(edge[0]);
    }
    for(int i=0;i<n;i++)
        if(!visited[i]) {
            List<Integer> component=new ArrayList();
            dfs(adj, i, visited, component);
            int V=component.size();
            int E=component.stream().mapToInt(v->adj.get(v).size()).sum() / 2;
            if(E==(long)V*(V-1)/2)
                count++;
        }
    return count;
}

private void dfs(List<List<Integer>> adj, int src, boolean[] visited, List<Integer> component) {
    visited[src]=true;
    component.add(src);
    for(int nei:adj.get(src)) {
        if(!visited[nei])
            dfs(adj, nei, visited, component);
    }
}

void main() {
    int n=6;
    int[][] edges={{0, 1}, {0, 2}, {1, 2}, {3, 4}};
    int completeComp=countCompleteComponents(n, edges);
    IO.println(completeComp);
}