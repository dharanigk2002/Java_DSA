void main() {
    int[][] edges = {
            {0, 1, 4},
            {0, 2, 4},
            {2, 1, 2},
            {2, 3, 3},
            {2, 5, 6},
            {2, 4, 1},
            {3, 5, 2},
            {4, 5, 3},
    };
    int src=0;
    int V=6;
    int[] dist=new int[V];
    List<List<int[]>> adj=new ArrayList<>();
    for(int i=0;i<V;i++) {
        dist[i]=Integer.MAX_VALUE;
        adj.add(new ArrayList<>());
    }
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1], w=edge[2];
        adj.get(u).add(new int[]{v, w});
        adj.get(v).add(new int[]{u, w});
    }
    dist[src]=0;
    Queue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(d -> d[0]));
    q.add(new int[]{0, src});
    while(!q.isEmpty()) {
        int[] node=q.poll();
        for(int[] nei:adj.get(node[1])) {
            if(dist[nei[0]] > node[0]+nei[1]) {
                dist[nei[0]]=node[0]+nei[1];
                q.add(new int[]{dist[nei[0]], nei[0]});
            }
        }
    }
    IO.println(Arrays.toString(dist));
}