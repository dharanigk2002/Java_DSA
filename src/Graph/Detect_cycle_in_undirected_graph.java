final int[][] dirs={{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

boolean bfs(List<List<Integer>> adjList, int start) {
    Queue<int[]> q=new LinkedList<>();
    q.add(new int[]{start, -1});
    boolean[] visited=new boolean[adjList.size()+1]; // 1 to n
    visited[start]=true;
    while(!q.isEmpty()) {
        int[] node=q.poll();
        int parent=node[1];
        for(int neigh:adjList.get(node[0])) {
            if(!visited[neigh]) {
                visited[neigh]=true;
                q.add(new int[]{neigh, node[0]});
            }
            else if(neigh!=parent)
                return true;
        }
    }
    return false;
}

boolean dfs(List<List<Integer>> adjList, int node, int parent, boolean[] visited) {
    visited[node]=true;
    for(int neigh:adjList.get(node))
        if(!visited[neigh]) {
            if(dfs(adjList, neigh, node, visited))
                return true;
        } else if(neigh!=parent)
            return true;
    return false;
}

void main() {
    int[][] graph={
            {1, 2},
            {3, 1},
            {3, 6},
            {4, 3},
            {2, 5},
            {5, 7},
            {6, 7}
    };
    List<List<Integer>> adj=new ArrayList<>();
    int V=7;
    for(int i=0;i<=V;i++)
        adj.add(new ArrayList<>());
    // Adjacency list
    for(int[] row:graph) {
        int u=row[0], v=row[1];
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    boolean hasCycle=bfs(adj, 1);
//    boolean hasCycle=dfs(adj, 1, -1, new boolean[V+1]);
    IO.println(hasCycle);
}