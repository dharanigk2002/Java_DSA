/*
Prim's algorithm is used to construct minimum spanning tree(MST). MST is nothing but it has n vertices and (n-1) edges.
There can be multiple MSTs formed from a graph but there will be onl one MST with minimum cost.
So, the intuition here is to traverse across graph and greedily pick only those path with minimal edge weight.
*/

void main() {
    int[][] edges = {{0, 1, 2}, {0, 2, 1}, {2, 1, 1}, {2, 4, 2}, {2, 3, 2}, {4, 3, 1}};
    int V=5;
    List<List<int[]>> adj=new ArrayList<>();
    boolean[] visited=new boolean[V];
    for(int i=0;i<V;i++)
        adj.add(new ArrayList<>());
    for(int[] edge:edges) {
        adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
    }
    Queue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(d->d[0]));
    List<List<Integer>> mst=new ArrayList<>();
    int cost=0;
    q.add(new int[]{0, 0, -1});
    while(!q.isEmpty()) {
        int w=q.peek()[0], node=q.peek()[1], parent=q.poll()[2];
        if(visited[node]) continue;
        cost+=w;
        visited[node]=true;
        if(parent!=-1)
            mst.add(new ArrayList<>(List.of(parent, node)));
        for(int[] nei:adj.get(node))
            if(!visited[nei[0]])
                q.add(new int[]{nei[1], nei[0], node});
    }
    IO.println("MST: "+mst);
    IO.println("Cost: "+cost);
}