/*
Kosaraju's algorithm finds all Strongly Connected Components (SCCs) in a directed graph. An SCC is a maximal set of vertices where every vertex is reachable from every other vertex.
Core Idea
It exploits a key property: if you reverse all edges in a graph, the SCCs remain the same — but the connections between them flip direction. This lets you isolate each SCC cleanly in two passes.

Steps:
1) DFS on original graph — run DFS on the original graph, pushing each vertex onto a stack in finishing order (last to finish = top of stack)
2) Transpose the graph — reverse all edge directions
3) DFS on transposed graph — pop vertices from the stack; for each unvisited vertex, run DFS on the transposed graph. Each DFS tree is one SCC.

*/
void dfs(List<List<Integer>> adj, int src, boolean[] visited, Deque<Integer> component) {
    visited[src]=true;
    for(int nei:adj.get(src)) {
        if(!visited[nei])
            dfs(adj, nei, visited, component);
    }
    component.push(src);
}

void dfs2(List<List<Integer>> adj, int src, boolean[] visited, List<Integer> component) {
    visited[src]=true;
    component.add(src);
    for(int nei:adj.get(src))
        if(!visited[nei])
            dfs2(adj, nei, visited, component);
}
void main() {
    int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 5}, {4, 7}, {5, 6}, {6, 4}, {6, 7}};
    int V=8;
    List<List<Integer>> adj=new ArrayList<>();
    List<List<Integer>> trans=new ArrayList<>();
    for(int i=0;i<V;i++) {
        adj.add(new ArrayList<>());
        trans.add(new ArrayList<>());
    }
    for(int[] edge:edges) {
        adj.get(edge[0]).add(edge[1]);
        trans.get(edge[1]).add(edge[0]);
    }
    boolean[] visited=new boolean[V];
    Deque<Integer> stack=new ArrayDeque<>();
    for(int i=0;i<V;i++)
        if(!visited[i])
            dfs(adj, i, visited, stack);
    Arrays.fill(visited, false);
    List<List<Integer>> list=new ArrayList<>();
    int components=0;
    while (!stack.isEmpty()) {
        int node=stack.pop();
        if(visited[node]) continue;
        components++;
        List<Integer> comp=new ArrayList<>();
        dfs2(trans, node, visited, comp);
        list.add(comp);
    }
    IO.println("List of SCC: "+list);
    IO.println("Total SCC: "+components);
}