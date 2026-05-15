void bfs(List<List<Integer>> adj, int V, int start) {
    Queue<Integer> q=new LinkedList<>();
    boolean[] visited=new boolean[V+1];
    List<Integer> list=new ArrayList<>();
    q.add(start);
    visited[start]=true;
    while (!q.isEmpty()) {
        int node=q.poll();
        list.add(node);
        for(int neighbour:adj.get(node))
            if(!visited[neighbour]) {
                visited[neighbour]=true;
                q.add(neighbour);
            }
    }
    IO.println(list);
}

void dfs(List<List<Integer>> adj, int start, boolean[] visited, List<Integer> res) {
    if(visited[start])
        return;
    visited[start]=true;
    res.add(start);
    for(int neighbour:adj.get(start))
        dfs(adj, neighbour, visited, res);
}

void dfs2(List<List<Integer>> adj, int start, int V) {
    Stack<Integer> st=new Stack<>();
    boolean[] visited=new boolean[V+1];
    List<Integer> res=new ArrayList<>();
    st.push(start);
    visited[start]=true;

    while (!st.isEmpty()) {
        int node=st.pop();
        res.add(node);
        for(int neighbour:adj.get(node))
            if(!visited[neighbour]) {
                visited[neighbour]=true;
                st.push(neighbour);
            }
    }
    IO.println(res);
}

void main() {
    int V=8;
    List<List<Integer>> adj=new ArrayList<>();
    int[][] graph={
            {1, 2}, {1, 6}, {2, 3}, {2, 4}, {6, 7}, {6, 8}, {4, 5}, {7, 5}
    };
    for(int i=0;i<=V;i++)
        adj.add(new ArrayList<>());
    for(int[] edge:graph) {
        int u=edge[0], v=edge[1];
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    IO.println("BFS traversal:");
    bfs(adj, V, 1);
    boolean[] visited=new boolean[V+1];
    List<Integer> res=new ArrayList<>();
    dfs(adj, 1, visited, res);
    IO.println("DFS traversal:");
    IO.println(res);
    IO.println("Iterative DFS traversal:");
    dfs2(adj, 1, V);
}