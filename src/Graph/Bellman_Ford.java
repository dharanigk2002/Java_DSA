/*
Bellman Ford algorithm is a single source shortest path algorithm. It is useful to find shortest path in directed graph.
It is helpful in detecting negative edge cycle whereas Dijikstra's fail and might result in TLE
*/

int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
    int[] dist=new int[V];
    final int INF=(int)1e9;
    Arrays.fill(dist, INF);
    dist[S]=0;
    // Relax edges (V-1) times which is E*(V-1)
    for(int i=0;i<V-1;i++)
        for(ArrayList<Integer> edge:edges) {
            int u=edge.get(0), v=edge.get(1), w=edge.get(2);
            if(dist[u]!=INF && dist[v]>dist[u]+w)
                dist[v]=Math.min(dist[v], dist[u]+w);
        }
    // To find negative cycle
    for(ArrayList<Integer> edge:edges) {
        int u=edge.get(0), v=edge.get(1), w=edge.get(2);
        if(dist[u]!=INF && dist[v]>dist[u]+w)
            return new int[]{-1};
    }
    return dist;
}
void main() {
    int V = 6, S = 0;
    ArrayList<ArrayList<Integer>> edges=new ArrayList<>();
    int[][] graph = {{3, 2, 6}, {5, 3, 1}, {0, 1, 5}, {1, 5, -3}, {1, 2, -2}, {3, 4, -2}, {2, 4, 3}};
    for(int[] e:graph) {
        ArrayList<Integer> list=new ArrayList<>();
        list.add(e[0]);
        list.add(e[1]);
        list.add(e[2]);
        edges.add(list);
    }
    int[] res=bellman_ford(V, edges, S);
    IO.println(Arrays.toString(res));
}