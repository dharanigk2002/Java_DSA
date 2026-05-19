// Topological ordering using BFS

void main() {
    int[][] edges = {{5, 0}, {5, 2}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
    int[][] cyclic = {{1}, {2}, {3, 4}, {2}, {}};
    int V=6;
    int[] indegree=new int[V];
    List<List<Integer>> adj=new ArrayList<>();
    Queue<Integer> q=new LinkedList<>();
    List<Integer> res=new ArrayList<>();
    for(int i=0;i<V;i++)
        adj.add(new ArrayList<>());
    for(int[] edge:edges) {
        adj.get(edge[0]).add(edge[1]);
        indegree[edge[1]]++;
    }
    for(int i=0;i<V;i++)
        if(indegree[i]==0)
            q.add(i);
    while(!q.isEmpty()) {
        int node=q.poll();
        res.add(node);
        for(int neigh:adj.get(node)) {
            indegree[neigh]--;
            if(indegree[neigh]==0)
                q.add(neigh);
        }
    }
    IO.println(res);
    // Is cyclic graph?
    boolean isCyclic=hasCycle(cyclic);
    IO.println(isCyclic);
}

boolean hasCycle(int[][] graph) {
    int n=graph.length, nodes=0;
    int[] indegree=new int[n];
    Queue<Integer> q=new LinkedList<>();
    for(int[] edge:graph)
        for(int node:edge)
            indegree[node]++;
    for(int i=0;i<n;i++)
        if(indegree[i]==0)
            q.add(i);
    while (!q.isEmpty()) {
        int node=q.poll();
        nodes++;
        for(int nei:graph[node]) {
            indegree[nei]--;
            if(indegree[nei]==0)
                q.add(nei);
        }
    }
    return nodes!=n;
}