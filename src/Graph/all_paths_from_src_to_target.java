/*
All Paths From Source to Target

Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

https://leetcode.com/problems/all-paths-from-source-to-target/description/
*/

public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> paths=new ArrayList();
    int n=graph.length;
    dfs(graph, 0, paths, new ArrayList(), new boolean[n]);
    return paths;
}

private void dfs(int[][] graph, int node, List<List<Integer>> paths, List<Integer> ds, boolean[] visited) {
    visited[node]=true;
    ds.add(node);
    if(graph.length==node+1)
        paths.add(new ArrayList(ds));
    for(int next:graph[node])
        if(!visited[next])
            dfs(graph, next, paths, ds, visited);
    ds.remove(ds.size()-1);
    visited[node]=false;
}

void main() {
    int[][] graph = {{4,3,1},{3,2,4},{3},{4},{}};
    System.out.println(allPathsSourceTarget(graph));
}