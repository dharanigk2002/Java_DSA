void main() {
    final int INF=(int)(1e7);
    int V = 9, src = 0;
    int[][] edges = {{0, 1}, {0, 3}, {1, 2}, {3, 4}, {4, 5}, {2, 6}, {5, 6}, {6, 7}, {6, 8}, {7, 8}};
    List<List<Integer>> adj=new ArrayList<>();
    Queue<Integer> q=new LinkedList<>();
    int[] dist=new int[V];
    for(int i=0;i<V;i++) {
        adj.add(new ArrayList<>());
        dist[i]=INF;
    }
    for(int[] e:edges) {
        adj.get(e[0]).add(e[1]);
        adj.get(e[1]).add(e[0]);
    }
    dist[src]=0;
    q.add(src);
    while(!q.isEmpty()) {
        int node=q.poll();
        for(int nei:adj.get(node))
            if(dist[nei]>dist[node]+1) {
                dist[nei]=dist[node]+1;
                q.add(nei);
            }
    }
    for(int i=0;i<V;i++)
        if(dist[i]==INF)
            dist[i]=-1;
    IO.println(Arrays.toString(dist));
}