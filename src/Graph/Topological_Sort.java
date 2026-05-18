/*
A topological sort is a linear ordering of vertices in a Directed Acyclic Graph (DAG).
For every directed edge from vertex u to v, vertex u comes before v in the ordering.
*/

void main() {
    int[][] graph={{}, {}, {3}, {1}, {0, 1}, {0, 2}};
    int n=graph.length;
    boolean[] visited=new boolean[n];
    List<Integer> res=new LinkedList<>();
    for(int i=0;i<n;i++) {
        if(visited[i]) continue;
        dfs(graph, i, visited, res);
    }
    IO.println(res);
}

void dfs(int[][] graph, int node, boolean[] visited, List<Integer> res) {
    visited[node]=true;
    for(int neigh:graph[node])
        if(!visited[neigh])
            dfs(graph, neigh, visited, res);
    res.addFirst(node);
}