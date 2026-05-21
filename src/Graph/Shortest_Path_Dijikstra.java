void main() {
    int[][] edges = {
            {1, 4, 1},
            {1, 2, 2},
            {4, 3, 3},
            {2, 3, 4},
            {3, 5, 1},
            {2, 5, 5}
    };
    int V=5, src=1;
    int[] dist=new int[V+1];
    int[] parent =new int[V+1];
    List<List<int[]>> adj=new ArrayList<>();

    for(int i=0;i<=V;i++) {
        adj.add(new ArrayList<>());
        parent[i]=i;
        dist[i]=Integer.MAX_VALUE;
    }
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1], w=edge[2];
        adj.get(u).add(new int[]{v, w});
        adj.get(v).add(new int[]{u, w});
    }
    dist[src]=0;
    Queue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(d->d[0]));
    q.add(new int[]{0, src});
    while (!q.isEmpty()) {
        int[] node=q.poll();
        if(node[0]>dist[node[1]]) continue;
        for(int[] nei:adj.get(node[1])) {
            if(dist[nei[0]] > node[0]+nei[1]) {
                dist[nei[0]] = node[0]+nei[1];
                parent[nei[0]] = node[1];
                q.add(new int[]{dist[nei[0]], nei[0]});
            }
        }
    }
    int dest=V;
    List<Integer> path=new LinkedList<>();
    while(dest!=parent[dest]) {
        path.addFirst(dest);
        dest=parent[dest];
    }
    path.addFirst(src);
    IO.println(path);
}