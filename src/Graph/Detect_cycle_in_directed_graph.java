boolean dfs(List<List<Integer>> adj, int node, boolean[] visited, boolean[] path) {
    visited[node]=path[node]=true;
    for(int neigh:adj.get(node))
        if(!visited[neigh] && dfs(adj, neigh, visited, path) || visited[neigh] && path[neigh])
            return true;
    return path[node]=false;
}

void main() {
    int V=10; // 1 to 10
    boolean[] visited=new boolean[V+1], path=new boolean[V+1];
    int[][] graph={{1, 2}, {2, 3}, {3, 4}, {3, 7}, {4, 5}, {7, 5}, {5, 6}, {8, 2}, {8, 9}, {9, 10}, {10, 8}};
//    int[][] graph={{2, 3}, {3, 1}, {1, 4}, {2, 4}};
    List<List<Integer>> adj=new ArrayList<>();
    for(int i=0;i<=V;i++)
        adj.add(new ArrayList<>());
    // g[0] - from node, g[1] - to node
    for(int[] g:graph)
        adj.get(g[0]).add(g[1]);
    boolean hasCycle=false;
    for(int i=1;i<=V;i++) {
        if(visited[i]) continue;
        if(dfs(adj, i, visited, path)) {
            hasCycle=true;
            break;
        }
    }
    IO.println(hasCycle);
}